package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private File file;

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping
    public String postUploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        InputStream inputStream = multipartFile.getInputStream();
        file.setFileData(multipartFile.getBytes());
        file.setFilename(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFilesize(String.valueOf(multipartFile.getSize()));
        file.setUserid(userId);

        List<File> files = fileService.getAllFileByUserId(userId);

        String fileName = file.getFilename();
        for (File oneFile : files) {
            if (oneFile.getFilename().equals(fileName)) {
                model.addAttribute("otherError", "File already exists");
                return "result";
            }
        }
        if (!fileName.equals(null) || !fileName.equals("") || !fileName.equals(" ")) {
            int result = fileService.insertFile(file);
            if (result == 1) {
//                model.addAttribute("files","files");
                model.addAttribute("Success", "Your file was saved!");
            } else {
                model.addAttribute("Error", "Error, data not saved!");
            }
        } else {
            model.addAttribute("otherError", "File is null, please chose a file!");
            return "result";
        }
        return "result";


    }

}
