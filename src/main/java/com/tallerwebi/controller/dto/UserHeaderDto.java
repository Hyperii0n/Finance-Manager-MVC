package com.tallerwebi.controller.dto;

public class UserHeaderDto {

    private String name;
    private String photoUrl;

    public UserHeaderDto(String userName, String photoUrl) {
        this.name = userName;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
