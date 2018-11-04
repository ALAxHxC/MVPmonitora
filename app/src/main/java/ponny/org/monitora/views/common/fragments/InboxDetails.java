package ponny.org.monitora.views.common.fragments;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.dialogs.SendResponseDialogFargment;
import ponny.org.monitora.views.common.listas.InboxRecyclerViewAdapter;

public class InboxDetails extends AppCompatActivity {
    private Inbox message;
    private RecyclerView inbox_messages;
    private OnListFragmentInteractionListener mListener;
    private SendResponseDialogFargment sendResponseDialogFargment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        message = (Inbox) getIntent().getSerializableExtra("Inbox");
        Log.println(Log.ASSERT, "prueba", "Tamaño de mensajes" + message.getMessages().size() + "");
        inbox_messages = (RecyclerView) findViewById(R.id.inbox_messages);
        fragmentManager = getSupportFragmentManager();
        loadInbox();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Fragment prev = fragmentManager.findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                sendResponseDialogFargment = SendResponseDialogFargment.newInstance(message, new InboxDetails.ObseverData());
                sendResponseDialogFargment.show(ft, "Test");
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }
    private class ObseverData implements java.util.Observer {
        @Override
        public void update(Observable o, Object arg) {
            if ((Boolean) arg) {
                Toast.makeText(getApplicationContext(), getString(R.string.messages_enviado), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.mensaje_no_enviado), Toast.LENGTH_SHORT).show();
            }
            InboxDetails.super.onBackPressed();
        }
    }

    public void loadInbox() {
        inbox_messages.setLayoutManager(new LinearLayoutManager(this));
        inbox_messages.setAdapter(new InboxRecyclerViewAdapter(message.getMessages()));

    }

}

