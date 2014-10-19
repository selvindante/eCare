package ru.tsystems.tsproject.ecare;

/**
 * Created by Selvin
 * on 19.10.2014.
 */
public class Session {
    private static Session instance;
    private static String role;
    private static boolean isOn = true;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null)
        {
            instance = new Session();
        }
        return instance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
}
