package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 29-Apr-18.
 */

public class SignUpClass {

    private String Username;
    private String Password;
    private int Age;
    private boolean Status;

    public SignUpClass() {
    }

    public SignUpClass(String username, String password, int age,Boolean status) {
        Username = username;
        Password = password;
        Age = age;
        Status= status;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
