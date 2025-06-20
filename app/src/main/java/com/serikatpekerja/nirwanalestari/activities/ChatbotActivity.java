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

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String msg = edtMessage.getText().toString().trim();
        if (msg.isEmpty()) return;

        messageList.add(new ChatMessage(msg, true)); // user message
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerChat.scrollToPosition(messageList.size() - 1);
        edtMessage.setText("");

        // Simulasi balasan bot
        String reply = getBotReply(msg);
        messageList.add(new ChatMessage(reply, false)); // bot message
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerChat.scrollToPosition(messageList.size() - 1);
    }

    private String getBotReply(String msg) {
        // Placeholder: bisa nanti diganti RAG / OpenAI
        if (msg.toLowerCase().contains("upah")) {
            return "Upah minimum ditentukan sesuai UMR daerah berdasarkan ketentuan Kemnaker.";
        } else if (msg.toLowerCase().contains("hak")) {
            return "Setiap pekerja memiliki hak atas jaminan sosial, perlindungan, dan berserikat.";
        } else {
            return "Terima kasih atas pertanyaannya. Silakan tunggu jawaban lebih lanjut dari serikat.";
        }
    }
}
