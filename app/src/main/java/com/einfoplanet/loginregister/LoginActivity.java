package com.einfoplanet.loginregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView textViewSignUp;
    Button btnLogin;
    EditText etLogPass,etLogEmail;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogEmail=(EditText)findViewById(R.id.etLoginEmail);
        etLogPass=(EditText)findViewById(R.id.etLoginPassword);

        //initializing the sign up textView
        textViewSignUp=(TextView)findViewById(R.id.txtViewSignUp);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( etLogEmail.getText().toString().equals("")||
                        etLogPass.getText().toString().equals("")){
                    builder=new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("Please fill all the fields.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }else{
                    BackgroundTask backgroundTask=new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute("login",etLogEmail.getText().toString(),etLogEmail.getText().toString());

                    builder=new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Login successful");
                    builder.setMessage("You are now online");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            etLogEmail.setText("");
                            etLogPass.setText("");
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }
            }
        });
    }
}
