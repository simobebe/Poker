package com.simio.poker.manager;

import com.simio.poker.model.CardDetail;
import com.simio.poker.model.UserName;

import java.util.ArrayList;

/**
 * Created by Simio on 21/7/2559.
 */
public class CheckWinner {
    private static CheckWinner instance;
    public static CheckWinner getInstance() {
       if(instance ==null)
           instance = new CheckWinner();
        return instance;
    }
    private CheckWinner() {
    }

    public UserName userStraightFlush(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveStraightFlush =  CheckRuleCard.getInstance().isStraightFlush(_listcardSomchai);
        boolean somsakHaveStraightFlush =  CheckRuleCard.getInstance().isStraightFlush(_listcardSomsak);
        if (somchaiHaveStraightFlush && somsakHaveStraightFlush){
            winner = userHighCard(_listcardSomchai,_listcardSomsak);
        }else if (somchaiHaveStraightFlush){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveStraightFlush){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userFour(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveFour =  CheckRuleCard.getInstance().isFour(_listcardSomchai);
        boolean somsakHaveFour =  CheckRuleCard.getInstance().isFour(_listcardSomsak);
        if (somchaiHaveFour && somsakHaveFour){
            int valuseSomchai = Integer.parseInt( CheckRuleCard.getInstance().getCardFour(_listcardSomchai).getScoreOfValue());
            int valuseSomsak = Integer.parseInt( CheckRuleCard.getInstance().getCardFour(_listcardSomsak).getScoreOfValue());
            if ( valuseSomchai > valuseSomsak){
                winner = UserName.SOMCHAI;
            } else{
                winner = UserName.SOMSAK;
            }
        }else if (somchaiHaveFour){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveFour){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userFullHouse(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveFullHouse =  CheckRuleCard.getInstance().isFullHouse(_listcardSomchai);
        boolean somsakHaveFullHouse =  CheckRuleCard.getInstance().isFullHouse(_listcardSomsak);
        if (somchaiHaveFullHouse && somsakHaveFullHouse){
            int valuseSomchai = Integer.parseInt( CheckRuleCard.getInstance().getCardThree(_listcardSomchai).getScoreOfValue());
            int valuseSomsak = Integer.parseInt( CheckRuleCard.getInstance().getCardThree(_listcardSomsak).getScoreOfValue());
            if ( valuseSomchai > valuseSomsak){
                winner = UserName.SOMCHAI;
            } else{
                winner = UserName.SOMSAK;
            }
        }else if (somchaiHaveFullHouse){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveFullHouse){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userFlush(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveFlush =  CheckRuleCard.getInstance().isFlush(_listcardSomchai);
        boolean somsakHaveFlush  =  CheckRuleCard.getInstance().isFlush (_listcardSomsak);
        if (somchaiHaveFlush && somsakHaveFlush){
            winner = userHighCard(_listcardSomchai,_listcardSomsak);
        }else if (somchaiHaveFlush){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveFlush){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userStraight(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveStraight =  CheckRuleCard.getInstance().isStraight(_listcardSomchai);
        boolean somsakHaveStraight  =  CheckRuleCard.getInstance().isStraight (_listcardSomsak);
        if (somchaiHaveStraight && somsakHaveStraight){
            winner = userHighCard(_listcardSomchai,_listcardSomsak);
        }else if (somchaiHaveStraight){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveStraight){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userThree(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHaveThree =  CheckRuleCard.getInstance().isThree(_listcardSomchai);
        boolean somsakHaveThree  =  CheckRuleCard.getInstance().isThree (_listcardSomsak);
        if (somchaiHaveThree && somsakHaveThree){
            int valuseSomchai = Integer.parseInt( CheckRuleCard.getInstance().getCardThree(_listcardSomchai).getScoreOfValue());
            int valuseSomsak = Integer.parseInt( CheckRuleCard.getInstance().getCardThree(_listcardSomsak).getScoreOfValue());
            if ( valuseSomchai > valuseSomsak){
                winner = UserName.SOMCHAI;
            } else{
                winner = UserName.SOMSAK;
            }
        }else if (somchaiHaveThree){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveThree){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userTwoPairs(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        CheckRuleCard.getInstance().sortCard(_listcardSomchai);
        CheckRuleCard.getInstance().sortCard(_listcardSomsak);
        UserName winner;
        boolean somchaiHaveTwoPairs =  CheckRuleCard.getInstance().isTwoPairs(_listcardSomchai);
        boolean somsakHaveTwoPairs  =  CheckRuleCard.getInstance().isTwoPairs (_listcardSomsak);
        if (somchaiHaveTwoPairs && somsakHaveTwoPairs){

            int somchaiPairsHigh = Integer.parseInt(CheckRuleCard.getInstance().checkPair(_listcardSomchai).get(0).getScoreOfValue());
            int somchaiPairsLowes = Integer.parseInt(CheckRuleCard.getInstance().checkPair(_listcardSomchai).get(1).getScoreOfValue());
            int somchaiRemains = CheckRuleCard.getInstance().getRemainsCard(_listcardSomchai,somchaiPairsHigh,somchaiPairsLowes);

            int somsakPairsHigh = Integer.parseInt(CheckRuleCard.getInstance().checkPair(_listcardSomsak).get(0).getScoreOfValue());
            int somsakPairsLowes = Integer.parseInt(CheckRuleCard.getInstance().checkPair(_listcardSomsak).get(1).getScoreOfValue());
            int somsakRemains = CheckRuleCard.getInstance().getRemainsCard(_listcardSomsak,somsakPairsHigh,somsakPairsLowes);
            if (somchaiPairsHigh > somsakPairsHigh){
                winner = UserName.SOMCHAI;
            }else if (somsakPairsHigh >somchaiPairsHigh ){
                winner = UserName.SOMSAK;
            }else{
                if (somchaiPairsLowes > somsakPairsLowes){
                    winner = UserName.SOMCHAI;
                }else if (somsakPairsLowes >somchaiPairsLowes ){
                    winner = UserName.SOMSAK;
                }else{
                    if (somchaiRemains > somsakRemains){
                        winner = UserName.SOMCHAI;
                    }else if (somsakRemains >somchaiRemains ){
                        winner = UserName.SOMSAK;
                    }else{
                        winner = UserName.BOTH;
                    }
                }
            }

        }else if (somchaiHaveTwoPairs){
            winner = UserName.SOMCHAI;
        }else  if (somsakHaveTwoPairs){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userPair(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner;
        boolean somchaiHavePair =  CheckRuleCard.getInstance().isPair(_listcardSomchai);
        boolean somsakHavePair =  CheckRuleCard.getInstance().isPair (_listcardSomsak);
        if (somchaiHavePair && somsakHavePair){
            winner = userHighCard( CheckRuleCard.getInstance().getCardOuterPair(_listcardSomchai),CheckRuleCard.getInstance().getCardOuterPair(_listcardSomsak));
        }else if (somchaiHavePair){
            winner = UserName.SOMCHAI;
        }else  if (somsakHavePair){
            winner = UserName.SOMSAK;
        }else{
            winner = null;
        }
        return winner;
    }
    public UserName userHighCard(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        UserName winner = null;
        int i =0;
        while (i<_listcardSomchai.size()){
            int scoreOfValueSomchai = Integer.parseInt(_listcardSomchai.get(i).getScoreOfValue());
            int scoreOfValueSomsak = Integer.parseInt(_listcardSomsak.get(i).getScoreOfValue());
            if (scoreOfValueSomchai > scoreOfValueSomsak){
                winner = UserName.SOMCHAI;
                break;
            }else if (scoreOfValueSomsak > scoreOfValueSomchai){
                winner = UserName.SOMSAK;
                break;
            }else{
                winner = UserName.BOTH;
                i++;
            }
        }

        return winner;
    }
    public CardDetail getHighCard(ArrayList<CardDetail> _listcardSomchai,ArrayList<CardDetail> _listcardSomsak){
        CardDetail card = null;
        int i =0;
        while (i<_listcardSomchai.size()){
            int scoreOfValueSomchai = Integer.parseInt(_listcardSomchai.get(i).getScoreOfValue());
            int scoreOfValueSomsak = Integer.parseInt(_listcardSomsak.get(i).getScoreOfValue());
            if (scoreOfValueSomchai > scoreOfValueSomsak){
                card = _listcardSomchai.get(i);
                break;
            }else if (scoreOfValueSomsak > scoreOfValueSomchai){
                card = _listcardSomsak.get(i);
                break;
            }else{
                card = null;
                i++;
            }
        }

        return card;
    }
}
