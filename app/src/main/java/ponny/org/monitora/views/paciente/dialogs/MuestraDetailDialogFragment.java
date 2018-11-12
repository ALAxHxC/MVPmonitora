package ponny.org.monitora.views.paciente.dialogs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;

public class MuestraDetailDialogFragment extends DialogFragment {
    @BindView(R.id.txt_detail_fecha)
    TextView fecha;
    @BindView(R.id.txt_detail_pulse)
    TextView pulso;
    @BindView(R.id.txt_detail_spo2)
    TextView spo2;
    @BindView(R.id.txt_detail_pi)
    TextView pi;
    @BindView(R.id.txt_detail_description)
    TextView description;
    @BindView(R.id.btn_save_description)
    FloatingActionButton close;
    Muestra muestra;

    public static MuestraDetailDialogFragment newInstance(String title, Muestra muestra) {
        MuestraDetailDialogFragment frag = new MuestraDetailDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("id", muestra);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_muestra, container);
        ButterKnife.bind(this, view);
        muestra = (Muestra) getArguments().getSerializable("id");
        loadMuestra();
        return view;
    }

    public void loadMuestra() {
        fecha.setText(muestra.getCreateAt());
        pulso.setText(muestra.getData().getOximeter().getPulse() + "");
        pi.setText(muestra.getData().getOximeter().getPi() + "");
        spo2.setText(muestra.getData().getOximeter().getSpo2() + "");
        description.setText(getString(R.string.paciente_dijo)+muestra.getDescription());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuestraDetailDialogFragment.this.dismiss();
            }
        });
    }

}
