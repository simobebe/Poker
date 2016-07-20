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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ArrayList<CardDetail> listAllCard;
    ArrayList<String> mlistAllCard;
//    List<String> lSuit;
//    List<Integer> lValue;
    CardDetail mCarddetail;
    String mCardDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAllCard = new ArrayList<>();
        mlistAllCard = new ArrayList<>();
        listAllCard.addAll(setcard(listAllCard));
        mlistAllCard.addAll(setmcard(mlistAllCard));
//        lSuit = new ArrayList<>();
//        lSuit.add("C");
//        lSuit.add("D");
//        lSuit.add("H");
//        lSuit.add("S");
//        lValue = new ArrayList<>();
//        for (int i =1 ; i <53 ; i++){
//            lValue.add(""+i);
//        }
    }
    public void test(View view) {
//        final Dialog dialog = new Dialog(MainActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.picker_dialog);
//        dialog.setCancelable(true);
//        WheelPicker wheelValue = (WheelPicker) dialog.findViewById(R.id.main_wheel_value);
//        final WheelPicker wheelSuit = (WheelPicker) dialog.findViewById(R.id.main_wheel_suit);
//        wheelValue.setData(lValue);
//        wheelValue.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelPicker picker, Object data, int position) {
////                Toast.makeText(MainActivity.this, String.valueOf(data), Toast.LENGTH_SHORT).show();
//                value = String.valueOf(data);
//            }
//        });
//
//        wheelSuit.setData(lSuit);
//        wheelSuit.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelPicker picker, Object data, int position) {
////                Toast.makeText(MainActivity.this, String.valueOf(data), Toast.LENGTH_SHORT).show();
//                suit = String.valueOf(data);
//            }
//        });
//        Button btnOK = (Button)dialog.findViewById(R.id.main_button_ok);
//        btnOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "You select card :"+value+suit, Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        dialog.show();

    }

    public void onSelectCard(View view) {
        TextView tv;
        switch (view.getId()){
            case R.id.user1_card1 : tv =(TextView)findViewById(R.id.user1_card1); break;
            case R.id.user1_card2 : tv =(TextView)findViewById(R.id.user1_card2); break;
            case R.id.user1_card3 : tv =(TextView)findViewById(R.id.user1_card3); break;
            case R.id.user1_card4 : tv =(TextView)findViewById(R.id.user1_card4); break;
            case R.id.user1_card5 : tv =(TextView)findViewById(R.id.user1_card5); break;


            case R.id.user2_card1 : tv =(TextView)findViewById(R.id.user2_card1); break;
            case R.id.user2_card2 : tv =(TextView)findViewById(R.id.user2_card2); break;
            case R.id.user2_card3 : tv =(TextView)findViewById(R.id.user2_card3); break;
            case R.id.user2_card4 : tv =(TextView)findViewById(R.id.user2_card4); break;
            case R.id.user2_card5 : tv =(TextView)findViewById(R.id.user2_card5); break;
                default: tv =null;
        }
        if (tv!=null&&tv.getText().toString().length()<3)return;

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.picker_dialog);
        dialog.setCancelable(true);
        WheelPicker wheelValue = (WheelPicker) dialog.findViewById(R.id.main_wheel_value);
        WheelPicker wheelSuit = (WheelPicker) dialog.findViewById(R.id.main_wheel_suit);
        wheelValue.setData(listAllCard);
        wheelValue.setSelectedItemPosition(0);
        mCarddetail = (CardDetail) wheelValue.getData().get(0) ;
        wheelValue.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                mCarddetail = (CardDetail)data;
            }
        });

        wheelSuit.setData(mlistAllCard);
        wheelSuit.setSelectedItemPosition(0);
        mCardDetail = String.valueOf(wheelSuit.getData().get(0)) ;
        wheelSuit.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                mCardDetail = String.valueOf(data) ;
            }
        });
        Button btnOK = (Button)dialog.findViewById(R.id.main_button_ok);
        final TextView finalTv = tv;
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (finalTv!=null) finalTv.setText( ""+mCarddetail.getValue()+mCarddetail.getSuit());
                if (finalTv!=null) finalTv.setText( mCardDetail);
                mlistAllCard.remove(mCardDetail);
//                Toast.makeText(MainActivity.this, "You select card :"+value+"-"+suit, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private ArrayList<CardDetail> setcard(ArrayList<CardDetail> _listcard ){
        for (int i=1;i<15;i++){
            if (i==10){
                _listcard.add(new CardDetail("T","C"));
                _listcard.add(new CardDetail("T","D"));
                _listcard.add(new CardDetail("T","H"));
                _listcard.add(new CardDetail("T","S"));
            }else if (i == 11){
                _listcard.add(new CardDetail("J","C"));
                _listcard.add(new CardDetail("J","D"));
                _listcard.add(new CardDetail("J","H"));
                _listcard.add(new CardDetail("J","S"));
            }else if (i == 12){
                _listcard.add(new CardDetail("Q","C"));
                _listcard.add(new CardDetail("Q","D"));
                _listcard.add(new CardDetail("Q","H"));
                _listcard.add(new CardDetail("Q","S"));
            }else if (i == 13){
                _listcard.add(new CardDetail("K","C"));
                _listcard.add(new CardDetail("K","D"));
                _listcard.add(new CardDetail("K","H"));
                _listcard.add(new CardDetail("K","S"));
            }else if (i == 14){
                _listcard.add(new CardDetail("A","C"));
                _listcard.add(new CardDetail("A","D"));
                _listcard.add(new CardDetail("A","H"));
                _listcard.add(new CardDetail("A","S"));
            }else{
                _listcard.add(new CardDetail(""+i,"C"));
                _listcard.add(new CardDetail(""+i,"D"));
                _listcard.add(new CardDetail(""+i,"H"));
                _listcard.add(new CardDetail(""+i,"S"));
            }

        }
        return _listcard;
    }
    private ArrayList<String> setmcard(ArrayList<String> _listcard ){
        for (int i=1;i<15;i++){
            if (i==10){
                _listcard.add("TC");
                _listcard.add("TD");
                _listcard.add("TH");
                _listcard.add("TS");
            }else if (i == 11){
                _listcard.add("JC");
                _listcard.add("JD");
                _listcard.add("JH");
                _listcard.add("JS");
            }else if (i == 12){
                _listcard.add("QC");
                _listcard.add("QD");
                _listcard.add("QH");
                _listcard.add("QS");
            }else if (i == 13){
                _listcard.add("KC");
                _listcard.add("KD");
                _listcard.add("KH");
                _listcard.add("KS");
            }else if (i == 14){
                _listcard.add("AC");
                _listcard.add("AD");
                _listcard.add("AH");
                _listcard.add("AS");
            }else{
                _listcard.add(i+"C");
                _listcard.add(i+"D");
                _listcard.add(i+"H");
                _listcard.add(i+"S");
            }

        }
        return _listcard;
    }
}
