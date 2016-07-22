package com.simio.poker.manager;

import com.simio.poker.model.CardDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Simio on 21/7/2559.
 */
public class CheckRuleCard {
    private static CheckRuleCard instance;
    public static CheckRuleCard getInstance() {
       if(instance ==null)
           instance = new CheckRuleCard();
        return instance;
    }
    private CheckRuleCard() {
    }

    public boolean isStraightFlush(ArrayList<CardDetail> _listCard) {
        return isStraight(_listCard)&&isFlush(_listCard);
    }
    public boolean isFour(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardFour = new ArrayList<>();
        int j=0;
        while (j < 2){
            boolean a = _listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue());
            boolean b = _listCard.get(j).getValue().equals(_listCard.get(j + 2).getValue());
            boolean c = _listCard.get(j).getValue().equals(_listCard.get(j + 3).getValue());
            if (!a || !b ||!c){
                j = j+1;
                continue;
            }
            listCardFour.add(_listCard.get(j)) ;
            break;
        }
        return listCardFour.size() == 1;
    }
    public CardDetail getCardFour(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardFour = new ArrayList<>();
        int j=0;
        while (j < 2){
            boolean a = _listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue());
            boolean b = _listCard.get(j).getValue().equals(_listCard.get(j + 2).getValue());
            boolean c = _listCard.get(j).getValue().equals(_listCard.get(j + 3).getValue());
            if (!a || !b ||!c){
                j = j+1;
                continue;
            }
            listCardFour.add(_listCard.get(j)) ;
            break;
        }
        return listCardFour.get(0);
    }
    public boolean isFullHouse(ArrayList<CardDetail> _listCard) {
        if (!isThree(_listCard)){
            return false;
        }else{
            return isPairForFullHouse(_listCard,getCardThree(_listCard));
        }
//        return isThree(_listCard) && isPairForFullHouse(_listCard);
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
            if (scoreA - scoreB != 1) break;
            straight = i;
        }
        return straight == 3;
    }
    public boolean isThree(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardThree = new ArrayList<>();
        int j=0;
        while (j < 3){
            boolean a = _listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue());
            boolean b = _listCard.get(j).getValue().equals(_listCard.get(j + 2).getValue());
            if (!a || !b){
                j = j+1;
                continue;
            }
            listCardThree.add(_listCard.get(j)) ;
            break;
        }
        return listCardThree.size() == 1;
    }
    public CardDetail getCardThree(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardThree = new ArrayList<>();
        int j=0;
        while (j < 3){
            boolean a = _listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue());
            boolean b = _listCard.get(j).getValue().equals(_listCard.get(j + 2).getValue());
            if (!a || !b){
                j = j+1;
                continue;
            }
            listCardThree.add(_listCard.get(j)) ;
            break;
        }
        return listCardThree.get(0);
    }
    public boolean isTwoPairs(ArrayList<CardDetail> _listCard) {
        return checkPair(_listCard).size() == 2;
    }
    public boolean isPair(ArrayList<CardDetail> _listCard) {
        return checkPair(_listCard).size() == 1;
    }

    public boolean isPairForFullHouse(ArrayList<CardDetail> _listCard,CardDetail cardThree) {
        return checkPairForFullHouse(_listCard,cardThree).size() == 1;
    }
    public int getHighCard(ArrayList<CardDetail> _listCard){
        sortCard(_listCard);
        return Integer.parseInt(_listCard.get(0).getScoreOfValue());
    }
    public int getRemainsCard(ArrayList<CardDetail> _listCard,int hight,int lowest){
        int remains = -1;
        for (CardDetail cardetail:_listCard){
            if (cardetail.getScoreOfValue().equals(String.valueOf(hight)))continue;
            if (cardetail.getScoreOfValue().equals(String.valueOf(lowest)))continue;
            remains = Integer.parseInt(cardetail.getScoreOfValue());
            break;
        }
      return  remains;
    }
    public String getNameHighCard(ArrayList<CardDetail> _listCard){
        sortCard(_listCard);
        return _listCard.get(0).getName();
    }
    public ArrayList<CardDetail> checkPair(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardPair = new ArrayList<>();
        int j=0;
        while (j < 4){
            if (!_listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue())){
                j = j+1;
                continue;
            }
            listCardPair.add(_listCard.get(j)) ;
            j = j+2;
        }
        return listCardPair;
    }
    public ArrayList<CardDetail> getCardOuterPair(ArrayList<CardDetail> _listCard) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardOuterPair = new ArrayList<>();
        int j=0;
        while (j < 4){
            if (!_listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue())){
                listCardOuterPair.add(_listCard.get(j)) ;
                j = j+1;
                continue;
            }
            j = j+2;
        }
        return listCardOuterPair;
    }
    public ArrayList<CardDetail> checkPairForFullHouse(ArrayList<CardDetail> _listCard,CardDetail cardThree) {
        sortCard(_listCard);
        ArrayList<CardDetail> listCardPair = new ArrayList<>();
        int j=0;
        while (j < 4){
            if (_listCard.get(j).getValue().equals(cardThree.getValue())){
                j = j+1;
                continue;
            }
            if (!_listCard.get(j).getValue().equals(_listCard.get(j + 1).getValue())){
                j = j+1;
                continue;
            }
            listCardPair.add(_listCard.get(j)) ;
            j = j+2;
        }
        return listCardPair;
    }
    public ArrayList<CardDetail> sortCard(ArrayList<CardDetail> _listCard) {
        Collections.sort(_listCard, new Comparator<CardDetail>() {
            @Override
            public int compare(CardDetail o1, CardDetail o2) {
                return o1.getScoreOfValue().compareTo(o2.getScoreOfValue());
            }
        });
        Collections.reverse(_listCard);
        return _listCard;
    }
}
