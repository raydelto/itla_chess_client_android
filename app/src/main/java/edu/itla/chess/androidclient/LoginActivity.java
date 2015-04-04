package edu.itla.chess.androidclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.itla.chess.androidclient.network.ServerClient;

/**
 * Created by Manuel Inoa on 3/28/15.
 */
public class LoginActivity extends Activity {

    private Button btnSignIn;
    private EditText txtNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        btnSignIn = (Button)findViewById(R.id.login_button);
        txtNickname = (EditText)findViewById(R.id.loginEditText);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = txtNickname.getText().toString();
                if(nickname.trim().equals("") || nickname.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "The username cannot be empty!!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Waiting for the Server response", Toast.LENGTH_SHORT).show();

                    ServerClient.getInstance().sendMessage("/login&".concat(nickname));
                    btnSignIn.setEnabled(false);

                    String message = ServerClient.getInstance().getMessageFromServer();
                    if(message.contains("already in use")){
                        Toast.makeText(getApplicationContext(), "This username is already in use!", Toast.LENGTH_LONG).show();
                        btnSignIn.setEnabled(true);
                    }else{
                        Intent i = new Intent(LoginActivity.this, UsersActivity.class);

                        i.putExtra("username", nickname);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }
}
