package fpoly.duyltph38444.sqlite_slide1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public  static final String NAME_DB = "SinhVien";
    public  static final int VERSION_DB =11;
    public DbHelper( Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Hàm chạy khi chưa có database
        //hàm này tạo bảng
        String creaTable="CREATE TABLE SINHVIEN(" +
                "ID INTEGER  PRIMARY KEY AUTOINCREMENT ," +
                "NAME TEXT ," +
                "ADDRESS TEXT ," +
                "GENDER TEXT )";
        db.execSQL(creaTable);
//        String insert ="INSERT INTO SINHVIEN (ID,NAME,ADDRESS,GENDER) VALUES" +
//                " ('1','duy','thanh hoa','nam')," +
//                "('2','nam','thanh long','nam')," +
//                "('3','linh','Ha Noi','nu');";
//        db.execSQL(insert);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //HAM DUNG CHẠY VESION  THAY ĐỔI
        //DÙNG UPDATE DATABASE


    if (oldVersion!=newVersion){
    db.execSQL("DROP TABLE IF EXISTS SINHVIEN ");
    onCreate(db);
}
    }
}
