package com.kanchan.contacts.ContactInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanchan.contacts.R;
import com.nexmo.client.HttpConfig;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.Random;

public class ContactInfo extends AppCompatActivity {

    TextView Name, phone;
    Button sendSms;
    String avtartURL;
    private static final Random generator = new Random();
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
        sendSms = findViewById(R.id.sendsms);
        avatar = findViewById(R.id.avatar);

        Intent i = getIntent();
        Name.setText(i.getStringExtra("Name"));
        phone.setText(i.getStringExtra("Phone"));
        avtartURL = i.getStringExtra("avtarURL");

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
                avatar.setImageDrawable(d);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                errorDrawable = getResources().getDrawable(R.drawable.ic_warning_black_24dp);
                avatar.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                placeHolderDrawable = getResources().getDrawable(R.drawable.loading);
                avatar.setImageDrawable(placeHolderDrawable);
            }
        };

        avatar.setTag(target);
        Picasso.with(ContactInfo.this)
                .load(avtartURL)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(target);


        HttpConfig httpConfig = HttpConfig.defaultConfig();

        NexmoClient client = new NexmoClient.Builder()
                .apiKey("f96a7847")
                .httpConfig(httpConfig)
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
                    TextMessage message = new TextMessage("Acme Inc", "917019715926", "A text message sent using the Nexmo SMS API");

                    SmsSubmissionResponse response = null;

                    response = client.getSmsClient().submitMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (NexmoClientException e) {
                    e.printStackTrace();
                    Log.e("error", e.getMessage());
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
