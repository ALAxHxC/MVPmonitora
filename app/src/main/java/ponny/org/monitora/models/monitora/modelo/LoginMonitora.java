package ponny.org.monitora.models.monitora.modelo;

public class LoginMonitora {
 Login LoginObject;
 User UserObject;


 // Getter Methods 

 public Login getLogin() {
  return LoginObject;
 }

 public User getUser() {
  return UserObject;
 }

 // Setter Methods 

 public void setLogin(Login loginObject) {
  this.LoginObject = loginObject;
 }

 public void setUser(User userObject) {
  this.UserObject = userObject;
 }
}

