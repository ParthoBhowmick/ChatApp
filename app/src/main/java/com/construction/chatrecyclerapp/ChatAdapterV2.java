package com.construction.chatrecyclerapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapterV2 extends RecyclerView.Adapter {

    List<ChatModelData> chatData = new ArrayList<>();
    static int text_msg = 0;
    static int image_msg = 1;
    int msgCount = 0;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());

        View view;

        if(viewType == text_msg) {
            view = inflater.inflate(R.layout.text_layout,parent,false);
            return new MessageHolder(view);
        }
        else  {
            view = inflater.inflate(R.layout.image_layout,parent,false);
            return new ImageHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MessageHolder) {
            ((MessageHolder) holder).msg.setText(chatData.get(position).getMessage());
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)((MessageHolder) holder).layout.getLayoutParams();

            if(chatData.get(position).isSender()) {
                ((MessageHolder) holder).layout.setBackgroundResource(R.drawable.outgoing_msg_bubble);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            else  {
                ((MessageHolder) holder).layout.setBackgroundResource(R.drawable.incomming_msg_bubble);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            ((MessageHolder) holder).layout.setLayoutParams(params);
        }

        else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)((ImageHolder) holder).layout.getLayoutParams();
            if(chatData.get(position).isSender()) {
               // ((ImageHolder) holder).layout.setBackgroundResource(R.drawable.outgoing_msg_bubble);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            else  {
                //((ImageHolder) holder).layout.setBackgroundResource(R.drawable.incomming_msg_bubble);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            ((ImageHolder) holder).layout.setLayoutParams(params);

            ((ImageHolder) holder).img.setImageResource(R.drawable.nike);

        }

    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatModelData currentChat = chatData.get(position);

        if (currentChat.isImage) {
            return image_msg;
        } else {
            return text_msg;
        }
    }

    void addItems(List<ChatModelData> data) {
        msgCount = chatData.size();
        chatData.addAll(data);
        notifyItemRangeInserted(msgCount,msgCount+data.size());
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        TextView msg;
        LinearLayout layout;
        public MessageHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.text_msg);
            layout = itemView.findViewById(R.id.text_holder);

        }
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView img;
        public ImageHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.image_holder);
            img = itemView.findViewById(R.id.img);

        }
    }


}
