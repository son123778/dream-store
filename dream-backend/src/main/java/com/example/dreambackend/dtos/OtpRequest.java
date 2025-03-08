package com.example.dreambackend.dtos;

public class OtpRequest {
    private String email;
    private String otp;

    // Constructors, Getters và Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
}
