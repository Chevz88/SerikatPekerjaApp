package com.serikatpekerja.nirwanalestari.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> messageList;

    public ChatAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage msg = messageList.get(position);

        if (msg.isUser()) {
            holder.txtUser.setVisibility(View.VISIBLE);
            holder.txtBot.setVisibility(View.GONE);
            holder.txtUser.setText(msg.getMessage());
        } else {
            holder.txtBot.setVisibility(View.VISIBLE);
            holder.txtUser.setVisibility(View.GONE);
            holder.txtBot.setText(msg.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtUser, txtBot;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUserMessage);
            txtBot = itemView.findViewById(R.id.txtBotMessage);
        }
    }
}
