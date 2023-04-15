package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.stereotype.Repository;

@Repository
public class File{
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public File(Integer fileid, String filename, String contentType, String fileSize, Integer userid, byte[] fileData) {
        this.fileid = fileid;
        this.filename = filename;
        this.contenttype = contentType;
        this.filesize = fileSize;
        this.userid = userid;
        this.filedata = fileData;
    }

    public File() {

    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contenttype;
    }

    public void setContentType(String contentType) {
        this.contenttype = contentType;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getFileData() {
        return filedata;
    }

    public void setFileData(byte[] fileData) {
        this.filedata = fileData;
    }
}
