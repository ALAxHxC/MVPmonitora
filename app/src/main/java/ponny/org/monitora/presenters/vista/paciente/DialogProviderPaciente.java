package ponny.org.monitora.presenters.vista.paciente;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.TextView;

import ponny.org.monitora.R;
import ponny.org.monitora.presenters.DialogProvider;

import static ponny.org.monitora.utils.Utils.getPrettyDateNow;

public class DialogProviderPaciente extends DialogProvider {

    private EditText txt_description;
    private FloatingActionButton save_muestra;

    public DialogProviderPaciente(Context context) {
        super(context);
    }

    public Dialog recogerMuestra() {
        Dialog muestraDialog = super.createDialog(R.layout.dialog_registro_muestra);
        muestraDialog.show();
        txt_description = muestraDialog.findViewById(R.id.txt_detail_description);
        TextView txt_date= muestraDialog.findViewById(R.id.txt_detail_fecha);
        txt_date.setText(getPrettyDateNow());
        save_muestra = muestraDialog.findViewById(R.id.btn_save_description);
        return muestraDialog;
    }

    public EditText getTxt_description() {
        return txt_description;
    }

    public FloatingActionButton getSave() {
        return save_muestra;
    }
}
