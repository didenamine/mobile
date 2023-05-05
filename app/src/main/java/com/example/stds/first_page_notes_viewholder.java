package com.example.stds;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class first_page_notes_viewholder extends RecyclerView.ViewHolder {
    ImageView imageView ;
    TextView Note,Title;
    RelativeLayout card ;
    public static ImageView delete ;
    public first_page_notes_viewholder(@NonNull View itemView) {
        super(itemView);
        Title= itemView.findViewById(R.id.Title);
        Note= itemView.findViewById(R.id.Note);
        card = itemView.findViewById(R.id.card_firstpage);
        delete = itemView.findViewById(R.id.delete_note);

    }
}
