package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@Controller()
@RequestMapping("/credentials")
public class CredentialController {
    private CredentialService credentialService;
    private UserMapper userMaper;

    public CredentialController(CredentialService credentialService, UserMapper userMaper) {
        this.credentialService = credentialService;
        this.userMaper = userMaper;
    }

    public CredentialService getCredentialService() {
        return credentialService;
    }

    @GetMapping(value = "/deleteCredential/{idCredentialToDelete}")
    public String  DeleteCredential(Authentication currentLoggedIn, @PathVariable Integer idCredentialToDelete, Model model) {
        credentialService.deleteCredential(idCredentialToDelete);
        model.addAttribute("feedback","success");
        return "result";
    }

    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public UserMapper getUserMaper() {
        return userMaper;
    }

    public void setUserMaper(UserMapper userMaper) {
        this.userMaper = userMaper;
    }

    @GetMapping(value = "/getCredential/{idCredentialToGet}")
    public String  getCredential(Authentication currentLoggedIn, @PathVariable Integer idCredentialToGet, Model model) {
        credentialService.getCredentialById(idCredentialToGet);
        model.addAttribute("feedback","success");
        return "result";
    }

    @PostMapping("/addCredential")
    public String postAddNote(Authentication currentLoggedIn, @ModelAttribute("credentialToAdd") Credential credentialToAdd, Model model) throws IOException {
        Integer newInsertedId = null;
        String currKey = null;
        String returnedView = "";
        //addFile(currentLoggedIn,fileUpload);
        User currentUser = null;
        System.out.println("credentialToAdd.getPassword()");
        currentUser = currentLoggedIn != null ? userMaper.getUserByUserName(currentLoggedIn.getName()) : null;
        if (currentUser != null && !(currentLoggedIn instanceof AnonymousAuthenticationToken)) {
            //credentialToAdd.setUserid(currentUser.getUserId());
            System.out.println("usernameee");
            System.out.println(currentLoggedIn.getName());
            if(credentialToAdd.getCredentialId() == null)
                    newInsertedId = credentialService.addCredential(currentUser, credentialToAdd);
             else
            {
                newInsertedId = credentialToAdd.getCredentialId();
              // currKey = credentialService.getKeyByCredentialId(newInsertedId);
                credentialToAdd.setUserid(currentUser.getUserId());
                credentialToAdd.setUsername(currentUser.getUserName());
              //  credentialToAdd.setKey(currKey);
              //  credentialToAdd.setShownPassword(credentialToAdd.getPassword());
                System.out.println("credentialToAdd informations :");
                System.out.println(credentialToAdd.getCredentialId());
                System.out.println(credentialToAdd.getPassword());
                System.out.println(credentialToAdd.getKey());
                System.out.println(credentialToAdd.getShownPassword());
                System.out.println(credentialToAdd.getUserid());
                System.out.println(credentialToAdd.getUsername());
                System.out.println(credentialToAdd.getUrl());
                credentialService.updateCredential(credentialToAdd);
            }

            if(newInsertedId>0)  model.addAttribute("feedback","success");
            else {
                model.addAttribute("feedback","error");
                model.addAttribute("feedbackMessage","some error occured during this operation");
            }
            returnedView = "result";
        } else returnedView = "login";

        return returnedView;


    }
}
