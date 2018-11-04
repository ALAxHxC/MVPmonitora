package ponny.org.monitora.views.common.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.listas.InboxRecyclerViewAdapter;
import ponny.org.monitora.views.common.listas.MessagesRecyclerViewAdapter;

public class InboxDetails extends AppCompatActivity {
    private Inbox message;
    private RecyclerView inbox_messages;
    private OnListFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        message = (Inbox) getIntent().getSerializableExtra("Inbox");
        inbox_messages = (RecyclerView) findViewById(R.id.inbox_messages);
        loadInbox();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void loadInbox() {
        if (message.getMessages().size() <= 1) {
            inbox_messages.setLayoutManager(new LinearLayoutManager(this));
        } else {
            inbox_messages.setLayoutManager(new GridLayoutManager(this, 1));
        }
        inbox_messages.setAdapter(new InboxRecyclerViewAdapter(message.getMessages()));

    }

}

