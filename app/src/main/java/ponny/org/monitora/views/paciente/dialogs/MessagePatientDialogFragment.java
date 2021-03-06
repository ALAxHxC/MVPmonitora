package ponny.org.monitora.views.paciente.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.presenters.vista.medic.MessageProvider;


@SuppressLint("ValidFragment")
public class MessagePatientDialogFragment extends DialogFragment {
    @BindView(R.id.txt_asunto)
    TextView asunto;
    @BindView(R.id.txt_descripccion)
    TextView descripccion;
    @BindView(R.id.btn_send_message)
    FloatingActionButton send;
    private MessageProvider messageProvider;
    private DialogProvider dialogProvider;
    private String patient;
    private Observer observer;

    @SuppressLint("ValidFragment")
    public MessagePatientDialogFragment(Observer observer) {
        super();
        this.observer=observer;
    }

    public static MessagePatientDialogFragment newInstance(String title,Observer obseverData) {
        ponny.org.monitora.views.medico.dialogs.MessageDialogFragment frag = new ponny.org.monitora.views.medico.dialogs.MessageDialogFragment();
        Bundle args = new Bundle();
        args.putString("ID", title);
        frag.setArguments(args);

        return new MessagePatientDialogFragment(obseverData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dialog_message, container);
        ButterKnife.bind(this,view);
        dialogProvider=new DialogProvider(this.getContext());
        messageProvider=new MessageProvider(this.getContext());
        return view;
    }
    @OnClick(R.id.btn_send_message)
    public void sendMessage(){
        boolean response=false;
        try {
            response=messageProvider.sendMessageAsPatient(asunto,descripccion);
            if(response)
                dialogProvider.showToast(R.string.messages_enviado);
            else
                dialogProvider.showToast(R.string.mensaje_no_enviado);
           // getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());

        } catch (IOException e) {
            dialogProvider.createDialogError(R.string.error_fatal,R.string.mensaje_no_enviado);
            Crashlytics.logException(e);
          //  getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());

            e.printStackTrace();
        }finally {
            this.observer.update(new Observable(),new Boolean(response));
            this.dismiss();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}

