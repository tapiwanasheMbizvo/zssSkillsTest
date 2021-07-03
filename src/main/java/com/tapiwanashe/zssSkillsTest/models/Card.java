package com.tapiwanashe.zssSkillsTest.models;

import java.time.LocalDate;
import java.util.Date;

public class Card {
    private  String id;
    private LocalDate expiry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }
}
