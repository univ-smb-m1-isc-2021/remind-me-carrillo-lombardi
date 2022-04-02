package com.example.remindme.classes;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {
    private MultipartFile image;
    public FormWrapper(MultipartFile image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }
    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private String title;
    private String description;
}
