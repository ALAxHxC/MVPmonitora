package ponny.org.monitora.presenters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import ponny.org.monitora.R;

public class DialogProvider {
    private Context context;
    public DialogProvider(Context context){
        this.context=context;
    }
    public Dialog createDialog(int r){
        Dialog dialogo = new Dialog(context, R.style.AlertDialogTheme);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setContentView(r);
        Window window = dialogo.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        return dialogo;
    }
    public AlertDialog createDialogError(int title,int mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(mensaje)
                .setCancelable(false)
                .setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
// create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
// show it
        alertDialog.show();
        return  alertDialog;
    }


}
