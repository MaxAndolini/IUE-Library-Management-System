package main;

public class LoginInfo {
    private static int uID;

    public static void SetID(int id) {
        uID = id;
    }

    public static int getID() {
        return uID;
    }
}
