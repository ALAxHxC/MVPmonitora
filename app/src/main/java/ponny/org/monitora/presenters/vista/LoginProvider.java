package ponny.org.monitora.presenters.vista;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.ApiMonitora;
import ponny.org.monitora.models.monitora.modelo.Login;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.utils.CipherMonitora;

public class LoginProvider {
    private ApiMonitora apiMonitora;
    private DialogProvider dialogProvider;
    private ActivityProvider activityProvider;
    private Context context;
    public LoginProvider(Context context){
        this.context=context;
        dialogProvider=new DialogProvider(context);
        apiMonitora=new ApiMonitora(context);
        activityProvider=new ActivityProvider(context);
    }
    public void initSesion(Button button, AutoCompleteTextView user, EditText pass){
        Dialog wait=dialogProvider.createDialog(R.layout.dialog_wait);
        wait.show();
        String username= user.getText().toString();
        String password=pass.getText().toString();
        Log.println(Log.ASSERT,"CR",username+","+password);

        try {
         LoginMonitora loginMonitora=   apiMonitora.getLogin(username,password);
            validateUser(loginMonitora);
        } catch (JSONException e) {
            wait.hide();
            dialogProvider.createDialogError(R.string.error_sesion,R.string.verifique_datos);
            e.printStackTrace();
        } catch (IOException e) {

            dialogProvider.createDialogError(R.string.error_sesion,R.string.verifique_datos);
            wait.hide();
            e.printStackTrace();
        }
        catch (Exception e){
            dialogProvider.createDialogError(R.string.error_sesion,R.string.verifique_datos);
            wait.hide();
            e.printStackTrace();
        }

    }
    private void validateUser(LoginMonitora login){

        switch (login.getUserObject().getUserTypeDescription()){
            case 2:
                activityProvider.goPacienteMain(login);
                break;
            case 3:
                activityProvider.goMedicoMain(login);
                break;
            default:
                dialogProvider.createDialogError(R.string.error_sesion,R.string.verifique_datos);
                break;

        }
    }
}
