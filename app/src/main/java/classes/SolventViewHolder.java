package classes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import localnews.spire.com.localnews.R;

public class SolventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView article_headline;
    public PEWImageView article_shot;
    private WebView articleView;

    public SolventViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        article_headline = (TextView) itemView.findViewById(R.id.article_headline);
        article_shot = (PEWImageView) itemView.findViewById(R.id.article_shot);

    }

    @Override
    public void onClick(View view) {

       //Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}