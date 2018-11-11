package ponny.org.monitora.views.paciente.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.presenters.vista.paciente.MuestraProvider;
import ponny.org.monitora.presenters.vista.paciente.HomeProviderPaciente;
import ponny.org.monitora.views.common.fragments.InboxDetails;

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
    @BindView(R.id.txt_pulse_value)
    TextView pulse;
    @BindView(R.id.txt_spo2_value)
    TextView spo2;
    @BindView(R.id.txt_peso_value)
    TextView peso;
    @BindView(R.id.txt_gluco_value)
    TextView gluco;
    private HomeProviderPaciente homeProviderPaciente;
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
        View view = inflater.inflate(R.layout.fragment_home_paciente, container, false);
        ButterKnife.bind(this, view);
        homeProviderPaciente = new HomeProviderPaciente(this.getActivity());
        loadDataMuestras();
        // Inflate the layout for this fragment
        return view;
    }
    @OnClick(R.id.home_user_save)
    public void subirMuestra(){
        homeProviderPaciente.subirMuestra(new ObserverData());
    }

    public void loadDataMuestras() {
         try {
                pulse.setText(MuestraProvider.getMuestra().getData().getOximeter().getPulse() + "");
                spo2.setText(MuestraProvider.getMuestra().getData().getOximeter().getSpo2() + "");
                peso.setText(MuestraProvider.getMuestra().getData().getWeigth() + "");
                gluco.setText(MuestraProvider.getMuestra().getData().getGlucose().getGluco() + "");
            } catch (Exception ex) {
                ex.printStackTrace();
            Log.println(Log.ASSERT, "BE", ex.getMessage());
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @OnClick(R.id.oximetria_save)
    public void oximetria() {
        homeProviderPaciente.scanBLE();
    }

    @OnClick(R.id.glucometria_save)
    public void glucometria() {
        homeProviderPaciente.scanBLE();
    }

    @OnClick(R.id.tensiometria_save)
    public void tensiometria() {
        homeProviderPaciente.scanBLE();
    }

    @OnClick(R.id.bascula_peso)
    public void bascula() {
        homeProviderPaciente.scanBLE();
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
        homeProviderPaciente.apagarBLE();
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
    private class ObserverData implements java.util.Observer {
        @Override
        public void update(Observable o, Object arg) {
            boolean reboot = (Boolean) arg;
            if(reboot){
                MuestraProvider.initMuestra();
            }
            loadDataMuestras();
           Log.println(Log.ASSERT,"data","data");
        }
    }
}
