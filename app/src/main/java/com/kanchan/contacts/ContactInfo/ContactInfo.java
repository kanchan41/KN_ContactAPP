package com.kanchan.contacts.ContactInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kanchan.contacts.R;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

import java.io.IOException;
import java.util.Random;

public class ContactInfo extends AppCompatActivity {

    TextView Name, phone;
    Button sendSms;
    private static final Random generator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
        sendSms = findViewById(R.id.sendsms);

        Intent i= getIntent();
        Name.setText(i.getStringExtra("Name"));
        phone.setText(i.getStringExtra("Phone"));


        NexmoClient client = new NexmoClient.Builder()
                .apiKey("f96a7847")
                .apiSecret("6pmUzjuOFEZBNTXR")
                .build();


        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* String messageText = "Hi Your OTP is" + generatePin();
                TextMessage message = new TextMessage("Kisan Network", "917019715926", messageText);

                SmsSubmissionResponse response;

                {
                    try {
                        response = client.getSmsClient().submitMessage(message);
                        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                            System.out.println("Messageis "+responseMessage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NexmoClientException e) {
                        e.printStackTrace();
                    }
                }*/


                try {
                TextMessage message = new TextMessage("Acme Inc", "7019715926", "A text message sent using the Nexmo SMS API");

                SmsSubmissionResponse response = null;

                    response = client.getSmsClient().submitMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (NexmoClientException e) {
                    e.printStackTrace();
                    Log.e("error",e.getMessage());
                }

//                for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
//                    System.out.println(responseMessage);
//                }


            }
        });

    }

    public static int generatePin() {
        return 100000 + generator.nextInt(900000);
    }
}
