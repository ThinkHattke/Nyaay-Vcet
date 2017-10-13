package com.gaurav.nyaay_vect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import static android.R.attr.action;

public class ChatBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        final ConversationService myCoversationServices = new ConversationService(                  //Initializing Chat-Bot
                "2017-05-26",
                getString(R.string.username),
                getString(R.string.password)
        );

        final TextView conversation = (TextView) findViewById(R.id.coversation);
        final EditText userInput = (EditText) findViewById(R.id.user_input);

        userInput.setOnEditorActionListener(new TextView
                .OnEditorActionListener() {                                                         //Invoking on send request after the message is provided
            @Override
            public boolean onEditorAction(TextView tv, int i, KeyEvent keyEvent) {                  //Constructor for the same

                if(EditorInfo.IME_ACTION_DONE == i) {

                    Log.i("Hits","Hit hooja");

                    final String inputText = userInput.getText().toString();
                    conversation.append(
                            android.text.Html.fromHtml("<p><b>You:<b> " + inputText + "</p>")       //Extracting the User input
                    );

                    userInput.setText("");                                                       //Resetting the input area

                    MessageRequest request = new MessageRequest.Builder()                           //Starting connection with IBM Waston and passing the input.
                            .inputText(inputText)
                            .build();

                    myCoversationServices
                            .message(getString(R.string.workspace), request)                        //Sending and receiving using Onresponse function.
                            .enqueue(new ServiceCallback<MessageResponse>() {
                                @Override
                                public void onResponse(MessageResponse response) {

                                    final String outputText = response.getText().get(0);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            conversation.append(
                                                    android.text.Html.fromHtml("<p><b>Bot:</b> " + outputText + "</p")    //Displaying the ouyput to user.
                                            );
                                        }
                                    });

                                }

                                @Override
                                public void onFailure(Exception e) {}
                            });

                }

                return false;

            }
        });

    }
}
