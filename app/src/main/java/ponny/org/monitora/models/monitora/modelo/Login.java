package ponny.org.monitora.models.monitora.modelo;

import java.io.Serializable;

public class Login implements Serializable{
 private String token;
 private float expires;


 // Getter Methods

 public String getToken() {
  return token;
 }

 public float getExpires() {
  return expires;
 }

 // Setter Methods

 public void setToken(String token) {
  this.token = token;
 }

 public void setExpires(float expires) {
  this.expires = expires;
 }
}
