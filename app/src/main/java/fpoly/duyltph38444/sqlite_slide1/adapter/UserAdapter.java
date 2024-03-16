package fpoly.duyltph38444.sqlite_slide1.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.duyltph38444.sqlite_slide1.R;
import fpoly.duyltph38444.sqlite_slide1.dao.UserDao;
import fpoly.duyltph38444.sqlite_slide1.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {
    Context context;
    ArrayList<User> list;
    UserDao userDao;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        userDao =new UserDao(context);

    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater .from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, @SuppressLint("RecyclerView") int position) {
holder.tvName.setText(list.get(position).getName());
        holder.tvAddress.setText(list.get(position).getAddress());
holder.tvGender.setText(list.get(position).getGender());


holder.imgDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int id= list.get(holder.getAdapterPosition()).getId();
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle("CẢNH BÁO");
        builder.setIcon(R.drawable.img_3);
        builder.setMessage("Bạn muốn xóa không");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                long check =userDao.deleteUser(id);
                if (check>0){
                    Toast.makeText(context, "Delete thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list=userDao.getDs();
                    notifyItemRemoved(holder.getAdapterPosition());
                }else {
                    Toast.makeText(context, "Delete thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Không delete nữa", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog =builder.create();
        dialog.show();
    }
});
holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view1 =inflater .inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setView(view1);
         EditText edId,edName,edAddress,edGender;
        edId =view1.findViewById(R.id.edId);
        edName =view1.findViewById(R.id.edName);
        edAddress =view1.findViewById(R.id.edAddress);
        edGender =view1.findViewById(R.id.edGender);

        ///load data len aleardialog
        edId.setText(String.valueOf(list.get(position).getId()));
        edName.setText(String.valueOf(list.get(position).getName()));
        edAddress.setText(String.valueOf(list.get(position).getAddress()));
        edGender.setText(String.valueOf(list.get(position).getGender()));

        builder.setTitle("UPDATE");
        builder.setIcon(R.drawable.img_4);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
  User user =new User();
  user .setId(Integer.parseInt(edId.getText().toString()));
  user.setName(edName.getText().toString());
  user.setAddress(edAddress.getText().toString());
  user.setGender(edGender.getText().toString());

  long check =userDao.updateUsre(user);
  if (check > 0){
      Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
  }
  else {
      Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
  }
  list.set(position,user);
             notifyItemChanged(holder.getAdapterPosition());
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog =builder.create();
        dialog.show();
    }
});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolderUser extends RecyclerView.ViewHolder{
        TextView tvName,tvAddress,tvGender;
        ImageView imgDelete,imgUpdate;


//    convertView = LayoutInflater.from((Activity) context).inflate(R.layout.item_user, parent, false);
//    TextView tvName = convertView.findViewById(R.id.tvName);
//    TextView tvAddres = convertView.findViewById(R.id.tvAddress);
//    TextView tvGender = convertView.findViewById(R.id.tvGender);
//        ImageView imgDelete =convertView.findViewById(R.id.imgDelete);
//        ImageView imgUpdate =convertView.findViewById(R.id.imgUpdate);
//
//User user =list.get(position);

    public ViewHolderUser(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tvName);
        tvAddress=itemView.findViewById(R.id.tvAddress);
        tvGender=itemView.findViewById(R.id.tvGender);
        imgDelete=itemView.findViewById(R.id.imgDelete);
        imgUpdate=itemView.findViewById(R.id.imgUpdate);
    }
//tvName.setText(user.getName());
//tvAddres.setText(user.getAddress());
//tvGender.setText(user.getGender());
//        return convertView;
    }

//    public class Vie
}
