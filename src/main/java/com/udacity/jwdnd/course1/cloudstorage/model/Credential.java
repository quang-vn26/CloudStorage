package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.Base64;

@Repository
public class Credential{
    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;


    public Credential(){

    }

    public Credential(Integer credentialid, String url, String username, String key, String password, Integer userid) {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }

    public String getDecryptedPassword(Credential credential){
        SecureRandom random = new SecureRandom();
        String encodedKey = credential.getKey();
        EncryptionService encryptionService = new EncryptionService();
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), encodedKey);
        return  decryptedPassword;
    }
    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
