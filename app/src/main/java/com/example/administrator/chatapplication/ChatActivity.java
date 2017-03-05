package com.example.administrator.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    private TextView mUserMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mInputMessage = (EditText) findViewById(R.id.input_message);
        mSendMessage = (Button) findViewById(R.id.send_message);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
        mCpuMessage = (TextView) findViewById(R.id.cpu_message);
        mUserMessage = (TextView) findViewById(R.id.user_message);

        mSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mSendMessage)){
            String inputText = mInputMessage.getText().toString();
            String anwer;
            mUserMessage.setText(inputText);
            if(inputText.contains("hi")){
                anwer = "Hello";
            }else if(inputText.contains("tired")){
                anwer = "Take a rest.";
            }else if(inputText.contains("tl")){
                double random = Math.random()*5d;
                if(random<1d){
                    anwer = "too bad";
                }else if (random<2d){
                    anwer = "little bad";
                }else if(random<3d){
                    anwer = "not bad";
                }else if(random<4d){
                    anwer = "lucky";
                }else{
                    anwer = "hit the jackpot";
                }

            }else {
                anwer = "you are right";
            }
            mInputMessage.setText("");
            mCpuMessage.setText(anwer);
        }
    }
}
