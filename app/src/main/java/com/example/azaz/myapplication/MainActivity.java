package com.example.azaz.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Toast toast = Toast.makeText(getApplicationContext(),
                "test_toast", Toast.LENGTH_SHORT);
        toast.show();
        TextView tv  = (TextView) findViewById(R.id.textView);
        tv.setTextSize(25);
        //SimpleDateFormat sdf = new SimpleDateFormat()
        tv.setText(System.currentTimeMillis()+"");
        //TextClock tc =(TextClock)findViewById(R.id.textClock);
        //tc.setFormat24Hour("HH:MM:SS");

    }

}
