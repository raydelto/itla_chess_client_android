package edu.itla.chess.androidclient.utils.viewholders;

import android.view.View;
import android.widget.TextView;

import edu.itla.chess.androidclient.R;
import edu.itla.chess.androidclient.entities.User;

/**
 * Created by Manuel Inoa on 3/29/15.
 */
public class UserViewHolder implements ViewHolder {

    private View view;
    private TextView userTextView;

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void setView(View view) {
        this.view = view;

        this.userTextView = (TextView) view
                .findViewById(R.id.userListName);
    }

    @Override
    public void setValues(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;

            this.userTextView.setText(user.getNickname());
        }

    }
}
