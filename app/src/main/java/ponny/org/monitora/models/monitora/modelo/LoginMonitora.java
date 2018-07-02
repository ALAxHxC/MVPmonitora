package ponny.org.monitora.models.monitora.modelo;

import java.io.Serializable;

public class LoginMonitora  implements Serializable {
    private Login login;
    private User user;

    public LoginMonitora() {
    }

    public Login getLoginObject() {
        return login;
    }

    public void setLoginObject(Login loginObject) {
        login = loginObject;
    }

    public User getUserObject() {
        return user;
    }

    public void setUserObject(User userObject) {
        user = userObject;
    }

    @Override
    public String toString() {
        return "LoginMonitora{" +
                "LoginObject=" + login.toString() +
                ", UserObject=" + user.toString() +
                '}';
    }
}


