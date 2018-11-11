package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.api.Api;
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
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitoraLogin extends ApiMonitora{
    public ApiMonitoraLogin(Context context) {
        super(context);
    }

    public LoginMonitora getLogin(String user, String password) throws JSONException, IOException, NullPointerException, SocketTimeoutException {
        String url = context.getString(R.string.base_url) + context.getString(R.string.login);
        JSONObject body = createLogin(user, password);
       // Log.println(Log.ASSERT, "HTTPS", body.toString());
        Response response = ServicesRest.getInstance().post(body.toString(), url);
       // Log.println(Log.ASSERT,"SQL",response.body().string());
        return convertBodyToObject(response.body().string());

    }
    private JSONObject createLogin(String user, String password) throws JSONException {
        JSONObject dataJson = new JSONObject();
        dataJson.put(context.getString(R.string.username_body), user);
        dataJson.put(context.getString(R.string.password_body), password);
        return dataJson;
    }
    private LoginMonitora convertBodyToObject(String body) throws JSONException {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(body);
        LoginMonitora objeto = gson.fromJson(jsonObject.toString(), LoginMonitora.class);

        UserData userData = getUserData(objeto.getUserObject().getUserTypeDescription(), jsonObject.getJSONObject("user").getJSONObject("userData"));
        objeto.getUserObject().setUserData(userData);
       Log.println(Log.ASSERT, this.getClass().getName(), userData.toString());
        return objeto;
    }
    private UserData getUserData(int userTypeDescription, JSONObject userData) {
        Gson gson = new Gson();
        UserData userDataObj = null;
        switch (userTypeDescription) {
            case 2:
                userDataObj = gson.fromJson(userData.toString(), UserDataPaciente.class);
                break;
            case 3:
                userDataObj = gson.fromJson(userData.toString(), UserDataMedico.class);
                break;

        }
        return userDataObj;

    }

}
