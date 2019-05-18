package com.tjeit.baseballgame01;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tjeit.baseballgame01.databinding.ActivityMainBinding;
import com.tjeit.baseballgame01.datas.Chat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    int[] computerExamArray = new int[3];

    List<Chat> chatList = new ArrayList<>();

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
                chatList.add(new Chat(true, act.userInputEdt.getText().toString()));
                checkStrikeAndBalls();
            }
        });
    }

    void checkStrikeAndBalls() {
        int[] userInputArray = new int[3];
        int number = Integer.parseInt(act.userInputEdt.getText().toString());

        userInputArray[0] = number / 100;
        userInputArray[1] = number / 10 % 10;
        userInputArray[2] = number % 10;

        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (userInputArray[i] == computerExamArray[j]) {
                    if (i == j) {
//                        숫자, 위치 모두 같음 (S:스트라이크)
                        strikeCount++;
                    } else {
                        ballCount++;
                    }
                }
            }
        }

        if (strikeCount == 3) {
            Toast.makeText(mContext, "정답입니다! 축하합니다!", Toast.LENGTH_SHORT).show();
            chatList.add(new Chat(false, "정답입니다! 축하합니다!"));
        } else {
            Toast.makeText(mContext, String.format("%dS, %dB", strikeCount, ballCount), Toast.LENGTH_SHORT).show();
            chatList.add(new Chat(false, String.format("%dS, %dB",strikeCount, ballCount)));
        }
    }

    @Override
    public void setValues() {
        makeExam();
    }

    //    게임을 만든다 (문제 숫자 3개 생성)
    void makeExam() {
        while (true) {

            int randomNumber = (int) (Math.random() * 899 + 100);

            int[] tempNumber = new int[3];

            tempNumber[0] = randomNumber / 100; //정수만 나옴 (예: 892 -> 8.92 에서 .92 버림 8만..)
            tempNumber[1] = randomNumber / 10 % 10;
            tempNumber[2] = randomNumber % 10;

            boolean isDuplOk = true;

            if (tempNumber[0] == tempNumber[1] ||
                    tempNumber[1] == tempNumber[2] ||
                    tempNumber[2] == tempNumber[0]) {
//            중복이면 다시 ..
                isDuplOk = false;
            }

            boolean isZeroOk = true;
            if (tempNumber[0] == 0 || tempNumber[1] == 1 || tempNumber[2] == 2) {
//            중복이면 다시 ..
                isZeroOk = false;
            }

            if (isDuplOk && isZeroOk) {
                computerExamArray[0] = tempNumber[0];
                computerExamArray[1] = tempNumber[1];
                computerExamArray[2] = tempNumber[2];


                Log.d("정답 숫자", randomNumber + " 입니다.");
                break;
            }

        }
    }


    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
