package com.example.azaz.myapplication;

/**
 * Created by azaz on 25.02.15.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<String> {

    private static String[] mContacts;

    Context mContext;

    // Конструктор
    public DataAdapter(String[] arr, Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        mContacts = arr;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        if (position < mContacts.length) {
            label.setText(mContacts[position]);
        } else {
            label.setText("=======");
        }
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String GetItem(int position) {
        if (position < mContacts.length) {
            return mContacts[position];
        } else return "+++++++++++++++";

    }

}
