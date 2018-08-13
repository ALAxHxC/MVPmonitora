
package ponny.org.monitora.models.monitora.modelo.pacientes;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entity implements Serializable
{

    @SerializedName("document")
    @Expose
    private Document document;
    @SerializedName("sesion")
    @Expose
    private Boolean sesion;
    @SerializedName("lastSesion")
    @Expose
    private Object lastSesion;
    @SerializedName("pathologys")
    @Expose
    private List<Object> pathologys = null;
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
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = -2052501709925908864L;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
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

    public List<Object> getPathologys() {
        return pathologys;
    }

    public void setPathologys(List<Object> pathologys) {
        this.pathologys = pathologys;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
