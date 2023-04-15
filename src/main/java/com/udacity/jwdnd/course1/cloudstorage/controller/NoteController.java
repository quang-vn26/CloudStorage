package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;

    @PostMapping()
    public String addOrEditNote(Model model, Authentication authentication, @ModelAttribute("noteForm") Note noteForm
                               , @RequestParam(value = "noteId", required = false) Integer noteId) {

        User user = userService.getUser(authentication.getName());
        Integer userid = user.getUserId();
        Note note = new Note();
        note.setNotetitle(noteForm.getNotetitle());
        note.setNotedescription(noteForm.getNotedescription());
        note.setUserid(userid);

        try {
            if (noteId != null) {
                note.setNoteid(noteId);
                noteService.updatNote(note);
                model.addAttribute("Success", "Your note was updated!");
            } else {
                if(note.getNotetitle() != "" && note.getNotetitle() != null && note.getNotetitle() !=" "){
                    noteService.insertNote(note);
                }
                else{
                    model.addAttribute("otherError", "Title is required!");
                    throw new Exception();
                }
                model.addAttribute("Success", "Your note was saved!");
            }

        } catch (Exception e) {
            model.addAttribute("Error", "Error, data not saved!");
        }

        return "result";

    }

    @GetMapping(value = {"/delete/{noteId}"})
    public String deleteFile(@PathVariable(name = "noteId") Integer noteId, Model model){
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("Success", "Your data was deleted!");
        }catch (Exception e){
            model.addAttribute("Error", "Error, data not deleted!");
        }

        return "result";

    }
}
