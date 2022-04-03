package com.example.remindme.classes;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {
    private MultipartFile image;
    public FormWrapper(MultipartFile image) {
        this.image = image;
    }
    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
