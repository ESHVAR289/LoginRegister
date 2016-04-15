package com.einfoplanet.loginregister;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by bridgelabz5 on 15/4/16.
 */
public class RegisterActivity extends AppCompatActivity {
    EditText etName,etEmail,etPass,etConfirmPass;
    Button btnRegister;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        //Initializing the edit text values
        etName= (EditText) findViewById(R.id.etRegName);
        etEmail= (EditText) findViewById(R.id.etRegEmail);
        etPass= (EditText) findViewById(R.id.etRegPassword);
        etConfirmPass= (EditText) findViewById(R.id.etRegConPassword);

        //Initialization of the button Register
        btnRegister= (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we have to check some input conditions for edit text
                if (etName.getText().toString().equals("") ||
                        etEmail.getText().toString().equals("")||
                        etPass.getText().toString().equals(""))
                {
                    builder=new AlertDialog.Builder(RegisterActivity.this);
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

                }else if (!(etPass.getText().toString().equals(etConfirmPass.getText().toString()))){
                    builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("Password doesn't matches");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            etPass.setText("");
                            etConfirmPass.setText("");
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }

                else{
                    BackgroundTask backgroundTask=new BackgroundTask(RegisterActivity.this);
                    backgroundTask.execute("register",etName.getText().toString(),etEmail.getText().toString(),etPass.getText().toString());
                    builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Registration successful");
                    builder.setMessage("Confirmation link is sent to your e-mail");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            etName.setText("");
                            etEmail.setText("");
                            etPass.setText("");
                            etConfirmPass.setText("");
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }

            }
        });
    }


}
