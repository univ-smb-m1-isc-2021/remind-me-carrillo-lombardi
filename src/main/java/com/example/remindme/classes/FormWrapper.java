package com.example.remindme.classes;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {
    //Class uniquement pour faire passer un MultipartFile donc ici un json 
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
