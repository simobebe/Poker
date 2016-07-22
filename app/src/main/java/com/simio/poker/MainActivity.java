package com.simio.poker;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.simio.poker.manager.CheckWinner;
import com.simio.poker.model.CardDetail;
import com.simio.poker.manager.CheckRuleCard;
import com.simio.poker.model.UserName;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<CardDetail> listCardSomchai, listCardSomsak;
    ArrayList<String> mlistAllCard;
    String mCardDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listCardSomchai = new ArrayList<>();
        listCardSomsak = new ArrayList<>();
        mlistAllCard = new ArrayList<>();
        mlistAllCard = new ArrayList<>(setmcard(mlistAllCard));
    }


    public void onSelectCard(View view) {
        TextView tv;
        final UserName userName;
        switch (view.getId()) {
            case R.id.user1_card1:
                tv = (TextView) findViewById(R.id.user1_card1);
                userName = UserName.SOMCHAI;
                break;
            case R.id.user1_card2:
                tv = (TextView) findViewById(R.id.user1_card2);
                userName = UserName.SOMCHAI;
                break;
            case R.id.user1_card3:
                tv = (TextView) findViewById(R.id.user1_card3);
                userName = UserName.SOMCHAI;
                break;
            case R.id.user1_card4:
                tv = (TextView) findViewById(R.id.user1_card4);
                userName = UserName.SOMCHAI;
                break;
            case R.id.user1_card5:
                tv = (TextView) findViewById(R.id.user1_card5);
                userName = UserName.SOMCHAI;
                break;


            case R.id.user2_card1:
                tv = (TextView) findViewById(R.id.user2_card1);
                userName = UserName.SOMSAK;
                break;
            case R.id.user2_card2:
                tv = (TextView) findViewById(R.id.user2_card2);
                userName = UserName.SOMSAK;
                break;
            case R.id.user2_card3:
                tv = (TextView) findViewById(R.id.user2_card3);
                userName = UserName.SOMSAK;
                break;
            case R.id.user2_card4:
                tv = (TextView) findViewById(R.id.user2_card4);
                userName = UserName.SOMSAK;
                break;
            case R.id.user2_card5:
                tv = (TextView) findViewById(R.id.user2_card5);
                userName = UserName.SOMSAK;
                break;
            default:
                userName = null;
                tv = null;
        }
        if (tv != null && tv.getText().toString().length() < 3) return;

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.picker_dialog);
        dialog.setCancelable(true);
        WheelPicker wheelCard = (WheelPicker) dialog.findViewById(R.id.main_wheel_card);

        wheelCard.setData(mlistAllCard);
        wheelCard.setSelectedItemPosition(0);
        mCardDetail = String.valueOf(wheelCard.getData().get(0));
        wheelCard.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                mCardDetail = String.valueOf(data);
            }
        });
        Button btnOK = (Button) dialog.findViewById(R.id.main_button_ok);
        final TextView finalTv = tv;
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set TextView
                if (finalTv != null) finalTv.setText(mCardDetail);
                //Add card to user
                if (userName == UserName.SOMCHAI) {
                    listCardSomchai.add(new CardDetail(String.valueOf(mCardDetail.charAt(0)), String.valueOf(mCardDetail.charAt(1))));
                } else {
                    listCardSomsak.add(new CardDetail(String.valueOf(mCardDetail.charAt(0)), String.valueOf(mCardDetail.charAt(1))));
                }
                mlistAllCard.remove(mCardDetail);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private ArrayList<String> setmcard(ArrayList<String> _listcard) {
        for (int i = 2; i < 15; i++) {
            if (i == 10) {
                _listcard.add("TC");
                _listcard.add("TD");
                _listcard.add("TH");
                _listcard.add("TS");
            } else if (i == 11) {
                _listcard.add("JC");
                _listcard.add("JD");
                _listcard.add("JH");
                _listcard.add("JS");
            } else if (i == 12) {
                _listcard.add("QC");
                _listcard.add("QD");
                _listcard.add("QH");
                _listcard.add("QS");
            } else if (i == 13) {
                _listcard.add("KC");
                _listcard.add("KD");
                _listcard.add("KH");
                _listcard.add("KS");
            } else if (i == 14) {
                _listcard.add("AC");
                _listcard.add("AD");
                _listcard.add("AH");
                _listcard.add("AS");
            } else {
                _listcard.add(i + "C");
                _listcard.add(i + "D");
                _listcard.add(i + "H");
                _listcard.add(i + "S");
            }

        }
        return _listcard;
    }


    public void onOK(View view) {
        if (listCardSomchai.size() == 5 && listCardSomsak.size() ==5) {
            String cardSomchai = setCard(listCardSomchai, "Somchai");
            String cardSomsak = setCard(listCardSomsak, "Somsak");
            String result = resultCard(listCardSomchai,listCardSomsak);
            Intent intent = new Intent(MainActivity.this,SuccessActivity.class);
            intent.putExtra("CARD_SOMCHAI",cardSomchai);
            intent.putExtra("CARD_SOMSAK",cardSomsak);
            intent.putExtra("RESULT",result);
            startActivity(intent);
        } else {
            Toast.makeText(this, "CARD FAIL", Toast.LENGTH_SHORT).show();
        }
    }

    private String resultCard(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak){
        String result;
        if (userWinStraightFlush(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinStraightFlush(_listcardSomchai,_listcardSomsak);
        }else if (userWinFour(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinFour(_listcardSomchai,_listcardSomsak);
        }else if (userWinFullHouse(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinFullHouse(_listcardSomchai,_listcardSomsak);
        }else if (userWinFlush(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinFlush(_listcardSomchai,_listcardSomsak);
        }else if (userWinStraight(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinStraight(_listcardSomchai,_listcardSomsak);
        }else if (userWinThree(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinThree(_listcardSomchai,_listcardSomsak);
        }else if (userWinTwoPairs(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinTwoPairs(_listcardSomchai,_listcardSomsak);
        }else if (userWinPair(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinPair(_listcardSomchai,_listcardSomsak);
        }else if (userWinHighCard(_listcardSomchai,_listcardSomsak)!=(null)){
            result = userWinHighCard(_listcardSomchai,_listcardSomsak);
        }else{
            result = null;
        }
        return result;
    }

    private String userWinStraightFlush(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinStraightFlush = CheckWinner.getInstance().userStraightFlush(_listcardSomchai, _listcardSomsak);
        if (userWinStraightFlush == UserName.BOTH) {
            result = "Tie";
        } else if (userWinStraightFlush == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with StraightFlush";
        } else if (userWinStraightFlush == UserName.SOMSAK) {
            result = "SOMSAK wins. - with StraightFlush";

        } else {
            result = null;
        }
        return result;
    }

    private String userWinFour(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinFour = CheckWinner.getInstance().userFour(_listcardSomchai, _listcardSomsak);
        if (userWinFour == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with Four";
        } else if (userWinFour == UserName.SOMSAK) {
            result = "SOMSAK wins. - with Four";
        } else {
            result = null;
        }
        return result;
    }

    private String userWinFullHouse(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinFullHouse = CheckWinner.getInstance().userFullHouse(_listcardSomchai, _listcardSomsak);
        if (userWinFullHouse == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with FullHouse";
        } else if (userWinFullHouse == UserName.SOMSAK) {
            result = "SOMSAK wins. - with FullHouse";
        } else {
            result = null;
        }
        return result;
    }

    private String userWinFlush(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinFlush = CheckWinner.getInstance().userFlush(_listcardSomchai, _listcardSomsak);
        if (userWinFlush == UserName.BOTH) {
            result = "Tie";
        } else if (userWinFlush == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with Flush ";
        } else if (userWinFlush == UserName.SOMSAK) {
            result = "SOMSAK wins. - with Flush ";

        } else {
            result = null;
        }
        return result;
    }
    private String userWinStraight(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinStraight = CheckWinner.getInstance().userStraight(_listcardSomchai, _listcardSomsak);
        if (userWinStraight == UserName.BOTH) {
            result = "Tie";
        } else if (userWinStraight == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with Straight ";
        } else if (userWinStraight == UserName.SOMSAK) {
            result = "SOMSAK wins. - with Straight ";

        } else {
            result = null;
        }
        return result;
    }

    private String userWinThree(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinThree = CheckWinner.getInstance().userThree(_listcardSomchai, _listcardSomsak);
        if (userWinThree == UserName.BOTH) {
            result = "Tie";
        } else if (userWinThree == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with Three ";
        } else if (userWinThree == UserName.SOMSAK) {
            result = "SOMSAK wins. - with Three ";

        } else {
            result = null;
        }
        return result;
    }

    private String userWinTwoPairs(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinTwoPairs = CheckWinner.getInstance().userTwoPairs(_listcardSomchai, _listcardSomsak);
        if (userWinTwoPairs == UserName.BOTH) {
            result = "Tie";
        } else if (userWinTwoPairs == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with TwoPairs ";
        } else if (userWinTwoPairs == UserName.SOMSAK) {
            result = "SOMSAK wins. - with TwoPairs ";

        } else {
            result = null;
        }
        return result;
    }
    private String userWinPair(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinPair = CheckWinner.getInstance().userPair(_listcardSomchai, _listcardSomsak);
        if (userWinPair == UserName.BOTH) {
            result = "Tie";
        } else if (userWinPair == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with Pair ";
        } else if (userWinPair == UserName.SOMSAK) {
            result = "SOMSAK wins. - with Pair ";

        } else {
            result = null;
        }
        return result;
    }

    private String userWinHighCard(ArrayList<CardDetail> _listcardSomchai, ArrayList<CardDetail> _listcardSomsak) {
        String result ;
        UserName userWinHighCard = CheckWinner.getInstance().userHighCard(_listcardSomchai, _listcardSomsak);
        if (userWinHighCard == UserName.BOTH) {
            result = "Tie";
        } else if (userWinHighCard == UserName.SOMCHAI) {
            result = "SOMCHAI wins. - with HighCard "+CheckWinner.getInstance().getHighCard(_listcardSomchai, _listcardSomsak).getName() ;
        } else if (userWinHighCard == UserName.SOMSAK) {
            result = "SOMSAK wins. - with HighCard "+CheckWinner.getInstance().getHighCard(_listcardSomchai, _listcardSomsak).getName();

        } else {
            result = null;
        }
        return result;
    }
    private String setCard(ArrayList<CardDetail> _listcard, String _name) {
        CheckRuleCard.getInstance().sortCard(_listcard);
        String card = _name + " : ";
        for (CardDetail carddetail : _listcard) {
            card = card + " " + carddetail.getValue() + carddetail.getSuit();
        }
        return card;
    }


}
