package com.yjp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages.length > 0){
            String sender = messages[0].getOriginatingAddress(); // 발신 번호
            Log.d(TAG, "sender : " + sender);

            String contents = messages[0].getMessageBody();     // message 내용
            Log.d(TAG, "contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "receivedDate : " + receivedDate);

            // 읽은 내용을 다른 Activity 로 전달
            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receivedDate", new SimpleDateFormat("yyyy--MM-dd HH:mm").format(receivedDate));

        context.startActivity(intent);
    }

    // Intent 혹은 bundle을 이용해서 받은 정보를 뽑아 냄
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        // pdus 안에 sms에 관한 내용이 저장돼 있다고 함
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];

        for(int i = 0; i < objects.length; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objects[i], format);
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[])objects[i]);
            }
        }
        return messages;
    }

}
