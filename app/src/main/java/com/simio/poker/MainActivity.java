package com.simio.poker;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.simio.poker.model.CardDetail;
import com.simio.poker.manager.ProcessCard;
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
        mlistAllCard.addAll(setmcard(mlistAllCard));
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
        for (int i = 1; i < 15; i++) {
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
        if (listCardSomchai.size() == 5 ) {
           boolean straight_flush= ProcessCard.getInstance().isStraightFlush(listCardSomchai);
//            boolean four = new ProcessCard().isFour(listCardSomchai);
//            boolean full_house = new ProcessCard().isFour(listCardSomchai);
//            boolean flush = new ProcessCard().isFlush(listCardSomchai);
//            boolean straight = new ProcessCard().isStraight(listCardSomchai);
//            boolean three = new ProcessCard().isThree(listCardSomchai);
//            boolean two_pairs = new ProcessCard().isTwoPairs(listCardSomchai);
//            boolean pair = ProcessCard.getInstance().isPair(listCardSomchai);
            Toast.makeText(this, "CARD SUCCESS \n listCardSomchai is straight_flush :" + straight_flush, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "CARD FAIL", Toast.LENGTH_SHORT).show();
        }
    }


}
