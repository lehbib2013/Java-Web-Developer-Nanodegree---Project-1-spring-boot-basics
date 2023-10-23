package com.udacity.jwdnd.course1.cloudstorage.controller;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller()
@RequestMapping("/files")
public class FileController {
    private FileService  fileService;
    private UserMapper userMaper;

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }


    public FileController(FileService fileService, UserMapper userMaper) {
        this.fileService = fileService;
        this.userMaper = userMaper;
    }

    public UserMapper getUserMaper() {
        return userMaper;
    }

    public void setUserMaper(UserMapper userMaper) {
        this.userMaper = userMaper;
    }

    @PostMapping("/uploadFile")
    public String postUploadFile(Authentication currentLoggedIn, @RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        String page=null;
        User currentUser = currentLoggedIn != null ? userMaper.getUserByUserName(currentLoggedIn.getName()) : null;
        Integer newInsertedId = fileService.addFile(currentUser,fileUpload);
        if(currentUser!=null) {
            System.out.println("newInsertedId is "+ newInsertedId.toString());
            if (newInsertedId > 0) model.addAttribute("feedback", "success");
            else {
                if(newInsertedId == -1) {
                    model.addAttribute("feedback", "errorDuplicate");
                    model.addAttribute("feedbackMessage", "duplicate file name");
                                      }
                if(newInsertedId == -2) {
                    model.addAttribute("feedback", "errorEmpty");
                    model.addAttribute("feedbackMessage", "empty selected file");
                }
                if(newInsertedId < -2)  {
                    model.addAttribute("feedback", "error");
                    model.addAttribute("feedbackMessage", "some error was happened");
                }
                }
            page="result";
        }
        else  page="home";
        return page;
    }

    @GetMapping(value = "/deleteFile/{idFileToDelete}")
    public String  DeleteFile(Authentication currentLoggedIn, @PathVariable Integer idFileToDelete, Model model) {
        fileService.deleteFile(idFileToDelete);
        model.addAttribute("feedback","success");
        return "result";
    }


    @RequestMapping( method = {RequestMethod.GET }, value = "/downloadFile/{idFileToDownload}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
    public HttpEntity<byte[]> DownloadFile(Authentication currentLoggedIn, @PathVariable Integer idFileToDownload, Model model) throws IOException {
        System.out.println("ccccccc: est :");
        System.out.println(idFileToDownload);
        return fileService.downloadFile(idFileToDownload);

    }
}
