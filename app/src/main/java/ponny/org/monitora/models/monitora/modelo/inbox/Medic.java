
package ponny.org.monitora.models.monitora.modelo.inbox;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medic implements Serializable
{

    @SerializedName("document")
    @Expose
    private Document_ document;
    @SerializedName("medical_center")
    @Expose
    private Object medicalCenter;
    @SerializedName("sesion")
    @Expose
    private Boolean sesion;
    @SerializedName("lastSesion")
    @Expose
    private Object lastSesion;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("firstNames")
    @Expose
    private String firstNames;
    @SerializedName("lastNames")
    @Expose
    private String lastNames;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("especiality")
    @Expose
    private String especiality;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = 3793708926325274196L;

    public Document_ getDocument() {
        return document;
    }

    public void setDocument(Document_ document) {
        this.document = document;
    }

    public Object getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(Object medicalCenter) {
        this.medicalCenter = medicalCenter;
    }

    public Boolean getSesion() {
        return sesion;
    }

    public void setSesion(Boolean sesion) {
        this.sesion = sesion;
    }

    public Object getLastSesion() {
        return lastSesion;
    }

    public void setLastSesion(Object lastSesion) {
        this.lastSesion = lastSesion;
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

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEspeciality() {
        return especiality;
    }

    public void setEspeciality(String especiality) {
        this.especiality = especiality;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
