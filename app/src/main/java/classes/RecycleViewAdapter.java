package classes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import localnews.spire.com.localnews.R;

public class RecycleViewAdapter  extends RecyclerView.Adapter<SolventViewHolder> {

    private List<ItemObjects> itemList;
    private Context context;

    public RecycleViewAdapter(Context context, List<ItemObjects> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public SolventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.solvent_list, null);
        SolventViewHolder rcv = new SolventViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(SolventViewHolder holder, final int position) {
        holder.article_headline.setText(itemList.get(position).getTitle());

        RequestOptions options = new RequestOptions();
        options.override(300,300).fitCenter();

        Glide.with(context).load(itemList.get(position).getPhoto())
                .apply(options)
                .into(holder.article_shot);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(itemList.get(position).getLink()));
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}