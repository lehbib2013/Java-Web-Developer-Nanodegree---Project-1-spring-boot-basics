package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    // @AuthenticationPrincipal User currLoggedInUser
    public int addFile(User currentLoggedInUser, MultipartFile fileToUpload) throws IOException {
        int returnedCode = 0;
        if((!fileToUpload.isEmpty()) && !checkDuplicateFileName(fileToUpload.getOriginalFilename()))
            returnedCode = fileMapper.insert(new File(fileToUpload.getOriginalFilename(), fileToUpload.getContentType(), Long.toString(fileToUpload.getSize()),currentLoggedInUser.getUserId(),fileToUpload.getInputStream().readAllBytes()));
        else {
            if((fileToUpload.isEmpty())) returnedCode = -2;
            if((checkDuplicateFileName(fileToUpload.getOriginalFilename()))) returnedCode = -1;
        }

        return returnedCode;
    }

    public List<File> getFiles(User currentLoggedIn) {
        System.out.println("jjjj");
        System.out.println(currentLoggedIn.getUserId());
        return fileMapper.getFilesByUserName(currentLoggedIn.getUserId());
    }
    public File getFileById(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public HttpEntity<byte[]> downloadFile(Integer idOfFile) throws IOException {

        File file = fileMapper.getFile(idOfFile);
        byte[] fileBody = file.getFileData();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(file.getContentType()));
        header.set(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=" + file.getFileName().replace(" ", "_"));
        header.setContentLength(Long.parseLong(file.getFileSize()));

        return new HttpEntity<byte[]>(fileBody, header);

    }
    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }

    public File updateFile(File file) {
        return fileMapper.updateFile(file);
    }

    public boolean checkDuplicateFileName(String fileName) {
        if(fileMapper.checkDuplicateFileName(fileName) == null)
            return false;
        else
            return true;
    }
}
