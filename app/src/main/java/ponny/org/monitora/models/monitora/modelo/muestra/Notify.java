
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notify implements Serializable
{

    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("description")
    @Expose
    private String description;
    public Notify(){
        this.subject="Envio";
        this.description="Envio";
    }
    public Notify(String subject, String description) {
        this.subject = subject;
        this.description = description;
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

}
