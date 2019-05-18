package com.tjeit.baseballgame01;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tjeit.baseballgame01.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    int[] computerExamArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        act.inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setValues() {

    }

//    게임을 만든다 (문제 숫자 3개 생성)
    void makeExam() {
        while (true) {

            int randomNumber = (int)(Math.random() * 899 + 100);

            int [] tempNumber = new int[3];

            tempNumber[0] = randomNumber / 100; //정수만 나옴 (예: 892 -> 8.92 에서 .92 버림 8만..)
            tempNumber[1] = randomNumber / 10 % 10;
            tempNumber[2] = randomNumber % 10;

            boolean isDuplOk = true;

            if(tempNumber[0] == tempNumber[1] ||
                    tempNumber[1] == tempNumber[2] ||
                    tempNumber[2] == tempNumber[0]) {
//            중복이면 다시 ..
                isDuplOk = false;
            }

            boolean isZeroOk = true;
            if(tempNumber[0] == 0 || tempNumber[1] == 1 || tempNumber[2] == 2) {
//            중복이면 다시 ..
                isZeroOk = false;
            }

            if(isDuplOk && isZeroOk) {
                computerExamArray[0] = tempNumber[0];
                computerExamArray[1] = tempNumber[1];
                computerExamArray[2] = tempNumber[2];
            }

        }




    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
