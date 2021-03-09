package com.construction.chatrecyclerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView chatHistoryView;
    ChatAdapter adapter;
    EditText msg;
    Button submit;
    ArrayList<ChatModelData> modelData =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatHistoryView = findViewById(R.id.conversation);
        adapter = new ChatAdapter();
        chatHistoryView.setLayoutManager(new LinearLayoutManager(this));
        chatHistoryView.setAdapter(adapter);

        msg = findViewById(R.id.msg_et);
        submit = findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelData.clear();
                modelData.add(new ChatModelData(true,false,msg.getText().toString()));
                adapter.addItems(modelData);
                getFromReceiver();
            }
        });

    }

    void getFromReceiver() {

        modelData.clear();

        modelData.add(new ChatModelData(false, true,""));

        modelData.add(new ChatModelData(false,false,"Text msg2 ! Text msg2 ! Text msg2 ! Text msg2 ! Text msg2 ! Text msg2 !"));

        modelData.add(new ChatModelData(true, true,""));

        adapter.addItems(modelData);
        chatHistoryView.scrollToPosition(adapter.getItemCount() - 1);

    }


}