package ponny.org.monitora.models.monitora.modelo;

import java.io.Serializable;

public class UserData  implements Serializable {
 private String _id;
 Document document;
 private boolean sesion;
 private String lastSesion = null;
 private String firstNames;
 private String lastNames;
 private String date;
 private String description;
 private String especiality;
 private String createAt;
 private String updateAt;
 private float __v;


 // Getter Methods

 public String get_id() {
  return _id;
 }

 public Document getDocument() {
  return document;
 }

 public boolean getSesion() {
  return sesion;
 }

 public String getLastSesion() {
  return lastSesion;
 }

 public String getFirstNames() {
  return firstNames;
 }

 public String getLastNames() {
  return lastNames;
 }

 public String getDate() {
  return date;
 }

 public String getDescription() {
  return description;
 }

 public String getEspeciality() {
  return especiality;
 }

 public String getCreateAt() {
  return createAt;
 }

 public String getUpdateAt() {
  return updateAt;
 }

 public float get__v() {
  return __v;
 }

 // Setter Methods

 public void set_id(String _id) {
  this._id = _id;
 }

 public void setDocument(Document documentObject) {
  this.document = documentObject;
 }

 public void setSesion(boolean sesion) {
  this.sesion = sesion;
 }

 public void setLastSesion(String lastSesion) {
  this.lastSesion = lastSesion;
 }

 public void setFirstNames(String firstNames) {
  this.firstNames = firstNames;
 }

 public void setLastNames(String lastNames) {
  this.lastNames = lastNames;
 }

 public void setDate(String date) {
  this.date = date;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public void setEspeciality(String especiality) {
  this.especiality = especiality;
 }

 public void setCreateAt(String createAt) {
  this.createAt = createAt;
 }

 public void setUpdateAt(String updateAt) {
  this.updateAt = updateAt;
 }

 public void set__v(float __v) {
  this.__v = __v;
 }
}
