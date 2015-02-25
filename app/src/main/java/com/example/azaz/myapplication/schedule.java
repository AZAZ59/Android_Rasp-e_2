package com.example.azaz.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class schedule extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();

        for (int i = 1; i < 7; i++) {
            View page = inflater.inflate(R.layout.activity_list__schedule, null);
            TextView textView = (TextView) page.findViewById(R.id.TEST_TEXT);
            textView.setText("День " + i);


            GridView gw = (GridView) page.findViewById(R.id.gridView);
            String[] arr = new String[45];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (j % 2 == 0 ? "AAAAA" : "BBBB") + "   " + j;
            }
            //DataAdapter da = new DataAdapter(arr,getApplicationContext(),R.id.TEST_TEXT);
            ArrayAdapter<String> st = new ArrayAdapter<String>(this, R.layout.activity_schedule__item, R.id.schedule_Item_text, arr);
            gw.setAdapter(st);


            pages.add(page);
        }

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
}
