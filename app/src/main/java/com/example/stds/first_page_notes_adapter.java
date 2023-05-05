package com.example.stds;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class first_page_notes_adapter extends RecyclerView.Adapter<first_page_notes_viewholder> {

    Context context ;
    List<first_page_notes_items> items ;
    public void refreshData() {
        // Do the refresh operation here
        notifyDataSetChanged();
    }

    public first_page_notes_adapter(Context context, List<first_page_notes_items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public first_page_notes_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new first_page_notes_viewholder(LayoutInflater.from(context).inflate(R.layout.first_page_notes_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull first_page_notes_viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.Title.setText(items.get(position).getTitle());
        String note = items.get(position).getNote();

        holder.Note.setText(note.substring(0,note.length()/5)+".....");
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),inside_note.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                DBhelper DB = new DBhelper(context);
                DB.insert_read_note(items.get(position).getTitle(),items.get(position).getNote());
                context.startActivity(intent);

                DB.close();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper DB = new DBhelper(context);
                DB.deleteNote(items.get(position).getNote());
                Toast.makeText(context, "Note deleted!", Toast.LENGTH_SHORT).show();
                DB.close();
                items.remove(position);
                notifyItemChanged(position);;
                notifyItemRemoved(position);;


            }


        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
