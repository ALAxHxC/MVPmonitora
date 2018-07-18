package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitoraMuestras extends ApiMonitora {
    public ApiMonitoraMuestras(Context context) {
        super(context);
    }
    public boolean sendMuestra(Muestra muestra) throws IOException {
        String url = context.getString(R.string.base_url) +
                context.getString(R.string.muestra);
        String body=procesarMuestra(muestra);
        Log.println(Log.ASSERT,"API",body);
        Response response = ServicesRest.getInstance().post(body, url);
        Log.println(Log.ASSERT,"API",response.code()+"");
        if(response.code()==201){
            return true;
        }
       // FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        return false;
    }
    public Muestra[] getMuestras() throws IOException {
        String url=context.getString(R.string.base_url)+context.getString(R.string.muestra)+ LoginProvider.getLogin().getUserObject().getUserData().get_id() ;
        Response response=ServicesRest.getInstance().get(url);
        return procesarMuestras(response.body().string());

    }
    private Muestra[] procesarMuestras(String body){
            Gson gson=new Gson();
            Muestra[] muestras=gson.fromJson(body, Muestra[].class);
            return muestras;
    }
    private String procesarMuestra(Muestra muestra) {
        Gson gson = new Gson();
        String muestraBody = gson.toJson(muestra);
        return muestraBody;
    }

}
