
package ponny.org.monitora.models.monitora.modelo.pacientes;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPatients implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("entity")
    @Expose
    private List<Entity> entity = null;
    private final static long serialVersionUID = 383510787675111076L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Entity> getEntity() {
        return entity;
    }

    public void setEntity(List<Entity> entity) {
        this.entity = entity;
    }

}
