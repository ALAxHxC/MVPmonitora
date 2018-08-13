package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.pacientes.ListPatients;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitoraPacientes  extends ApiMonitora {
    public ApiMonitoraPacientes(Context context) {
        super(context);
    }
    public ListPatients getPatients(String id) throws IOException {
        String url=context.getString(R.string.base_url)+context.getString(R.string.get_patients)+id;
        Response response= ServicesRest.getInstance().get(url);
        return procesarMuestrasPaciente(response.body().string());
    }
    private ListPatients procesarMuestrasPaciente(String body){
        Gson gson=new Gson();
        ListPatients listPatients=gson.fromJson(body,ListPatients.class);
        return listPatients;
    }
}
