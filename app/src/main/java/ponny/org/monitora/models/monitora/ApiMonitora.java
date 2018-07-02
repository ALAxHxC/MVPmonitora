package ponny.org.monitora.models.monitora;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.Login;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitora {
    private Context context;

    public ApiMonitora(Context context) {
        this.context = context;
    }

    public LoginMonitora getLogin(String user, String password) throws JSONException, IOException, NullPointerException, SocketTimeoutException {
        String url = context.getString(R.string.base_url) + context.getString(R.string.login);
        JSONObject body = createLogin(user, password);
        Log.println(Log.ASSERT, "HTTPS", body.toString());
        Response response = ServicesRest.getInstance().post(body.toString(), url);
        return  convertBodyToObject(response.body().string());

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
        Log.println(Log.ASSERT,this.getClass().getName(),objeto.toString()+"");
        return objeto;
    }

}
