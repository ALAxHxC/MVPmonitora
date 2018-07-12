
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blodpressure implements Serializable
{

    @SerializedName("sys")
    @Expose
    private Integer sys;
    @SerializedName("dias")
    @Expose
    private Integer dias;
    @SerializedName("pulse")
    @Expose
    private Integer pulse;

    public Blodpressure(Integer sys, Integer dias, Integer pulse) {
        this.sys = sys;
        this.dias = dias;
        this.pulse = pulse;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

}
