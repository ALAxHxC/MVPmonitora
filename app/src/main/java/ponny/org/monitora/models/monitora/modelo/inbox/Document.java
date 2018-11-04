
package ponny.org.monitora.models.monitora.modelo.inbox;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document implements Serializable
{

    @SerializedName("identification")
    @Expose
    private String identification;
    @SerializedName("type")
    @Expose
    private String type;
    private final static long serialVersionUID = -5379337906762189364L;

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
