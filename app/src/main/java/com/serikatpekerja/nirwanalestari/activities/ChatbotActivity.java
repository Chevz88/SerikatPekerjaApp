package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.adapters.ChatAdapter;
import com.serikatpekerja.nirwanalestari.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {

    RecyclerView recyclerChat;
    EditText edtMessage;
    Button btnSend;
    ChatAdapter chatAdapter;
    List<ChatMessage> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        recyclerChat = findViewById(R.id.recyclerChat);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(chatAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtMessage.getText().toString().trim();
                if (!msg.isEmpty()) {
                    messageList.add(new ChatMessage(msg, true)); // user message
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                    edtMessage.setText("");

                    // Simulasi jawaban bot
                    String reply = "Terima kasih. Pertanyaan Anda akan segera diproses.";
                    messageList.add(new ChatMessage(reply, false));
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                }
            }
        });
    }
}
