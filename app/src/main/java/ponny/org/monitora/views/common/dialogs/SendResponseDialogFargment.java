package ponny.org.monitora.views.common.dialogs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.presenters.vista.medic.MessageProvider;

public class SendResponseDialogFargment extends DialogFragment {

    @BindView(R.id.txt_response)
    EditText descripccion;
    @BindView(R.id.btn_send_message)
    FloatingActionButton send;
    private Inbox message;
    private MessageProvider messageProvider;
    private Observer observer;
    @SuppressLint("ValidFragment")
    public SendResponseDialogFargment(Observer observer) {
        super();
        this.observer=observer;
    }

    public static SendResponseDialogFargment newInstance(Inbox message, Observer observer) {
        SendResponseDialogFargment send = new SendResponseDialogFargment(observer);
        Bundle args = new Bundle();
        args.putSerializable("id", message);
        send.setArguments(args);
        return send;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageProvider=new MessageProvider(getContext());
        View view = inflater.inflate(R.layout.dialog_response, container);
        ButterKnife.bind(this, view);
        message = (Inbox) getArguments().getSerializable("id");
        return view;
    }

    @OnClick(R.id.btn_send_message)
    public void OnClick() {
        boolean response = false;
        try {
            response=messageProvider.appendMessage(descripccion, message);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            observer.update(new Observable(),response);
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