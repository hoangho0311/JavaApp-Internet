package KTCK.accountSignUp;

import java.io.Serializable;

public class signupAccount implements Serializable {
    int id;
    String nameSU, userSU, passSU;
    public signupAccount(){}

    public signupAccount(int id, String nameSU, String userSU, String passSU) {
        this.id = id;
        this.nameSU = nameSU;
        this.userSU = userSU;
        this.passSU = passSU;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSU() {
        return nameSU;
    }

    public void setNameSU(String nameSU) {
        this.nameSU = nameSU;
    }

    public String getUserSU() {
        return userSU;
    }

    public void setUserSU(String userSU) {
        this.userSU = userSU;
    }

    public String getPassSU() {
        return passSU;
    }

    public void setPassSU(String passSU) {
        this.passSU = passSU;
    }
}
