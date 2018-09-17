package ponny.org.monitora.views.medico.dialogs;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.presenters.vista.medic.MessageProvider;


public class MessageDialogFragment extends DialogFragment {
    @BindView(R.id.txt_asunto)
    TextView asunto;
    @BindView(R.id.txt_descripccion)
    TextView descripccion;
    @BindView(R.id.btn_send_message)
    FloatingActionButton send;
    private MessageProvider messageProvider;
    private DialogProvider dialogProvider;
    private String patient;

    public MessageDialogFragment() {

    }

    public static MessageDialogFragment newInstance(String title) {
        MessageDialogFragment frag = new MessageDialogFragment();
        Bundle args = new Bundle();
        args.putString("ID", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dialog_message, container);
        ButterKnife.bind(this,view);
        dialogProvider=new DialogProvider(this.getContext());
        messageProvider=new MessageProvider(this.getContext());
    patient=getArguments().getString("ID");
        //patient= LoginProvider.getLogin().getUserObject().getUserData().get_id();
        return view;
    }
    @OnClick(R.id.btn_send_message)
    public void sendMessage(){
        Log.println(Log.ASSERT,"API","click");
        try {
            boolean response=messageProvider.sendMessageAsMedic(asunto,descripccion,patient);
            if(response)
                dialogProvider.showToast(R.string.messages_enviado);
            else
                dialogProvider.showToast(R.string.mensaje_no_enviado);
        } catch (IOException e) {
            Log.println(Log.ASSERT,"API","error");
            dialogProvider.createDialogError(R.string.error_fatal,R.string.mensaje_no_enviado);
            Crashlytics.logException(e);
            e.printStackTrace();
        }finally {
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
