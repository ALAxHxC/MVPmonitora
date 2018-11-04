package ponny.org.monitora.models.monitora.servicios;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.models.monitora.modelo.mensajes.MessageToCreate;
import ponny.org.monitora.utils.ServicesRest;

public class ApiMonitoraMessages extends ApiMonitora {
    public ApiMonitoraMessages(Context context) {
        super(context);
    }

    public boolean sendMessage(Inbox message) throws IOException {
        String url = context.getString(R.string.base_url) + context.getString(R.string.post_message);
        Gson gson = new Gson();
        String data = gson.toJson(message, Inbox.class);
        Response response = ServicesRest.getInstance().post(data, url);
        Log.println(Log.ASSERT, "api", response.body().string());
        return response.code() == 201 ? true : false;
    }



    public Message[] getMessagesMedic(String id) throws IOException {
        String url = this.context.getString(R.string.base_url) + this.context.getString(R.string.get_messages_medic) + id;
        Response response = ServicesRest.getInstance().get(url);
        return getMessagesString(response.body().string());
    }

    public Message[] getMessagesPatients(String id) throws IOException {
        String url = this.context.getString(R.string.base_url) + this.context.getString(R.string.get_messages_patient) + id;
        Response response = ServicesRest.getInstance().get(url);
        return getMessagesString(response.body().string());
    }

    public Message[] getMessagesString(String mensajes) {
        Gson gson = new Gson();
        Message[] messages = gson.fromJson(mensajes, Message[].class);
        return messages;
    }

}
