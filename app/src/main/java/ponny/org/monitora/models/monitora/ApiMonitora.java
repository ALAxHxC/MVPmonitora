package ponny.org.monitora.models.monitora;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitora {
    private Context context;
    public ApiMonitora(Context context){
        this.context=context;
    }
    public void getLogin(String user,String password) throws JSONException, IOException, NullPointerException {
            String url = context.getString(R.string.base_url) + context.getString(R.string.login);
            JSONObject body = createLogin(user, password);
        Log.println(Log.ASSERT,"HTTPS",body.toString());
            Response response = ServicesRest.getInstance().post(body.toString(), url);
           Log.println(Log.ASSERT,"HTTPS",response.body().string());
    }
    private JSONObject createLogin(String user,String password) throws JSONException {
        JSONObject dataJson = new JSONObject();
        dataJson.put(context.getString(R.string.username_body),user);
        dataJson.put(context.getString(R.string.password_body),password);
        return dataJson;
    }

}
