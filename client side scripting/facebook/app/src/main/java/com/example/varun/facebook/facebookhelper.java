package com.example.varun.facebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.EditText;

/**
 * Created by varun on 9/1/2016.
 */
public class facebookhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="FB.DB";
    //public static final String TABLE_NAME="REGISTER";
   // public static final String COL1="ID";
    //public static final String COL2="USER_NAME";
   // public static final String COL3="PASSWORD";
    public Cursor getAllData;

    public facebookhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table REGISTER(id integer primary key autoincrement, username text, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS REGISTER");
        onCreate(db);
    }

  public boolean insertData(String email, String password)
  {
      long result=-1;
      SQLiteDatabase db=this.getWritableDatabase();
      ContentValues mycontent =new ContentValues();
      mycontent.put("username",email);
      mycontent.put("password", password);
      try
      {
          result = db.insert("REGISTER", null, mycontent);
      }
      catch (SQLException e)
      {
          Log.d("message",e.getLocalizedMessage().toString());
      }
      if (result==-1)
      {
          return false;

      }
      else
          return true;

  }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from REGISTER", null);
        return res;
    }

    public Cursor getAllData() {
        return null;
    }
    public String getEntry(String mail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       Cursor res=db.query("REGISTER", null, "username=?", new String[]{mail}, null, null, null);
        if (res.getCount()<1)
        {
            res.close();
            return "NOT EXISTS";
       }
       res.moveToFirst();
       String pwd=res.getString(res.getColumnIndex("password"));
       res.close();
       return pwd;
   }



}
