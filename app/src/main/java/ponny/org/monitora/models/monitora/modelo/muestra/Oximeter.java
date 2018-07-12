
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oximeter implements Serializable
{

    @SerializedName("pi")
    @Expose
    private Integer pi;
    @SerializedName("spo2")
    @Expose
    private Integer spo2;
    @SerializedName("pulse")
    @Expose
    private Integer pulse;

    public Oximeter(Integer pi, Integer spo2, Integer pulse) {
        this.pi = pi;
        this.spo2 = spo2;
        this.pulse = pulse;
    }

    public Integer getPi() {
        return pi;
    }

    public void setPi(Integer pi) {
        this.pi = pi;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }
    public boolean datosValidos() {return !(this.spo2 >= 127 || this.pulse >= 255 || this.pi == 0.0);}
}
