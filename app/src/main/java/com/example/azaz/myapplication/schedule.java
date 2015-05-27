package com.example.azaz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entities.Schedule;
import Entities.Schedule_item;


public class schedule extends ActionBarActivity implements Handable {
    List<Schedule_item> schedule = Schedule.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mSharedPreferences = getPreferences(MODE_PRIVATE);
        Long group = mSharedPreferences.getLong("group",-1);
        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, this, "Posting data...", this);
        wst.execute(new String[]{Constants.getServiceUrl() + "/schedule/byId?id=" + group});



        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();

        

        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new SimplePageAdapter(pages));
        viewPager.setCurrentItem(0);

        setContentView(viewPager);

//        setContentView(R.layout.activity_schedule);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
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


    @Override
    public void handleResponse(String response) {

        EditText edPassword = (EditText) findViewById(R.id.password);
        try {
            JSONObject jso = new JSONObject(response);
            if (jso.has("rasp")) {
                JSONArray objects = jso.getJSONArray("rasp");
                for (int i = 0; i < objects.length(); i++) {
                    JSONObject obj = objects.getJSONObject(i);
                    Schedule_item item = new Schedule_item();
                    item.setName(obj.getString("name_pair"));
                    item.setWeek(obj.getBoolean("odd"));
                    item.setNumber(obj.getInt("number_of_pair"));
                    item.setTeacher(obj.getString("teacher"));
                    schedule.add(item);
                }


            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid password or email", Toast.LENGTH_SHORT).show();
            Log.e("AASS", e.getLocalizedMessage(), e);
        }

    }
}
