package localnews.spire.com.localnews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;

import classes.CustomAdapter;
import classes.DataModel;


public class MainActivity extends Activity {


    ArrayList<DataModel> dataModels;

    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_list);


        listView=(ListView)findViewById(R.id.list);

        dataModels= new ArrayList<>();

        String urlString = "https://www.bild.de/rssfeeds/vw-lifestyle/vw-lifestyle-16728898,dzbildplus=true,short=1,sort=1,teaserbildmobil=false,view=rss2.bild.xml";
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                //what to do when the parsing is done
                //the Array List contains all article's data. For example you can use it for your adapter.
                for(int i = 0; i < list.size(); i++){
                    System.out.println("CATEGORIE: " + list.get(i).getCategories());

                    dataModels.add(new DataModel(list.get(i).getTitle(),
                                                list.get(i).getAuthor(),
                                                list.get(i).getDescription(),
                                                list.get(i).getContent(),
                                                list.get(i).getImage(),
                                                list.get(i).getPubDate().toString(),
                                                list.get(i).getLink()));


                }

                adapter= new CustomAdapter(dataModels,getApplicationContext());

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        DataModel dataModel= dataModels.get(position);

                    }
                });

            }


            @Override
            public void onError() {
                //what to do in case of error
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

