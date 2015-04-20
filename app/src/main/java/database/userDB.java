package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Grand on 16.04.2015.
 */
public class userDB extends SQLiteOpenHelper {

    private static final String db_name="user_db.db";
    private static Integer id_user;
    private static Integer id_university;
    private static Integer id_group;
    private static Integer rights;


    public userDB(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void write(Integer id_user, Integer id_university, Integer id_group, Integer rights){
        String query = "INSERT INTO " + userDB.db_name
                + " (" + userDB.id_user+"," + userDB.id_university+","+ userDB.id_group+","+userDB.rights + ") VALUES ('"
                + id_user+","+id_university+","+id_group+","+rights + "')";


    }
}
