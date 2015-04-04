package edu.itla.chess.androidclient.entities;

/**
 * Created by Manuel Inoa on 3/29/15.
 */
public class User {
    private String nickname;
    private boolean available;

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User(String nickname, boolean available) {
        super();
        this.nickname = nickname;
        this.available = available;
    }
}
