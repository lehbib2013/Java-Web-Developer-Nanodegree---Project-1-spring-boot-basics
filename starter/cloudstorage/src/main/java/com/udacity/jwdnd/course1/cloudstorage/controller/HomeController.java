package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller()
public class HomeController {
    private FileService filesService;
    private NoteService notesService;
    private CredentialService credentialsService;
    private UserMapper userMaper;
    private EncryptionService encryptionService;

    public HomeController(FileService filesService, NoteService notesService, CredentialService credentialsService, UserMapper userMaper,EncryptionService encryptionService) {
        this.filesService = filesService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.userMaper = userMaper;
        this.encryptionService =encryptionService;
    }

    public UserMapper getUserMaper() {
        return userMaper;
    }

    public void setUserMaper(UserMapper userMaper) {
        this.userMaper = userMaper;
    }

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping(value={"/home","/"})
    public String getHomePage(Authentication currentLoggedIn, @RequestParam(value = "activeTab", defaultValue = "files") String activeTab,  Model model) {
        System.out.println(currentLoggedIn);
        User currentUser = null;
        String returnedView ="";
        System.out.println("yyyyyy user");
        System.out.println(currentLoggedIn);
        currentUser = currentLoggedIn != null ? userMaper.getUserByUserName(currentLoggedIn.getName()) : null;
        if(currentUser!= null && !(currentLoggedIn instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("files", filesService.getFiles(currentUser));
            model.addAttribute("credentials", credentialsService.getCredentialsByUserName(currentUser));
            model.addAttribute("notes", notesService.getNotesByUserName(currentUser));
            model.addAttribute("encryptionService", encryptionService);
            model.addAttribute("noteToAdd",new Note());
            switch (activeTab) {
                case "files":
                    model.addAttribute("activeTab", "files");
                    break;
                case "credentials":
                    model.addAttribute("activeTab", "credentials");
                    break;
                case "notes":
                  //  model.addAttribute("activeTab", "notes");
                    break;
            }
            returnedView ="home";
        }
        else returnedView = "login";

        return returnedView;
    }
}
