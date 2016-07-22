package com.simio.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    private TextView tvCardSomchai;
    private TextView tvCardSomsak;
    private TextView tvResult;

    String mCardSomchai;
    String mCardSomsak;
    String mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Intent intent = getIntent();
        mCardSomchai = intent.getStringExtra("CARD_SOMCHAI");
        mCardSomsak = intent.getStringExtra("CARD_SOMSAK");
        mResult = intent.getStringExtra("RESULT");
        initailUI();
        setupUI();
    }

    private void setupUI() {
        tvCardSomchai.setText(mCardSomchai);
        tvCardSomsak.setText(mCardSomsak);
        tvResult.setText(mResult);
    }

    private void initailUI() {
        tvCardSomchai = (TextView)findViewById(R.id.card_somchai);
        tvCardSomsak = (TextView)findViewById(R.id.card_somsak);
        tvResult = (TextView)findViewById(R.id.result);
    }

    public void onDone(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
