package ponny.org.monitora.views.common.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.presenters.vista.medic.MessagesProvider;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.listas.MessagesRecyclerViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InboxFragmentPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InboxFragmentPatient extends Fragment {


    private OnListFragmentInteractionListener mListener;
    private List<Message> messages;
    private MessagesProvider messagesProvider;
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
        mListener=new OnListFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(String uri) {
                Log.println(Log.ASSERT,"prueba",uri);
            }

            public void onFragmentInteraction(Uri uri) {
                Log.println(Log.ASSERT,"FRAGMENT",uri.getFragment());
            }
        };
        // Inflate the layout for this fragment
        messagesProvider=new MessagesProvider(getActivity());
        switch (LoginProvider.getLogin().getUserObject().getUserTypeDescription()){
            case 2:
                messages=messagesProvider.getMessagesPaciente(LoginProvider.getLogin().getUserObject().getUserData().get_id());
                break;
            case 3:
                messages=messagesProvider.getMessagesMedico(LoginProvider.getLogin().getUserObject().getUserData().get_id());
                break;
        }
       // messagesProvider.getMessagesPaciente(LoginProvider.getLogin().getUserObject().getUserData().get_id());
        //Log.println(Log.ASSERT,"API",messages.size()+"");

        View view = inflater.inflate(R.layout.fragment_inbox_fragment_patient, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (messages.size() <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
            }
            recyclerView.setAdapter(new MessagesRecyclerViewAdapter(messages, mListener));

        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
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
