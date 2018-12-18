package com.yjp.broadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText editText, editText4, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText = findViewById(R.id.editText2);
        editText4 = findViewById(R.id.editText4);
        editText3 = findViewById(R.id.editText3);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent passedIntent) {
        if(passedIntent != null){
            String sender = passedIntent.getStringExtra("sender");
            String contents = passedIntent.getStringExtra("contents");
            String receivedDate = passedIntent.getStringExtra("receivedDate");

            editText.setText(sender);
            editText4.setText(contents);
            editText3.setText(receivedDate);
        }
    }
}
