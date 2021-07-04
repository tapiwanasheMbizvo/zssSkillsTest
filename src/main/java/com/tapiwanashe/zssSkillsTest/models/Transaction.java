package com.tapiwanashe.zssSkillsTest.models;

public class Transaction {

    private  long bookID;
    private  String cardID;

    public Transaction() {
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
}
