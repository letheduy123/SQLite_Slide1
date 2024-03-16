package fpoly.duyltph38444.sqlite_slide1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.duyltph38444.sqlite_slide1.R;
import fpoly.duyltph38444.sqlite_slide1.dao.UserDao;
import fpoly.duyltph38444.sqlite_slide1.model.User;

public class UAdapter extends RecyclerView.Adapter<UAdapter.ViewHolder> {
   private Context context;
    private ArrayList<User> list;
private UserDao dao;

    public UAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        dao =new UserDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user =list.get(position);

holder.tvName.setText(user.getName());
holder.tvAddress.setText(user.getAddress());
holder.tvGender.setText(user.getGender());
holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
holder.imgDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      long delete =dao.deleteUser(user.getId());
      if (delete > 0){
          Toast.makeText(context, "Delete thanh cong", Toast.LENGTH_SHORT).show();
          list.remove(position);

          notifyDataSetChanged();
      }
      else {
          Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
      }
    }
});

    }

    @Override
    public int getItemCount() {

            return list.size();

    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
TextView tvName,tvGender,tvAddress;
ImageView imgUpdate,imgDelete ,img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvGender=itemView.findViewById(R.id.tvGender);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgUpdate=itemView.findViewById(R.id.imgUpdate);
            img=itemView.findViewById(R.id.img);

        }


    }
}
