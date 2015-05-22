package com.example.azaz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements Handable {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Button guest = (Button) findViewById(R.id.Guest);
        guest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecAct = new Intent(getApplicationContext(), FindGroup.class);
                startActivity(SecAct);
            }
        });

    }

    private void clearControls(){
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView.setText("");
        mPasswordView.setText("");
    }

    private void postData() {
        EditText edEmail = (EditText) findViewById(R.id.email);
        EditText edPassword = (EditText) findViewById(R.id.password);

        String password = edPassword.getText().toString();
        String email = edEmail.getText().toString();

        if (password.equals("") || email.equals("")) {
            Toast.makeText(this, "Please enter in all required fields.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, this, "Posting data...", this);

        //wst.addNameValuePair("email", email);
        //wst.addNameValuePair("password", password);

        // the passed String is the URL we will POST to
        wst.execute(new String[]{Constants.getServiceUrl() + "/user/byName?name=" + email});

    }

    public void handleResponse(String response) {
        EditText edPassword = (EditText) findViewById(R.id.password);
        try {
            JSONObject jso = new JSONObject(response);
            if (jso.getString("password").equals(edPassword.getText().toString())) {
                Intent SecAct = new Intent(getApplicationContext(), FindGroup.class);
                startActivity(SecAct);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid password or email", Toast.LENGTH_SHORT).show();
            Log.e("AASS", e.getLocalizedMessage(), e);
        }

    }

}



