package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 09-May-18.
 */

public class Contacts {

    private String Username;
    private String Name;
    private String Category;
    private String number;

    public Contacts() {
    }

    public Contacts(String username, String name, String category, String number) {
        Username = username;
        Name = name;
        Category = category;
        this.number = number;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
