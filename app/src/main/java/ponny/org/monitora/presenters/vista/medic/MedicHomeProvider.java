package ponny.org.monitora.presenters.vista.medic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.models.monitora.modelo.pacientes.ListPatients;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraPacientes;
import ponny.org.monitora.presenters.ActivityProvider;

public class MedicHomeProvider {
    private Context context;
    private ActivityProvider activityProvider;
    private ApiMonitoraPacientes apiMonitoraPacientes;
    public MedicHomeProvider(Activity activity) {
        this.context = activity;
        this.activityProvider = new ActivityProvider(activity);
        apiMonitoraPacientes=new ApiMonitoraPacientes(activity);
    }
    public ListPatients getPacientes(String id){
        try {
            ListPatients patients=apiMonitoraPacientes.getPatients(id);
            Log.println(Log.ASSERT,"API",patients.toString());
            return patients;
        } catch (IOException e) {
            Log.println(Log.ASSERT,"API",e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
