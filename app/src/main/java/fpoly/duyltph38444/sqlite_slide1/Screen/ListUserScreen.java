package fpoly.duyltph38444.sqlite_slide1.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import fpoly.duyltph38444.sqlite_slide1.R;
import fpoly.duyltph38444.sqlite_slide1.adapter.UAdapter;
import fpoly.duyltph38444.sqlite_slide1.dao.UserDao;
import fpoly.duyltph38444.sqlite_slide1.model.User;

public class ListUserScreen extends AppCompatActivity {
private RecyclerView rc;
UAdapter adapter;
ArrayList<User>list;
UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_screen);
        rc=findViewById(R.id.rc);
dao=new UserDao(ListUserScreen.this);
list=dao.getDs();
adapter =new UAdapter(ListUserScreen.this,list);
rc.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(ListUserScreen.this);
      linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
      rc.setLayoutManager(linearLayoutManager);
    }
}