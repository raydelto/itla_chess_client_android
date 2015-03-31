package edu.itla.chess.androidclient.connection.notifications;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import edu.itla.chess.androidclient.connection.Connector;

/**
 * Created by JarlynManuel on 29/3/15.
 */
public class ChessmanObserver implements Observer{
    private Connector connector;

    public ChessmanObserver() {
        connector = new Connector();
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data instanceof NotificableChessman) try {
            NotificableChessman chessman = ((NotificableChessman) data);
            String movement = chessman.getChessmanID() + chessman.getPositions();
            connector.getSocketConnection().getOutputStream().write(movement.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
