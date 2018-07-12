
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Muestra implements Serializable
{

    @SerializedName("idPatient")
    @Expose
    private String idPatient;
    @SerializedName("idMedic")
    @Expose
    private String idMedic;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("notify")
    @Expose
    private Notify notify;

    public Muestra() {
    }

    public Muestra(String idPatient,String idMedic) {
        this.idPatient = idPatient;
        this.idMedic=idMedic;
        this.data=new Data();
    }

    public Muestra(String idPatient, String idMedic, String description, Data data, Notify notify) {
        this.idPatient = idPatient;
        this.idMedic = idMedic;
        this.description = description;
        this.data = data;
        this.notify = notify;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(String idMedic) {
        this.idMedic = idMedic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

}
