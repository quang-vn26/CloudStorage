package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
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

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final  CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController( UserService userService, FileService fileService,
                          NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String getHomepage(Authentication authentication, @ModelAttribute("newFile") File file, @ModelAttribute("newNote")Note newNote
            , @ModelAttribute("newCredential")Credential credential, Model model){
        User user = userService.getUser(authentication.getName());
        int userId = user.getUserId();
        model.addAttribute("files",fileService.getAllFile(userId));
        model.addAttribute("notes",noteService.getAllNotes(userId));
        model.addAttribute("credentials",credentialService.getAllCredentials(userId));
        return "home";
    }

    @PostMapping()
    public String postHomepage(){

        return "home";
    }
}

