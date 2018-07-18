
package ponny.org.monitora.models.monitora.modelo.muestra;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("oximeter")
    @Expose
    private Oximeter oximeter;
    @SerializedName("glucose")
    @Expose
    private Glucose glucose;
    @SerializedName("Weigth")
    @Expose
    private Integer weigth;
    @SerializedName("blodpressure")
    @Expose
    private Blodpressure blodpressure;

    public Data(){
        this.oximeter=new Oximeter(0,0,0);
        this.glucose=new Glucose(0);
        this.blodpressure=new Blodpressure(0,0,0);
        this.weigth=0;
    }
    public Data(Oximeter oximeter, Glucose glucose, Integer weigth, Blodpressure blodpressure) {
        this.oximeter = oximeter;
        this.glucose = glucose;
        this.weigth = weigth;
        this.blodpressure = blodpressure;
    }

    public Oximeter getOximeter() {
        return oximeter;
    }

    public void setOximeter(Oximeter oximeter) {
        this.oximeter = oximeter;
    }

    public Glucose getGlucose() {
        return glucose;
    }

    public void setGlucose(Glucose glucose) {
        this.glucose = glucose;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Blodpressure getBlodpressure() {
        return blodpressure;
    }

    public void setBlodpressure(Blodpressure blodpressure) {
        this.blodpressure = blodpressure;
    }

}
