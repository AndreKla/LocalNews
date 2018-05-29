package localnews.spire.com.localnews;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;

import classes.CustomAdapter;
import classes.DataModel;
import classes.ItemObjects;
import classes.RecycleViewAdapter;


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

        dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3320188-jahnplatz-umbauarbeiten-ab-mitte-juni-fuer-verkehrstest-versuchsweise-ab-august/94520090-1-ger-DE/Jahnplatz-Umbauarbeiten-ab-Mitte-Juni-fuer-Verkehrstest-Versuchsweise-ab-August_image_300_250.jpg"));
        dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Donut","Android 1.6","4","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Eclair", "Android 2.0", "5","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Froyo", "Android 2.2", "8","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Honeycomb","Android 3.0","11","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Lollipop","Android 5.0","21","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));
        dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","http://www.westfalen-blatt.de/var/storage/images/wb/startseite/owl/bielefeld/bielefeld/3318617-akten-werden-an-staatsanwaltschaft-uebergeben-keine-neuen-erkenntnisse-vergewaltigungs-vorwurf-polizei-schliesst-ermittlungen-ab/94493224-1-ger-DE/Akten-werden-an-Staatsanwaltschaft-uebergeben-keine-neuen-Erkenntnisse-Vergewaltigungs-Vorwurf-Polizei-schliesst-Ermittlungen-ab_image_300_250.jpg"));

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);

            }
        });



        String urlString = "https://www.bild.de/rssfeeds/vw-lifestyle/vw-lifestyle-16728898,dzbildplus=true,short=1,sort=1,teaserbildmobil=false,view=rss2.bild.xml";
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                //what to do when the parsing is done
                //the Array List contains all article's data. For example you can use it for your adapter.
                for(int i = 0; i < list.size(); i++){
                    System.out.println(list.get(i).getTitle() + " + " + list.get(i).getImage());

                    /*
                    listViewItems.add(new ItemObjects(list.get(i).getTitle()
                            ,list.get(i).getImage()
                            ,list.get(i).getLink()));*/
                }

                /*
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);

                gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                recyclerView.setLayoutManager(gaggeredGridLayoutManager);

                List<ItemObjects> gaggeredList = listViewItems;

                RecycleViewAdapter rcAdapter = new RecycleViewAdapter(MainActivity.this, gaggeredList);
                recyclerView.setAdapter(rcAdapter);*/
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

