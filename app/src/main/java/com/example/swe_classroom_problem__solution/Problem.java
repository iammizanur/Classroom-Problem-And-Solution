package com.example.swe_classroom_problem__solution;

public class Problem {

    String id;
    String roomNo;
    String Category;
    String description;
    String email;
    String key;

    public Problem(String id, String roomNo, String category, String description, String email, String key) {
        this.id = id;
        this.roomNo = roomNo;
        Category = category;
        this.description = description;
        this.email = email;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}