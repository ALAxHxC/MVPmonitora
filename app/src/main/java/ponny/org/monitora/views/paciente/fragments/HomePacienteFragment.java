package ponny.org.monitora.views.paciente.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.presenters.vista.paciente.HomeProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePacienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePacienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePacienteFragment extends Fragment {

    @BindView(R.id.home_user_save)
    FloatingActionButton home;
    @BindView(R.id.glucometria_save)
    ImageButton glucometria;
    @BindView(R.id.bascula_peso)
    ImageButton bascula;
    @BindView(R.id.tensiometria_save)
    ImageButton tensimetria;
    @BindView(R.id.oximetria_save)
    ImageButton oximetria;
    private HomeProvider homeProvider;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomePacienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePacienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePacienteFragment newInstance(String param1, String param2) {
        HomePacienteFragment fragment = new HomePacienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_paciente, container, false);
        ButterKnife.bind(this, view);
        homeProvider=new HomeProvider(this.getActivity());
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @OnClick(R.id.oximetria_save)
    public void oximetria(){
        homeProvider.scanBLE();
    }
    @OnClick(R.id.glucometria_save)
    public void glucometria(){
        homeProvider.scanBLE();
    }
    @OnClick(R.id.tensiometria_save)
    public void tensiometria(){
        homeProvider.scanBLE();
    }
    @OnClick(R.id.bascula_peso)
    public void bascula(){
        homeProvider.scanBLE();
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
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        homeProvider.apagarBLE();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
