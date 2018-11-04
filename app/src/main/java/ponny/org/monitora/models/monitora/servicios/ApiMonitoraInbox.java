package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitoraInbox  extends ApiMonitora {
    public ApiMonitoraInbox(Context context) {
        super(context);
    }
    public Inbox[] getMessagesPatients(String id) throws IOException {
        String url=this.context.getString(R.string.base_url)+this.context.getString(R.string.get_inbox_patient)+id;
        Response response= ServicesRest.getInstance().get(url);
        return  getMessagesString(response.body().string());
    }
    public Inbox[] getMessagesString(String mensajes)
    {
        Gson gson=new Gson();
        Inbox[] messages=gson.fromJson(mensajes,Inbox[].class);
        return messages;
    }
    public boolean sendResponse(ponny.org.monitora.models.monitora.modelo.inbox.Message message, String id) throws IOException {
        String url = context.getString(R.string.base_url) + context.getString(R.string.post_message) + id;
        Log.println(Log.ASSERT,"Enviando mensaje",url);
        Gson gson = new Gson();
        String data = gson.toJson(message, ponny.org.monitora.models.monitora.modelo.inbox.Message.class);
        Response response = ServicesRest.getInstance().post(data, url);
        Log.println(Log.ASSERT, "api", response.body().string());
        return response.code() == 201 ? true : false;

    }
}
