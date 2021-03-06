package ponny.org.monitora.presenters.vista.medic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ponny.org.monitora.models.monitora.modelo.UserDataPaciente;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraInbox;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraMessages;
import ponny.org.monitora.presenters.vista.LoginProvider;

public class MessageProvider {
    private Context context;
    private TextView asunto;
    private TextView descripccion;
    private ApiMonitoraMessages apiMonitoraMessages;
    private ApiMonitoraInbox apiMonitoraInbox;

    public MessageProvider(Context context) {
        this.context = context;
        apiMonitoraMessages = new ApiMonitoraMessages(context);
        this.apiMonitoraInbox = new ApiMonitoraInbox(context);
    }

    public boolean sendMessageAsMedic(TextView asunto, TextView descripccion, final String paciente) throws IOException {

       /* this.asunto = asunto;
        this.descripccion = descripccion;

        Inbox message = crearMuestraAsMedic(
                MessageProvider.this.asunto.getText().toString(),
                MessageProvider.this.descripccion.getText().toString(),
                paciente
        );
        boolean response = apiMonitoraMessages.sendMessage(message);
        return response;*/
        return true;

    }

    public boolean appendMessage(TextView response, Inbox inbox) throws IOException {
        ponny.org.monitora.models.monitora.modelo.inbox.Message message = new ponny.org.monitora.models.monitora.modelo.inbox.Message(
                LoginProvider.getLogin().getUserObject().getUserData().getFirstNames() + " " + LoginProvider.getLogin().getUserObject().getUserData().getLastNames(),
                response.getText().toString());
                boolean respuesta = apiMonitoraInbox.sendResponse(message,inbox.getId());
                return  respuesta;
    }
    public boolean sendMessageAsPatient(TextView asunto, TextView descripccion) throws IOException {

        this.asunto = asunto;
        this.descripccion = descripccion;

        Inbox message = crearMuestraAsPatient(
                MessageProvider.this.asunto.getText().toString(),
                MessageProvider.this.descripccion.getText().toString()
        );
        boolean response = apiMonitoraMessages.sendMessage(message);
        return response;

    }

    private Inbox crearMuestraAsPatient(String asunto, String descripccion) {
        List<ponny.org.monitora.models.monitora.modelo.inbox.Message> messsage = new ArrayList<>();
        messsage.add(new ponny.org.monitora.models.monitora.modelo.inbox.Message(LoginProvider.getLogin().getUserObject().getUserData().getFirstNames(), descripccion));
        UserDataPaciente userDataPaciente = (UserDataPaciente) LoginProvider.getLogin().getUserObject().getUserData();
        Log.println(Log.ASSERT, "PATIENT", userDataPaciente.getIdMedic());
        Inbox message = new Inbox(userDataPaciente.getIdMedic(), LoginProvider.getLogin().getUserObject().getUserData().get_id());
        message.setSubject(asunto);
        message.setMessages(messsage);
        return message;
    }

    private Message crearMuestraAsMedic(String asunto, String descripccion, String paciente) {
        Message message = new Message(LoginProvider.getLogin().getUserObject().getUserData().get_id(), paciente);
        message.setDescription(descripccion);
        message.setSubject(asunto);
        return message;
    }
}
