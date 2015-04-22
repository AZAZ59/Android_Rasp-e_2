package com.example.azaz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements Handable {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test@test.ru:test", "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

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

        /*Button guest = (Button) findViewById(R.id.button3);
        guest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecAct = new Intent(getApplicationContext(), FindGroup.class);
                startActivity(SecAct);
            }
        });*/

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
        wst.execute(new String[]{Constants.getServiceUrl() + "/group/all"});

    }

    public void handleResponse(String response) {

        EditText edEmail = (EditText) findViewById(R.id.email);
        EditText edPassword = (EditText) findViewById(R.id.password);

        edEmail.setText("");
        edPassword.setText("");

        try {

            JSONObject jso = new JSONObject(response);

            JSONArray arr = jso.getJSONArray("group");
            JSONObject o1 = arr.getJSONObject(0);

            edEmail.setText(o1.getString("id"));
            edPassword.setText(o1.getString("nameGroup"));

            Toast.makeText(this, o1.getString("nameGroup"),
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("AASS", e.getLocalizedMessage(), e);
        }

    }

}



