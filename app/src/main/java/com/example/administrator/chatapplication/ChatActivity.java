package com.example.administrator.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    //private TextView mUserMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mInputMessage = (EditText) findViewById(R.id.input_message);
        mSendMessage = (Button) findViewById(R.id.send_message);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
        mCpuMessage = (TextView) findViewById(R.id.cpu_message);
        //mUserMessage = (TextView) findViewById(R.id.user_message);




    }

    public void sendMessage(View v) {
        String inputText = mInputMessage.getText().toString();
        String answer;

        TextView userMessage = new TextView(this);

        int messageColor = getResources().getColor(R.color.message_color);
        userMessage.setTextColor(messageColor);
        userMessage.setBackgroundResource(R.drawable.user_message);
        userMessage.setText(inputText);
        //userMessage.setGravity(Gravity.END); // 오른쪽 맞춤

        LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        userMessageLayout.gravity = Gravity.END;

        final int marginSize = getResources().getDimensionPixelSize(R.dimen.message_margin); // 간격 설정


        mMessageLog.addView(userMessage,0,userMessageLayout); //TextView를 View의 맨 위로 설정
        //mUserMessage.setText(inputText);
        if (inputText.contains("hi")) {
            answer = "Hello";
        } else if (inputText.contains("tired")) {
            answer = "Take a rest.";
        } else if (inputText.contains("today is")) {
            double random = Math.random() * 5.1d;
            if (random < 1d) {
                answer = "too bad";
            } else if (random < 2d) {
                answer = "little bad";
            } else if (random < 3d) {
                answer = "not bad";
            } else if (random < 4d) {
                answer = "lucky";
            } else if (random < 5d) {
                answer = "jackpot";
            } else {
                answer = "perfact day";
            }

        } else if (inputText.contains("time")) {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            answer = String.format("Current time is %1$d hour %2$d min %3$d sec", hour, minute, second);
        } else {
            answer = "you are right";
        }
        //mInputMessage.setText("");

        final TextView cpuMessage = new TextView(this);
        cpuMessage.setTextColor(messageColor);

        cpuMessage.setBackgroundResource(R.drawable.cpu_message);
        cpuMessage.setText(answer);
        cpuMessage.setGravity(Gravity.START);
        mInputMessage.setText("");
        TranslateAnimation userMessageAnimation = new TranslateAnimation(mCpuMessage.getWidth(),0,0,0);
        userMessageAnimation.setDuration(1000);
        userMessageAnimation.setAnimationListener(new Animation.AnimationListener(){
            //클래스 이름이 선언되지 않은 내부 클래스를 익명 내부 클래스 라고 한다.

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                cpuMessageLayout.gravity = Gravity.START;
                // 간격 설정
                cpuMessageLayout.setMargins(marginSize,marginSize,marginSize,marginSize);



                mMessageLog.addView(cpuMessage,0,cpuMessageLayout);
                TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                cpuAnimation.setDuration(1000);
                cpuMessage.setAnimation(cpuAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        userMessage.startAnimation(userMessageAnimation);
        //mMessageLog.addView(cpuMessage,0);
        //mCpuMessage.setText(answer);
    }
}
