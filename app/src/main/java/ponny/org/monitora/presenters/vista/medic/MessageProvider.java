package ponny.org.monitora.presenters.vista.medic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import ponny.org.monitora.models.monitora.modelo.UserDataPaciente;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraMessages;
import ponny.org.monitora.presenters.vista.LoginProvider;

public class MessageProvider {
    private Context context;
    private TextView asunto;
    private TextView descripccion;
    private ApiMonitoraMessages apiMonitoraMessages;

    public MessageProvider(Context context) {
        this.context = context;
        apiMonitoraMessages = new ApiMonitoraMessages(context);
    }

    public boolean sendMessageAsMedic(TextView asunto, TextView descripccion, final String paciente) throws IOException {

        this.asunto = asunto;
        this.descripccion = descripccion;

        Message message = crearMuestraAsMedic(
                MessageProvider.this.asunto.getText().toString(),
                MessageProvider.this.descripccion.getText().toString(),
                paciente
        );
        boolean response = apiMonitoraMessages.sendMessage(message);
        return response;

    }


    public boolean sendMessageAsPatient(TextView asunto, TextView descripccion) throws IOException {

        this.asunto = asunto;
        this.descripccion = descripccion;

        Message message = crearMuestraAsPatient(
                MessageProvider.this.asunto.getText().toString(),
                MessageProvider.this.descripccion.getText().toString()
        );
        boolean response = apiMonitoraMessages.sendMessage(message);
        return response;

    }

    private Message crearMuestraAsPatient(String asunto, String descripccion) {
        UserDataPaciente userDataPaciente= (UserDataPaciente) LoginProvider.getLogin().getUserObject().getUserData();
        Log.println(Log.ASSERT,"api",userDataPaciente.getIdMedic());
        Message message = new Message( userDataPaciente.getIdMedic() ,LoginProvider.getLogin().getUserObject().getUserData().get_id());
        message.setDescription(descripccion);
        message.setSubject(asunto);
        return message;
    }

    private Message crearMuestraAsMedic(String asunto, String descripccion, String paciente) {
        Message message = new Message( LoginProvider.getLogin().getUserObject().getUserData().get_id(),paciente);
        message.setDescription(descripccion);
        message.setSubject(asunto);
        return message;
    }
}
