package com.simio.poker.model;

/**
 * Created by Simio on 20/7/2559.
 */
public class CardDetail {
    private String value;
    private String suit;
    private String scoreOfValue;

    public CardDetail() {
    }

    public CardDetail(String mValue, String mSuit ) {
        this.value = mValue;
        this.suit = mSuit;
        this.scoreOfValue = setScoreofVAlue(mValue);
    }
    private String setScoreofVAlue(String _value){
        String score;
        switch (_value){
            case "1":score="01"; break;
            case "2":score="02"; break;
            case "3":score="03"; break;
            case "4":score="04"; break;
            case "5":score="05"; break;
            case "6":score="06"; break;
            case "7":score="07"; break;
            case "8":score="08"; break;
            case "9":score="09"; break;
            case "T":score="10"; break;
            case "J":score="11"; break;
            case "Q":score="12"; break;
            case "K":score="13"; break;
            case "A":score="14"; break;
            default:score =_value;
        }
        return  score;
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


    public String getScoreOfValue() {
        return scoreOfValue;
    }

    public void setScoreOfValue(String scoreOfValue) {
        this.scoreOfValue = scoreOfValue;
    }
}
