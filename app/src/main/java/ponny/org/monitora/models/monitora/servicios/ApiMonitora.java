package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.models.monitora.modelo.UserData;
import ponny.org.monitora.models.monitora.modelo.UserDataMedico;
import ponny.org.monitora.models.monitora.modelo.UserDataPaciente;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitora {
    protected Context context;

    public ApiMonitora(Context context) {
        this.context = context;
    }


    public boolean updateFirebase(String user) throws IOException, JSONException {
        String url = context.getString(R.string.base_url) +
                context.getString(R.string.update_user) +
                user + "/" +
                FirebaseInstanceId.getInstance().getToken();
        Log.println(Log.ASSERT, "HTTPS", url);
        Response response = ServicesRest.getInstance().get(url);
        return procesarActualizacion(response.body().string());
    }
    private boolean procesarActualizacion(String body) throws JSONException {
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.getInt("n") > 0) {
            return true;
        }
        return false;
    }

}
