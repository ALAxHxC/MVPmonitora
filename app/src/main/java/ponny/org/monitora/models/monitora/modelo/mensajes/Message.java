
package ponny.org.monitora.models.monitora.modelo.mensajes;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message implements Serializable
{

    @SerializedName("response")
    @Expose
    private Object response;
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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = -9125165768415125138L;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Message{" +
                "response=" + response +
                ", patientView=" + patientView +
                ", medicView=" + medicView +
                ", close=" + close +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", id='" + id + '\'' +
                ", idMedic='" + idMedic + '\'' +
                ", idPatient='" + idPatient + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", v=" + v +
                '}';
    }
}
