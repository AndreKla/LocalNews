package localnews.spire.com.localnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.an.customfontview.CustomTextView;
import com.bumptech.glide.Glide;

import com.github.ybq.parallaxviewpager.Mode;
import com.github.ybq.parallaxviewpager.ParallaxViewPager;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import org.w3c.dom.Text;

import java.util.ArrayList;

import classes.CustomAdapter;
import classes.DataModel;


public class MainActivityPager extends Activity {

    private ParallaxViewPager mParallaxViewPager;
    @SuppressWarnings("SpellCheckingInspection")

    ArrayList<DataModel> dataModels;

    int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_pager);
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.viewpager);

        dataModels= new ArrayList<>();

        Intent mIntent = getIntent();
        posi = mIntent.getIntExtra("position", 0);

        String urlString = "https://www.bild.de/rssfeeds/vw-lifestyle/vw-lifestyle-16728898,dzbildplus=true,short=1,sort=1,teaserbildmobil=false,view=rss2.bild.xml";
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

                            @Override
                            public void onTaskCompleted(ArrayList<Article> list) {
                                //what to do when the parsing is done
                                //the Array List contains all article's data. For example you can use it for your adapter.
                                for (int i = 0; i < list.size(); i++) {
                                    System.out.println(list.get(i).getTitle() + " + " + list.get(i).getContent());

                                    dataModels.add(new DataModel(list.get(i).getTitle(),
                                            list.get(i).getAuthor(),
                                            list.get(i).getDescription(),
                                            list.get(i).getContent(),
                                            list.get(i).getImage(),
                                            list.get(i).getPubDate().toString(),
                                            list.get(i).getLink()));


                                }

                                initViewPager(dataModels, posi);



                            }

                        @Override
                        public void onError() {

                        }
            });

    }

    private void initViewPager(final ArrayList<DataModel> dataModels, int pos) {


        PagerAdapter adapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(container.getContext(), R.layout.pager_item, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.item_img);

                CustomTextView date = (CustomTextView) view.findViewById(R.id.date);
                CustomTextView headline = (CustomTextView) view.findViewById(R.id.headline);
                CustomTextView description = (CustomTextView)view.findViewById(R.id.description);
                date.setText(dataModels.get(position).getPubDate());
                headline.setText(dataModels.get(position).getTitle());
                //description.setText(dataModels.get(position).getDescription());

                Glide.with(MainActivityPager.this).load(dataModels.get(position % dataModels.size()).getMainImage()).into(imageView);
                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                return view;
            }

            @Override
            public int getCount() {
                return dataModels.size();
            }
        };
        mParallaxViewPager.setAdapter(adapter);
        mParallaxViewPager.setCurrentItem(pos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.left_overlay:
                mParallaxViewPager.setMode(Mode.LEFT_OVERLAY);
                break;
            case R.id.right_overlay:
                mParallaxViewPager.setMode(Mode.RIGHT_OVERLAY);
                break;
            case R.id.none:
                mParallaxViewPager.setMode(Mode.NONE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}