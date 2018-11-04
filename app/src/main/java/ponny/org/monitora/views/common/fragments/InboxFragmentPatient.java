package ponny.org.monitora.views.common.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.presenters.vista.medic.MessagesProvider;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.dialogs.MessageViewDialogFargment;
import ponny.org.monitora.views.common.listas.MessagesRecyclerViewAdapter;
import ponny.org.monitora.views.paciente.dialogs.MessagePatientDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InboxFragmentPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InboxFragmentPatient extends Fragment {

    private  MessagePatientDialogFragment messageDialogFragment;
    private OnListFragmentInteractionListener mListener;
    private List<Inbox> messages;
    private MessagesProvider messagesProvider;
    @BindView(R.id.list)
    RecyclerView home;
    @BindView(R.id.messagesend)
    FloatingActionButton sendMessage;

    public InboxFragmentPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InboxFragmentPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static InboxFragmentPatient newInstance(String param1, String param2) {
        InboxFragmentPatient fragment = new InboxFragmentPatient();
        Bundle args = new Bundle();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mListener = new OnListFragmentInteractionListener() {

            @Override
            public void onFragmentInteraction(Inbox message) {
                Log.println(Log.ASSERT, "FRAGMENT", message.getSubject());
                FragmentManager fm = getFragmentManager();
                Intent intent = new Intent(getContext(), InboxDetails.class);
                intent.putExtra("Inbox", message);
                getContext().startActivity(intent);
            }

            @Override
            public void onSendMessage(boolean result) {
                if (result){
                    reaload();
                }
            }
        };
        View view = inflater.inflate(R.layout.fragment_inbox_fragment_patient, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        messagesProvider = new MessagesProvider(getActivity());
        messages = messagesProvider.getInboxPatient(LoginProvider.getLogin().getUserObject().getUserData().get_id());
        sendMessage.bringToFront();
        Context context = view.getContext();
        reaload();
        return view;
    }
    private void reaload(){
        if (messages.size() <= 1) {
            home.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            home.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
        home.setAdapter(new MessagesRecyclerViewAdapter(messages, mListener));

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.ASSERT,"OCN","CLIOCK");
                messageDialogFragment = MessagePatientDialogFragment.newInstance(getString(R.string.enviar_mensaje),new ObseverData());
                messageDialogFragment.show(getFragmentManager(),"fragment_edit_name");
            }
        });
    }

    public static final int DIALOG_FRAGMENT = 1;
    private class ObseverData implements Observer {


        @Override
        public void update(Observable o, Object arg) {
            Log.println(Log.ASSERT,"RECIBE","FAGMENT");
            reaload();
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /**
     *
     * */

}
