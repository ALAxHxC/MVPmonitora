package ponny.org.monitora.views.common.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;

public class MessageViewDialogFargment extends DialogFragment {
    @BindView(R.id.txt_asunto)
    TextView asunto;
    @BindView(R.id.txt_descripccion)
    TextView descripccion;
    @BindView(R.id.btn_send_message)
    FloatingActionButton send;
    private Message message;

    public MessageViewDialogFargment() {
    }

    public static MessageViewDialogFargment newInstance(Message message) {
        MessageViewDialogFargment messageViewDialogFargment=new MessageViewDialogFargment();
        Bundle args = new Bundle();
        args.putSerializable("id",message);
        messageViewDialogFargment.setArguments(args);
        return messageViewDialogFargment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_message, container);
        ButterKnife.bind(this, view);
        message= (Message) getArguments().getSerializable("id");
        showMessage(message);
        return view;
    }
    private void showMessage(Message message){
        asunto.setText(message.getSubject());
        asunto.setFocusable(false);
        asunto.setFocusableInTouchMode(false);
        asunto.setClickable(false);
        descripccion.setText(message.getDescription());
        descripccion.setFocusable(false);
        descripccion.setFocusableInTouchMode(false);
        descripccion.setClickable(false);
    }
    @OnClick(R.id.btn_send_message)
    public void OnClick(){
        this.dismiss();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


}
