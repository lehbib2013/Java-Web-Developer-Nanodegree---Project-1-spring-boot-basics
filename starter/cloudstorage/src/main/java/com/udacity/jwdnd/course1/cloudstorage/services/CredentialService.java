package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encriptionService;

    // @AuthenticationPrincipal User currLoggedInUser

     public CredentialService(CredentialMapper credentialMapper, EncryptionService encriptionService) {
        this.credentialMapper = credentialMapper;
        this.encriptionService = encriptionService;
    }


    public CredentialMapper getCredentialMapper() {
        return credentialMapper;
    }

    public void setCredentialMapper(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public EncryptionService getEncriptionService() {
        return encriptionService;
    }

    public void setEncriptionService(EncryptionService encriptionService) {
        this.encriptionService = encriptionService;
    }

    public int addCredential(User currentLoggedIn, Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

       // new SecretKeySpec(randomKeyBytes, "AES");
        String encryptedPassword = encriptionService.encryptValue(credential.getPassword(), encodedKey);
        return credentialMapper.insert(new Credential(credential.getUrl(),currentLoggedIn.getUserName(),encodedKey,encryptedPassword ,currentLoggedIn.getUserId()));
    }

    public Credential[] getCredentialsByUserName(User currentLoggedIn) {
        Credential[] listOfCredentials = credentialMapper.getCredentialsByUserName(currentLoggedIn.getUserId());
        System.out.println("currentLoggedIn.getUserId()");
        System.out.println(currentLoggedIn.getUserId());
        for (Credential credential : listOfCredentials) {
            credential.setShownPassword(encriptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return listOfCredentials;
    }


    public Credential getCredentialById(Integer crdentialId) {
        Credential currCredential = credentialMapper.getCredential(crdentialId);
        String decryptedPassword = encriptionService.decryptValue(currCredential.getPassword(),currCredential.getKey());
        return new Credential(currCredential.getUrl(),currCredential.getUsername(),currCredential.getKey(), decryptedPassword, currCredential.getUserid());
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

    public void updateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
      // new SecretKeySpec(randomKeyBytes, "AES");
        String encryptedPassword = encriptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        credentialMapper.updateCredential(credential);
    }
    public String getKeyByCredentialId(Integer idOfCredential){
        return credentialMapper.getKeyByCredentialId(idOfCredential);
    }
}
