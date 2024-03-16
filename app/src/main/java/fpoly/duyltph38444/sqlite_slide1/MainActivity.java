package fpoly.duyltph38444.sqlite_slide1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.duyltph38444.sqlite_slide1.Screen.ListUserScreen;
import fpoly.duyltph38444.sqlite_slide1.adapter.UAdapter;

import fpoly.duyltph38444.sqlite_slide1.dao.UserDao;
import fpoly.duyltph38444.sqlite_slide1.model.User;

public class MainActivity extends AppCompatActivity {
private EditText edName,edAddress,edGender;
    private Button btnAdd,btnGet ;
    private UserDao Userdao;
//    RecyclerView rcUser;
    ArrayList<User>list;
    UAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
btnGet=findViewById(R.id.btnGet);
        Userdao =new UserDao(this);
        edName=findViewById(R.id.edName);
        edAddress=findViewById(R.id.edAddress);
        edGender=findViewById(R.id.edGender);
        btnAdd=findViewById(R.id.btnAdd);
//        rcUser=findViewById(R.id.rcUser);
        list=Userdao.getDs();
        adapter =new UAdapter(MainActivity.this,list);

//        btnGet=findViewById(R.id.btnGet);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
//        rcUser.setLayoutManager(linearLayoutManager);
//        rcUser.setAdapter(adapter);


btnGet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(MainActivity.this, ListUserScreen.class);
        startActivity(intent);

    }
});
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edName.getText().toString();
                String anddess=edAddress.getText().toString();
                String gender=edGender.getText().toString();
                if (name.isEmpty()||anddess.isEmpty()||gender.isEmpty()){
                    Toast.makeText(MainActivity.this, "Khong de trong du lieu", Toast.LENGTH_SHORT).show();
                    if (name.isEmpty()){
                        edName.setError("Khong de trong name");
                    }
                    if (anddess.isEmpty()){
                        edAddress.setError("Khong de trong anddess");
                    }
                    if (gender.isEmpty()){
                        edGender.setError("Khong de trong gender");
                    }

                }
                else {
                    User user=new User(1,name,anddess,gender);
                    long check=Userdao.insertUser(user);
                    if (check<0){
                        Toast.makeText(MainActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    }

                }
                list =Userdao.getDs();
                adapter =new UAdapter(MainActivity.this,list);
//                rcUser.setLayoutManager(linearLayoutManager);
//                rcUser.setAdapter(adapter);
                reset();
            }
        });
//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             ArrayList<User> list=Userdao .getDs();
//       for ( User user:list){
//           Log.d( "user",user.getName());
//       }
//            }
//        });
    }
    public void reset(){
        edName.setText("");
        edAddress.setText("");
        edGender.setText("");
    }
}