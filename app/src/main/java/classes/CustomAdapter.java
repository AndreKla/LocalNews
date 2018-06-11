package classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

import localnews.spire.com.localnews.MainActivityPager;
import localnews.spire.com.localnews.R;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView date;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
        ImageView seperator;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag(v.getId());

        System.out.println("given pos1111 :" + position);
        //Object object= getItem(position);
        //DataModel dataModel=(DataModel)object;

        Intent i = new Intent(getContext(), MainActivityPager.class);
        i.putExtra("position",position);
        mContext.startActivity(i);

        /*
        switch (v.getId())
        {
            case R.id.item_info: {

            }
            break;
        }*/
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            convertView.setOnClickListener(this);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);
            viewHolder.seperator = (ImageView) convertView.findViewById(R.id.viewsss);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        RequestOptions options = new RequestOptions();
        options.override(500,500);

        viewHolder.txtType.setText(dataModel.getTitle());

        viewHolder.date.setText(dataModel.getPubDate());

        Glide.with(getContext()).load(dataModel.getMainImage())
                .apply(options)
                .into(viewHolder.info);


        //viewHolder.info.setOnClickListener(this);
        viewHolder.seperator.setVisibility(View.VISIBLE);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen

        convertView.setTag(convertView.getId(),position);


        return convertView;
    }

}
