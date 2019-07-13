package com.ttg.inventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ttg.inventory.ui.BodegaActivity;
import com.ttg.inventory.Model.User;
import com.ttg.inventory.R;
import com.ttg.inventory.ui.UserActivity;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public UserRecyclerViewAdapter(List<User> userList, Context context, FirebaseFirestore firestoreDB) {
        this.userList = userList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @Override
    public UserRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new UserRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final User user = userList.get(itemPosition);

        if(position % 2 == 1)
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));


        holder.edifullname.setText(user.getFullname());
        holder.editusername.setText(user.getUsername());
        holder.editpassword.setText(user.getPassword());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(user);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(user.getUid(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView edifullname;
        TextView editusername;
        TextView editpassword;
        ImageView edit;
        ImageView delete;



        ViewHolder(View view) {
            super(view);
            edifullname=view.findViewById(R.id.editfullname);
            editusername=view.findViewById(R.id.editUsername);
            editpassword=view.findViewById(R.id.editPassword);


            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateUser(User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("UpdateUserId", user.getUid());
        intent.putExtra("UpdateUserFullName", user.getFullname());
        intent.putExtra("UpdateUserUserName", user.getUsername());
        intent.putExtra("UpdateUserPassword", user.getPassword());


        context.startActivity(intent);
    }

    private void deleteUser(String id, final int position) {
        firestoreDB.collection("User")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        userList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, userList.size());
                        Toast.makeText(context, "Usuario eliminado!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

