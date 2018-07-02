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
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.utils.CipherMonitora;

public class LoginProvider {
    private ApiMonitora apiMonitora;
    private DialogProvider dialogProvider;
    private Context context;
    public LoginProvider(Context context){
        this.context=context;
        dialogProvider=new DialogProvider(context);
        apiMonitora=new ApiMonitora(context);
    }
    public void initSesion(Button button, AutoCompleteTextView user, EditText pass){
        Dialog wait=dialogProvider.createDialog(R.layout.dialog_wait);
        String username= user.getText().toString();
        String password=pass.getText().toString();
        Log.println(Log.ASSERT,"CR",username+","+password);
        wait.show();
        try {
            apiMonitora.getLogin(username,password);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
