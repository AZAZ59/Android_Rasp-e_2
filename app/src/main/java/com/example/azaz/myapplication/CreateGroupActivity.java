package com.example.azaz.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreateGroupActivity extends ActionBarActivity implements Handable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Button save = (Button) findViewById(R.id.save);
        TextView group = (TextView) findViewById(R.id.univName);
        Button createUniversity = (Button) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });

        createUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUniversity();
            }
        });

    }

    public void save() {//TODO add group and university for user

    }

    public void addGroup() { //TODO add group to university

    }

    public void addUniversity() {//TODO add university

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        wst.execute(new String[]{Constants.getServiceUrl() + ""});

    }

    @Override
    public void handleResponse(String response) {

    }
}
