package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private  UserService userService;
    @Autowired
    private  FileService fileService;
    @Autowired
    private  NoteService noteService;
    @Autowired
    private  CredentialService credentialService;
    @Autowired
    private  EncryptionService encryptionService;

    @GetMapping()
    public String getHomepage(Authentication authentication, Model model){
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        if(userId == null){
            return "redirect:/login";
        }
        model.addAttribute("files",fileService.getAllFileByUserId(userId));
        model.addAttribute("notes",noteService.getAllNotesByUserId(userId));
        model.addAttribute("credentials",credentialService.getAllCredentialsByUserId(userId));
        model.addAttribute("fileForm", new File());
        model.addAttribute("noteForm",new Note());
        model.addAttribute("credentialForm",new Credential());
        return "home";
    }
}

