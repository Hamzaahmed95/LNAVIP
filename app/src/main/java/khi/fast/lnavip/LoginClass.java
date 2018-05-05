package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 30-Apr-18.
 */

public class LoginClass {

    String Username;
    Boolean Status;

    public LoginClass(String username, Boolean status) {
        Username = username;
        Status = status;
    }

    public LoginClass() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
