package ponny.org.monitora.presenters;

import android.app.Dialog;
import android.content.Context;
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


}
