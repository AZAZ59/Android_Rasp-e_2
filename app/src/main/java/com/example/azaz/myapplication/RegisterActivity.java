package com.example.azaz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends Activity implements Handable {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner mSpinner_university;
    private Spinner mSpinner_group;
    private ArrayList<String> groups = new ArrayList<>();
    private ArrayList<String> university = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private int idgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mSpinner_university = (Spinner) findViewById(R.id.spinner);
        mSpinner_university.setPrompt("University");
        mSpinner_group = (Spinner) findViewById(R.id.spinner2);
        mSpinner_group.setEnabled(true);
        mSpinner_university.setEnabled(true);

        new WebServiceTask(WebServiceTask.GET_TASK, this, "Posting data...", new Handable() {
            @Override
            public void handleResponse(String response) {
                try {
                    JSONObject jso = new JSONObject(response);
                    JSONArray jsa = jso.getJSONArray("university");
                    for (int i = 0; i < jsa.length(); i++) {
                        university.add(jsa.getJSONObject(i).getString("name"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, university.toArray(new String[0]));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_university.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute(new String[]{Constants.getServiceUrl() + "/university/all"});


        mSpinner_university.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new WebServiceTask(WebServiceTask.GET_TASK, RegisterActivity.this, "Posting data...", new Handable() {
                    @Override
                    public void handleResponse(String response) {
                        try {
                            JSONObject jso = new JSONObject(response);
                            JSONArray jsa = jso.getJSONArray("group");
                            for (int i = 0; i < jsa.length(); i++) {
                                groups.add(jsa.getJSONObject(i).getString("nameGroup"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, groups.toArray(new String[0]));
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            mSpinner_group.setAdapter(adapter);
                            //mSpinner_group.setSelection(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).execute(new String[]{Constants.getServiceUrl() + "/group/byUniversity?university=" + university.get(position)});
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSpinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new WebServiceTask(WebServiceTask.GET_TASK, RegisterActivity.this, "Posting data...", new Handable() {
                    @Override
                    public void handleResponse(String response) {
                        try {
                            idgroup = new JSONObject(response).getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).execute(
                        new String[]{Constants.getServiceUrl() + "/group/byName?name=" + groups.get(position)}
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSpinner_university.getSelectedItemId() < 0) {
                    //postDataWithoutAll();//TODO
                } else {
                    if (mSpinner_group.getSelectedItemId() < 0) {
                        postDataWithoutGroup();
                    } else {
                        postData();
                    }
                }
            }


        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mProgressView.setEnabled(true);
    }

    private void clearControls() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView.setText("");
        mPasswordView.setText("");
    }

    private void postData() {
        EditText edEmail = (EditText) findViewById(R.id.email);
        EditText edPassword = (EditText) findViewById(R.id.password);

        String password = edPassword.getText().toString();
        String login = edEmail.getText().toString();

        if (password.equals("") || login.equals("")) {
            Toast.makeText(this, "Please enter in all required fields.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", login);
        editor.putString("password", password);
        editor.putLong("group", idgroup);
        editor.commit();
        Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, this, "Posting data...", this);
        wst.execute(new String[]{Constants.getServiceUrl() + "/user/create?name=" + login + "&pass=" + password + "&rights=" + 1 + "&id_group=" + idgroup});//TODO

    }

    private void postDataWithoutGroup() {
        EditText edEmail = (EditText) findViewById(R.id.email);
        EditText edPassword = (EditText) findViewById(R.id.password);
        String password = edPassword.getText().toString();
        String login = edEmail.getText().toString();

        if (password.equals("") || login.equals("")) {
            Toast.makeText(this, "Please enter in all required fields.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, this, "Posting data...", this);
        wst.execute(new String[]{Constants.getServiceUrl() + "/user/create?name=" + login + "&pass=" + password + "&rights=" + 1});//TODO

    }

    public void handleResponse(String response) {
        try {
            JSONObject jso = new JSONObject(response);
            EditText edPassword = (EditText) findViewById(R.id.password);
            if (jso.getString("password").equals(edPassword.getText().toString())) {
                Intent SecAct = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(SecAct);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid password or email", Toast.LENGTH_SHORT).show();
            Log.e("AASS", e.getLocalizedMessage(), e);
        }

    }

}



