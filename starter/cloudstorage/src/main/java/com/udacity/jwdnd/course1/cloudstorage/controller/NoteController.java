package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@Controller()
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;
    private UserMapper userMaper;

    public NoteController(NoteService noteService, UserMapper userMaper) {
        this.noteService = noteService;
        this.userMaper = userMaper;
    }

    public NoteService getNoteService() {
        return noteService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public UserMapper getUserMaper() {
        return userMaper;
    }

    public void setUserMaper(UserMapper userMaper) {
        this.userMaper = userMaper;
    }

    @PostMapping("/addNote")
    public ModelAndView postAddNote(Authentication currentLoggedIn, @ModelAttribute("noteToAdd") Note noteToAdd, Model model) throws IOException {
        User currentUser = null;
        String returnedView = "";
        Integer newInsertedId = -1;
        model.addAttribute("activeTab", "notes");
        currentUser = currentLoggedIn != null ? userMaper.getUserByUserName(currentLoggedIn.getName()) : null;
        if (currentUser != null || currentLoggedIn instanceof AnonymousAuthenticationToken) {
            System.out.println("noteToAdd.getNoteId()");
            System.out.println(noteToAdd.getNoteId());
            if(noteToAdd.getNoteId() == null)
                newInsertedId = noteService.createNote(currentUser, noteToAdd);//addFile(currentLoggedIn,fileUpload);
            else
                {
                    newInsertedId = noteToAdd.getNoteId();
                    noteToAdd.setUserId(currentUser.getUserId());
                    noteService.updateNote(noteToAdd);
                }
            model.addAttribute("notes", noteService.getNotesByUserName(currentUser));
            if (newInsertedId > 0) {
                model.addAttribute("feedback", "success");
            }
            else {
                model.addAttribute("feedback", "error");
                model.addAttribute("feedbackMessage", "some error occured during this operation");
            }
            returnedView = "home";
        }
        else returnedView = "login";

        return new ModelAndView("redirect:/"+returnedView);
    }

    @GetMapping(value = "/deleteNote/{idNoteToDelete}")
    public String  DeleteCredential(Authentication currentLoggedIn, @PathVariable Integer idNoteToDelete, Model model) {
       noteService.deleteNote(idNoteToDelete);
        model.addAttribute("feedback","success");
        return "result";
    }

}
