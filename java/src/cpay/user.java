/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpay;

/**
 *
 * @author chilse
 */
public class user {

    private boolean admin = false;
    private boolean guest = true;
    private String name = "";
    private int userId = -1;
    private int userCard = -1;

    public user(boolean admin, boolean guest, int id, int cardid, String name) {
        this.admin = admin;
        this.guest = guest;
        this.userId = id;
        this.userCard = cardid;
        this.name = name;
    }

    public int getID() {
        return userId;
    }

    public int getCardID() {
        return userCard;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isGuest() {
        return guest;
    }
}
