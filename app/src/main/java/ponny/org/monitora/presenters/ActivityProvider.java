package ponny.org.monitora.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ponny.org.monitora.models.monitora.modelo.Login;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.views.medico.MedicoMain;
import ponny.org.monitora.views.paciente.PacienteMain;

public class ActivityProvider {
    private Context context;
    public ActivityProvider(Context context) {
        this.context = context;
    }
    public void goMedicoMain(LoginMonitora login){
        Intent intent=new Intent(context,MedicoMain.class);
        putSerializableDataLogin(intent,Login.class,login);
        context.startActivity(intent);
    }
    public void goPacienteMain(LoginMonitora login){
        Intent intent=new Intent(context,PacienteMain.class);
        putSerializableDataLogin(intent,Login.class,login);
        context.startActivity(intent);
    }
    public void putSerializableDataLogin(Intent intent,Class clase,LoginMonitora object){
        Bundle bundle = new Bundle();
        bundle.putSerializable(clase.getName(), object);
        intent.putExtras(bundle);
    }
}
