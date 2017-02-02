package com.youtube.sorcjc.sga_mobile.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Message;
import com.youtube.sorcjc.sga_mobile.ui.Global;
import com.youtube.sorcjc.sga_mobile.ui.adapter.MessageAdapter;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    private FirebaseDatabase database;

    private EditText etDescription;

    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        database = FirebaseDatabase.getInstance();
        setupRecyclerView();

        getChatMessages();
    }

    private void getChatMessages() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messages");
        myRef.addValueEventListener(this);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        etDescription = (EditText) findViewById(R.id.etDescription);
        ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                final String description = etDescription.getText().toString().trim();
                if (description.isEmpty())
                    return;

                final String name = "Juan";
                // final String name = Global.getFromSharedPreferences(this, "name");
                // if (name.isEmpty())
                //     return;

                //
                final String messageKey = database.getReference("messages").push().getKey();

                Message newMessage = new Message();
                newMessage.setContent(description);
                newMessage.setName(name);

                database.getReference("messages/"+messageKey).setValue(newMessage);
                etDescription.setText("");

                Global.hideKeyBoard(this);
                break;
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ArrayList<Message> messages = new ArrayList<>();

        for (DataSnapshot messageSnap : dataSnapshot.getChildren()) {
            Message message = messageSnap.getValue(Message.class);

            // final String questionKey = messageSnap.getKey();
            // message.setKey(questionKey);

            messages.add(message);
        }

        messageAdapter.setDataSet(messages);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.w("Test/ChatActivity", "Failed to read value", databaseError.toException());
    }
}
