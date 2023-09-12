package com.shankha.newsnow;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView title_source;
    ImageView img_headline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.text_title);
        title_source=itemView.findViewById(R.id.text_souce);
        img_headline =itemView.findViewById(R.id.Img_headline);
        cardView=itemView.findViewById(R.id.card_main_container);
    }
}
