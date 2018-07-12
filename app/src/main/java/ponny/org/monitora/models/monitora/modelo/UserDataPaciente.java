package ponny.org.monitora.models.monitora.modelo;

public class UserDataPaciente extends UserData{
    private String idMedic;
    private Object[] pathologys;

    public UserDataPaciente() {
    }


    public String getIdMedic() {
        return idMedic;
    }


    public void setIdMedic(String idMedic) {
        this.idMedic = idMedic;
    }


    public Object[] getPathologys() {
        return pathologys;
    }


    public void setPathologys(Object[] pathologys) {
        this.pathologys = pathologys;
    }
}
