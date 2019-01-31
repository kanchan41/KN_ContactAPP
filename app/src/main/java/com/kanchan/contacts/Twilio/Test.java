package com.kanchan.contacts.Twilio;


import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

import java.io.IOException;
import java.util.Random;

public class Test {

    NexmoClient client = new NexmoClient.Builder()
            .apiKey("f96a7847")
            .apiSecret("6pmUzjuOFEZBNTXR")
            .build();

    String messageText = "Hi Your OTP is" + generatePin();
    TextMessage message = new TextMessage("Kisan Network", "917019715926", messageText);

    SmsSubmissionResponse response;

    {
        try {
            response = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                System.out.println(responseMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }
    }

    private static final Random generator = new Random();

    public static int generatePin() {
        return 100000 + generator.nextInt(900000);
    }


}
