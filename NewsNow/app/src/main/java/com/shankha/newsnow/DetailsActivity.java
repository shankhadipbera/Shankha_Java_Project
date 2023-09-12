package com.shankha.newsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
        NewsHeadlines headlines;
        TextView txt_title,txt_author,txt_Time,txt_details,txt_content,txt_link;
        ImageView head_img;
        CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title=findViewById(R.id.txtv_title);
        txt_author=findViewById(R.id.txt_author);
        txt_Time=findViewById(R.id.txt_time_);
        txt_details=findViewById(R.id.text_details_details);
        txt_content=findViewById(R.id.text_details_content);
        txt_link=findViewById(R.id.text_details_content_link);
        head_img=findViewById(R.id.img_bac_img);
        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_Time.setText(headlines.getpublishedAt());
        txt_details.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        txt_link.setText(headlines.getUrl());
        Picasso.get().load(headlines.getUrlToImage()).into(head_img);



    }
}