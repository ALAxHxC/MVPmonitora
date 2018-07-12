package ponny.org.monitora.models.monitora.modelo;

import android.support.annotation.Nullable;

public class UserDataMedico extends UserData{
    private String description;
    private String especiality;

    public UserDataMedico() {
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
}
