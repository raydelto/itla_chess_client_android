package edu.itla.chess.androidclient.connection.notifications;

import java.util.Observable;

/**
 * Created by JarlynManuel on 29/3/15.
 */
public abstract class NotificableChessman extends Observable{
    private String positionX;
    private int positionY;
    private String ChessmanID;

    public String getChessmanID() {
        return ChessmanID;
    }

    public void setChessmanID(String chessmanID) {
        ChessmanID = chessmanID;
    }

    public String getPositions() {
        return positionX+positionY;
    }

    public void setPositions(String x, int y) {
        this.positionX = x;
        this.positionY = y;
        setChanged();
        notifyObservers();

    }
}
