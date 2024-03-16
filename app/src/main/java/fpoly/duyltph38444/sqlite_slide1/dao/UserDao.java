package fpoly.duyltph38444.sqlite_slide1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.duyltph38444.sqlite_slide1.database.DbHelper;
import fpoly.duyltph38444.sqlite_slide1.model.User;

public class UserDao {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public UserDao(Context context) {
        dbHelper=new DbHelper(context);
        database=dbHelper.getWritableDatabase();
    }
    public long insertUser(User user){
        ContentValues values=new ContentValues();
        database=dbHelper.getWritableDatabase();
//        values.put("ID",user.getId());
        values.put("NAME",user.getName());
        values.put("ADDRESS",user.getAddress());
        values.put("GENDER",user.getGender());
        long check =database.insert("SINHVIEN",null,values);
        if (check<=0){
            return -1;
        }
        return 1;
    }
    //lay ra
    public ArrayList<User>getDs(){
        ArrayList<User>list=new ArrayList<>();
        database=dbHelper.getReadableDatabase();
       Cursor cursor= database.rawQuery("SELECT *FROM SINHVIEN",null);
       if (cursor.getCount()>0){
           cursor.moveToFirst();
           do {
               list.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
           }while (cursor.moveToNext());
       }
return list;
    }
    public long updateUsre(User user){
        ContentValues values=new ContentValues();
        values.put("ID",user.getId());
        values.put("NAME",user.getName());
        values.put("ADDRESS",user.getAddress());
        values.put("GENDER",user.getGender());
        long check =database.update("SINHVIEN",values,"ID=?",new String[]{String.valueOf(user.getId())});
        if (check <= 0){
            return -1;
        }
        return 1;
    }
    public long deleteUser(int id){
        long check =database.delete("SINHVIEN","ID=?",new String[]{String.valueOf(id)});
        if (check<=0){
            return -1;
        }
        return 1;
    }
}
