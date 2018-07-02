package ponny.org.monitora.models.monitora.modelo;

import java.io.Serializable;

public class Document  implements Serializable {
 private String identification;
 private String type;


 // Getter Methods

 public String getIdentification() {
  return identification;
 }

 public String getType() {
  return type;
 }

 // Setter Methods

 public void setIdentification(String identification) {
  this.identification = identification;
 }

 public void setType(String type) {
  this.type = type;
 }
}
