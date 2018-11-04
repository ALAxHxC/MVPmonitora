
package ponny.org.monitora.models.monitora.modelo.inbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inbox implements Serializable
{

    @SerializedName("patient_view")
    @Expose
    private Boolean patientView;
    @SerializedName("medic_view")
    @Expose
    private Boolean medicView;
    @SerializedName("close")
    @Expose
    private Boolean close;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = new ArrayList<Message>();
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("idMedic")
    @Expose
    private String idMedic;
    @SerializedName("idPatient")
    @Expose
    private String idPatient;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("patient")
    @Expose
    private Patient patient;
    @SerializedName("medic")
    @Expose
    private Medic medic;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = 7429239161570757738L;

    public Boolean getPatientView() {
        return patientView;
    }

    public void setPatientView(Boolean patientView) {
        this.patientView = patientView;
    }

    public Boolean getMedicView() {
        return medicView;
    }

    public void setMedicView(Boolean medicView) {
        this.medicView = medicView;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(String idMedic) {
        this.idMedic = idMedic;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
