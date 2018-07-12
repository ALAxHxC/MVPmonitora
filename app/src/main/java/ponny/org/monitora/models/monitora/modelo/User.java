package ponny.org.monitora.models.monitora.modelo;

import java.io.Serializable;

public class User  implements Serializable {
 private String _id;
 private String idFirebase = null;
 private String username;
 private String password;
 private String userDetails;
 private int userTypeDescription;
 private float __v;
 UserData UserDataObject;


 // Getter Methods

 public String get_id() {
  return _id;
 }

 public String getIdFirebase() {
  return idFirebase;
 }

 public String getUsername() {
  return username;
 }

 public String getPassword() {
  return password;
 }

 public String getUserDetails() {
  return userDetails;
 }

 public int getUserTypeDescription() {
  return userTypeDescription;
 }

 public float get__v() {
  return __v;
 }

 public UserData getUserData() {
  return UserDataObject;
 }

 // Setter Methods

 public void set_id(String _id) {
  this._id = _id;
 }

 public void setIdFirebase(String idFirebase) {
  this.idFirebase = idFirebase;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public void setUserDetails(String userDetails) {
  this.userDetails = userDetails;
 }

 public void setUserTypeDescription(int userTypeDescription) {
  this.userTypeDescription = userTypeDescription;
 }
 public void set__v(float __v) {
  this.__v = __v;
 }

 public void setUserData(UserData userDataObject) {
  this.UserDataObject = userDataObject;
 }

 @Override
 public String toString() {
  return "User{" +
          "_id='" + _id + '\'' +
          ", idFirebase='" + idFirebase + '\'' +
          ", username='" + username + '\'' +
          ", password='" + password + '\'' +
          '}';
 }
}
