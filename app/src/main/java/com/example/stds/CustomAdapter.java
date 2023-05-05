package com.example.stds;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<String> To_Do;

    public CustomAdapter(Context context, ArrayList<String> to_do) {
        this.context = context;
        To_Do = to_do;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        DBhelper db  = new DBhelper(context);
        holder.rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                db.updatestate(To_Do.get(position));
            }
        });
        holder.rb.setText(String.valueOf(To_Do.get(position)));
        int state = 0 ;
        state=db.getstate(To_Do.get(position));
       if (state==0)
       {
           holder.rb.setChecked(false);
       }
       else{
           holder.rb.setChecked(true);
       }
      holder.delete_todo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              db.deleteTodo(To_Do.get(position));
              Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
              To_Do.remove(position);
              notifyItemChanged(position);;
              notifyItemRemoved(position);;

          }
      });

    }

    @Override
    public int getItemCount() {
        return To_Do.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton rb;
        ImageView delete_todo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rb=itemView.findViewById(R.id.rb);
            delete_todo = itemView.findViewById(R.id.delete_todo);
        }
    }

}
