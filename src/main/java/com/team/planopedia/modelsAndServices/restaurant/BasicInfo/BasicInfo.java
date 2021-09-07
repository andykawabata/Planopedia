package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;


public class BasicInfo {
    
    private String name;
    private String address;
    private String phone;
    private String rating;

    public BasicInfo(String name, String address, String phone, String rating) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    
}
