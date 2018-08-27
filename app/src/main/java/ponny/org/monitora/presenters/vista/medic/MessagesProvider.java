package ponny.org.monitora.presenters.vista.medic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraMessages;
import ponny.org.monitora.presenters.ActivityProvider;

public class MessagesProvider {
    private Context context;
    private ActivityProvider activityProvider;
    private ApiMonitoraMessages apiMonitoraMessages;

    public MessagesProvider(Activity activity) {
        this.context = activity;
        this.activityProvider = new ActivityProvider(activity);
        this.apiMonitoraMessages = new ApiMonitoraMessages(activity);
    }

    public List<Message> getMessagesPaciente(String id) {
        List<Message> list = new ArrayList<>();
        try {
            Message[] mensajes =apiMonitoraMessages.getMessagesPatients(id);
            list = Arrays.asList(mensajes);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.println(Log.ASSERT,"API",ex.getMessage());
            return list;
        }
    }
    public List<Message> getMessagesMedico(String id) {
        List<Message> list = new ArrayList<>();
        try {
            Message[] mensajes =apiMonitoraMessages.getMessagesMedic(id);
            list = Arrays.asList(mensajes);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.println(Log.ASSERT,"API",ex.getMessage());
            return list;
        }
    }
}
