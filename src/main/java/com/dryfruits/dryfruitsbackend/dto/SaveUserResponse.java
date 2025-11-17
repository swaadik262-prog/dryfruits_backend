package com.dryfruits.dryfruitsbackend.dto;

public class SaveUserResponse {
    private boolean success;
    private Integer userId;
    private String profileUrl;
    private String message;
    private String token;

    public SaveUserResponse() {}

    public SaveUserResponse(boolean success, Integer userId, String profileUrl, String message, String token) {
        this.success = success;
        this.userId = userId;
        this.profileUrl = profileUrl;
        this.message = message;
        this.token = token;
    }

    // getters / setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getProfileUrl() { return profileUrl; }
    public void setProfileUrl(String profileUrl) { this.profileUrl = profileUrl; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
