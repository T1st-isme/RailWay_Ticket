package com.example.railwayticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.R;
import com.example.railwayticket.Utils.Utils;
import com.example.railwayticket.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    ArrayList<User> lstUser;
    Context context;
    UserCallback userCallback;

    //Khởi tạo hàm tương tác vs lstUser
    public UserAdapter(ArrayList<User> lstUser) {
        this.lstUser = lstUser;
    }

    public void setCallback(UserCallback userCallback) {
        this.userCallback = userCallback;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Nạp layout cho View view biểu diễn phần tử User
        View userView = inflater.inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        //Lấy từng item của dữ liệu
        User item = lstUser.get(position);
        //gán item vào từng view
        holder.ivAvatarC.setImageBitmap(Utils.convertToBitmapFromAssets(context, item.getAvatar()));
        holder.tvNameC.setText(item.getName());
        holder.tvPassC.setText(item.getPassword());
        //Bắt sự kiện
        holder.ivDeleteC.setOnClickListener(v -> userCallback.onItemDeleteClicked(item, position));
        holder.ivEditC.setOnClickListener(v -> userCallback.onItemEditClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatarC, ivDeleteC, ivEditC;
        TextView tvNameC,tvPassC;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatarC = itemView.findViewById(R.id.ivAvatar);
            ivDeleteC = itemView.findViewById(R.id.ivDelete);
            ivEditC = itemView.findViewById(R.id.ivEdit);
            tvNameC = itemView.findViewById(R.id.tvName);
            tvPassC = itemView.findViewById(R.id.tvPassword);
        }
    }

    //Đùng để thực hiện thao tác e,d
    public interface UserCallback {
        void onItemDeleteClicked(User user, int position);

        void onItemEditClicked(User user, int position);
    }


}

