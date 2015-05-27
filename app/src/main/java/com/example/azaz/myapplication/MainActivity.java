package com.example.azaz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        findViewById(R.id.schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecAct = new Intent(getApplicationContext(), schedule.class);
                startActivity(SecAct);
            }
        });

        findViewById(R.id.registr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecAct = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(SecAct);
            }
        });

        findViewById(R.id.createGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecAct = new Intent(getApplicationContext(), CreateGroupActivity.class);
                startActivity(SecAct);
            }
        });

        SharedPreferences mSharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        Long group = mSharedPreferences.getLong("group", -1);
        if (group == -1) {
            Toast.makeText(this, "Пожалуйста, войдите в систему", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void PrintTime(View view) {
        Intent SecAct = new Intent(getApplicationContext(), FindGroup.class);
        startActivity(SecAct);

        /*Toast toast = Toast.makeText(getApplicationContext(),
                "test_toast", Toast.LENGTH_SHORT);
        toast.show();
        TextView tv  = (TextView) findViewById(R.id.textView);
        tv.setTextSize(25);
        //SimpleDateFormat sdf = new SimpleDateFormat()
        tv.setText(System.currentTimeMillis()+"");
        //TextClock tc =(TextClock)findViewById(R.id.textClock);
        //tc.setFormat24Hour("HH:MM:SS");*/

    }


}
