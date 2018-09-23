package ponny.org.monitora.views.medico.fragments.pacientes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.pacientes.ListPatients;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.presenters.listeners.FragmentPatientListener;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.presenters.vista.medic.MedicHomeProvider;
import ponny.org.monitora.views.medico.fragments.PatientsRecyclerViewAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class PatientFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private FragmentPatientListener mListener;
    private MedicHomeProvider medicHomeProvider;
    private ListPatients listPatients;
    private ActivityProvider activityProvider;
    private DialogProvider dialogProvider;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatientFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PatientFragment newInstance(int columnCount) {
        PatientFragment fragment = new PatientFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProvider = new ActivityProvider(this.getContext());
        if (getArguments() != null) {
            // mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        medicHomeProvider = new MedicHomeProvider(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        try {
            listPatients = medicHomeProvider.getPacientes(LoginProvider.getLogin().getUserObject().getUserData().get_id());
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setAdapter(new PatientsRecyclerViewAdapter(listPatients, mListener, activityProvider));
            }

        }catch (NullPointerException ex){
            dialogProvider.showStaticToast(getContext(),R.string.no_hay_datos);
        }

           // Set the adapter
        return view;
    }

    /*
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnListFragmentInteractionListener) {
                mListener = (OnListFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
