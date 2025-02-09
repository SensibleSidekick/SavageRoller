package com.savageroller.SavageRoller.models.dto;

public class RegistrationDTO extends LoginFormDTO {

    private String verifyPassword;

    public String getVerifyPassword(){
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword){
        this.verifyPassword = verifyPassword;
    }
}
