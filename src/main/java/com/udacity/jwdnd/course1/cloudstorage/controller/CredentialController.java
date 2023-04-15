package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    CredentialService credentialService;
    @Autowired
    UserService userService;

    @Autowired
    EncryptionService encryptionService;

    @PostMapping
    public String addOrEditCredential(Authentication authentication, Model model, @ModelAttribute("credentialForm")Credential credentialForm,
                                      @RequestParam(value = "credentialId",required = false) Integer credentialId){
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        Credential credential = new Credential();
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setUserid(userId);

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);

        credential.setPassword(encryptedPassword);

//        try{
            if(credentialId != null){
                credential.setCredentialid(credentialId);
                credential.setKey(encodedKey);
                credentialService.updateCredential(credential);
                model.addAttribute("Success", "Your data was update!");

            }else if (credential.getUrl() != null && credential.getUsername() != null && credential.getPassword() != null){
                    //        save new credential form
                    credential.setKey(encodedKey);
                    credentialService.insertCredential(credential);
                    model.addAttribute("Success", "Your data was saved!");
            }
            else{
                model.addAttribute("Error", "Error, data not saved!");
                return "result";
            }


//        }
//        catch (Exception e){
//            model.addAttribute("Error", "Error, data make an exception !");
//            return "result";
//        }
        return "result";
    }
    @GetMapping(value = "/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId")Integer credentialID, Model model){
        try{
            credentialService.deleteCredential(credentialID);
            model.addAttribute("Success", "Your data was deleted!");
        }
        catch (Exception e){
            model.addAttribute("Error", "Error, data not deleted!");

        }

        return "result";
    }
}