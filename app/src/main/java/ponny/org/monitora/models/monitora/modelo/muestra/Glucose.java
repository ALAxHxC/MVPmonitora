
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Glucose implements Serializable
{

    @SerializedName("gluco")
    @Expose
    private Integer gluco;

    public Glucose(Integer gluco) {
        this.gluco = gluco;
    }

    public Integer getGluco() {
        return gluco;
    }

    public void setGluco(Integer gluco) {
        this.gluco = gluco;
    }

}
