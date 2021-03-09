package com.construction.chatrecyclerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    List<ChatModelData> chatData = new ArrayList<>();
    static int sender = 0;
    static int receiver = 1;
    static int senderImg = 2;
    static int receiverImg = 3;
    int msgCount = 0;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());

        View view;

        if(viewType == sender) {
            view = inflater.inflate(R.layout.self_text,parent,false);
            return new SenderHolder(view);
        }
        else if(viewType == senderImg) {
            view = inflater.inflate(R.layout.sender_image,parent,false);
            return new SenderImgHolder(view);
        }
        else if(viewType == receiverImg)  {
            view = inflater.inflate(R.layout.receiver_image,parent,false);
            return new ReceiverImgHolder(view);
        }
        else  {
            view = inflater.inflate(R.layout.other_text,parent,false);
            return new ReceiverHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof SenderHolder) {
            ((SenderHolder) holder).msg.setText(chatData.get(position).getMessage());
        }

        else if(holder instanceof SenderImgHolder) {
            ((SenderImgHolder) holder).img.setImageResource(R.drawable.nike);
        }

        else if(holder instanceof ReceiverImgHolder) {
            ((ReceiverImgHolder) holder).img.setImageResource(R.drawable.nike);
        }

        else {
            ((ReceiverHolder) holder).msg.setText(chatData.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatModelData currentChat = chatData.get(position);
        if (currentChat.isSender() && currentChat.isImage())
            return senderImg;
        else if (currentChat.isSender() && !currentChat.isImage())
            return sender;
        else if (!currentChat.isSender() && !currentChat.isImage())
            return receiver;
        else if (!currentChat.isSender() && currentChat.isImage())
            return receiverImg;

        return -1;
    }

    void addItems(List<ChatModelData> data) {
        msgCount = chatData.size();
        chatData.addAll(data);
        notifyItemRangeInserted(msgCount,msgCount+data.size());
    }

    class SenderHolder extends RecyclerView.ViewHolder {
        TextView msg;
        public SenderHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.self_msg);
        }
    }

    class ReceiverHolder extends RecyclerView.ViewHolder {
        TextView msg;
        public ReceiverHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.other_msg);
        }
    }


    class SenderImgHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public SenderImgHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

    class ReceiverImgHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ReceiverImgHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }



}
