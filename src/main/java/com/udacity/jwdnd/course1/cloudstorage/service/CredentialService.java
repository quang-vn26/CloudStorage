package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final  CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getAllCredentialsByUserId(Integer userId){
        return credentialMapper.getListCredential(userId);
    }

    public int insertCredential(Credential credential) {
        return credentialMapper.insertCredential(credential);
    }

    public Credential getInfoCredential(Integer credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateCredential(credential);
    }

    public int deleteCredential(Integer credentialId){return credentialMapper.deleteCredential(credentialId);}
}

