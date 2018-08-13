package ponny.org.monitora.presenters;

import android.content.Context;
import ponny.org.monitora.models.monitora.modelo.pacientes.Entity;
import android.content.Intent;
import android.os.Bundle;


import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.Login;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.views.medico.MedicoMain;
import ponny.org.monitora.views.medico.VistaPacienteMedico;
import ponny.org.monitora.views.paciente.MuestraOximetria;
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
    public void goOximetria(String address){
        Bundle bundle=new Bundle();
        bundle.putString(context.getString(R.string.address),address);
        Intent intent=new Intent(context, MuestraOximetria.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public void goVistaPaciente(Entity entity){
        Bundle bundle=new Bundle();
        bundle.putSerializable(context.getString(R.string.paciente),entity);
        Intent intent=new Intent(context, VistaPacienteMedico.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public Entity getPaciente(Intent intent){
        Entity entity=(Entity)intent.getExtras().getSerializable(context.getString(R.string.paciente));
        return entity;
    }
}
