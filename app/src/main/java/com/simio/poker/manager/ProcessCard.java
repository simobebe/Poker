package com.simio.poker.manager;

import com.simio.poker.model.CardDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Simio on 21/7/2559.
 */
public class ProcessCard {
    private static ProcessCard instance;
    public static ProcessCard getInstance() {
       if(instance ==null)
           instance = new ProcessCard();
        return instance;
    }
    private ProcessCard() {
    }

    public boolean isStraightFlush(ArrayList<CardDetail> _listCard) {
        return isStraight(_listCard)&&isFlush(_listCard);
    }

    public boolean isFour(ArrayList<CardDetail> _listCard) {
        ArrayList<CardDetail> listCard = sortCard(_listCard);
        int four = 0;
        for (int i = 0; i < 4; i++) {
            if (!listCard.get(i).getValue().equals(listCard.get(i + 1).getValue())) continue;
            four++;
        }
        return four == 3;
    }

    public boolean isFlush(ArrayList<CardDetail> _listCard) {
        int flush = 0;
        for (int i = 0; i < 4; i++) {
            if (!_listCard.get(i).getSuit().equals(_listCard.get(i + 1).getSuit())) break;
            flush = i;
        }
        return flush == 3;
    }

    public boolean isStraight(ArrayList<CardDetail> _listCard) {
        ArrayList<CardDetail> listCard = sortCard(_listCard);
        int straight = 0;
        for (int i = 0; i < 4; i++) {
            int scoreA = Integer.parseInt(listCard.get(i).getScoreOfValue());
            int scoreB = Integer.parseInt(listCard.get(i+1).getScoreOfValue());
            if (scoreB - scoreA != 1) break;
            straight = i;
        }
        return straight == 3;
    }

    public boolean isThree(ArrayList<CardDetail> _listCard) {
        ArrayList<CardDetail> listCard = sortCard(_listCard);
        int three = 0;
        for (int i = 0; i < 4; i++) {
            if (!listCard.get(i).getValue().equals(listCard.get(i + 1).getValue())) continue;
            three++;
        }
        return three == 2;
    }
    public boolean isTwoPairs(ArrayList<CardDetail> _listCard) {

        return checkPair(_listCard).size() ==2;
    }
    public boolean isPair(ArrayList<CardDetail> _listCard) {
        return checkPair(_listCard).size() == 1;
    }
    public ArrayList<CardDetail> checkPair(ArrayList<CardDetail> _listCard) {
        ArrayList<CardDetail> listCard = new ArrayList<>(sortCard(_listCard)) ;
        ArrayList<CardDetail> listCardPair = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (!listCard.get(i).getValue().equals(listCard.get(i + 1).getValue()))continue;
            listCardPair.add(listCard.get(i)) ;
        }
        return listCardPair;
    }
    private ArrayList<CardDetail> sortCard(ArrayList<CardDetail> _listCard) {
        Collections.sort(_listCard, new Comparator<CardDetail>() {
            @Override
            public int compare(CardDetail o1, CardDetail o2) {
                return o1.getScoreOfValue().compareTo(o2.getScoreOfValue());
            }
        });
        return _listCard;
    }
}
