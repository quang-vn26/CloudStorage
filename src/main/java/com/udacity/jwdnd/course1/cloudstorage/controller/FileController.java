package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping
    public String postUploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        File file = new File();
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
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
        if (!fileName.equals(null) && !fileName.equals("") && !fileName.equals(" ")) {
            try {
                fileService.insertFile(file);
                model.addAttribute("Success", "Your file was saved!");
            }catch (Exception e){
                model.addAttribute("Error", "Error, data not saved!");
            }
        } else {
            model.addAttribute("otherError", "File is null, please chose a file!");
            return "result";
        }
        return "result";


    }
    @GetMapping(value = {"/download/{fileid}"})
    public ResponseEntity<byte[]> downloadFile(@PathVariable(name = "fileid") String fileId,
                                               HttpServletResponse response, HttpServletRequest request) {
        File file = fileService.getDetailFile(Integer.parseInt(fileId));
        byte[] data = file.getFileData();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
        String fileName = file.getFilename();
        httpHeaders.setContentDispositionFormData(fileName, fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> dataDownload = new ResponseEntity<byte[]>(data, httpHeaders, HttpStatus.OK);
        return dataDownload;
    }

    @GetMapping(value = {"/delete/{fileid}"})
    public String deleteFile(@PathVariable(name = "fileid") String fileId){
        fileService.deleteFile(Integer.parseInt(fileId));
        return "redirect:/home";

    }


}
