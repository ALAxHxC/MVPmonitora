package ponny.org.monitora.views.common.graphs;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.presenters.chart.LineChartMuestrasProvider;

import static ponny.org.monitora.presenters.DialogProvider.showStaticToast;
import static ponny.org.monitora.views.medico.fragments.pacientes.TomaFragment.ARG_COLUMN_COUNT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Spo2Chart.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Spo2Chart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Spo2Chart extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private LineChartMuestrasProvider lineChartMuestrasProvider;
    // TODO: Rename parameter arguments, choose names that match
    public List<Muestra> muestras;
    @BindView(R.id.linearChat)
    public LineChart linearGraph;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Spo2Chart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Spo2Chart.
     */
    // TODO: Rename and change types and number of parameters
    public static Spo2Chart newInstance(List<Muestra> list) {
        Spo2Chart fragment = new Spo2Chart();
        Bundle args = new Bundle();
        if (list != null) {
            Gson gson = new Gson();
            String muestras = gson.toJson(list.toArray());

            args.putString(ARG_COLUMN_COUNT, muestras);

            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        if (getArguments() != null) {
            Muestra[] array = gson.fromJson(getArguments().getString(ARG_COLUMN_COUNT), Muestra[].class);
            muestras = new ArrayList<>();
            muestras.addAll(Arrays.asList(array));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spo2_chart, container, false);
        ButterKnife.bind(this, view);
        try {
            lineChartMuestrasProvider = new LineChartMuestrasProvider(linearGraph, muestras);
            lineChartMuestrasProvider.listaDeDatosMuestrasSpo2();
            linearGraph.setData(lineChartMuestrasProvider.loadDataSetSpo2(getString(R.string.pulso), Color.MAGENTA, Color.WHITE));
        } catch (NullPointerException ex) {
            showStaticToast(getContext(), R.string.no_hay_datos);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
