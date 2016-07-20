package com.simio.poker.model;

/**
 * Created by Simio on 20/7/2559.
 */
public class CardDetail {
    private String value;
    private String suit;

    public CardDetail() {
    }

    public CardDetail(String mValue, String mSuit) {
        this.value = mValue;
        this.suit = mSuit;
    }



    public String getSuit() {
        return suit;
    }

    public void setSuit(String mSuit) {
        this.suit = mSuit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String mValue) {
        this.value = mValue;
    }
}
