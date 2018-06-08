package localnews.spire.com.localnews;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easywaylocation.EasyWayLocation;
import com.example.easywaylocation.Listener;

import java.util.Random;


public class SplashScreen extends Activity implements Listener {

    EasyWayLocation easyWayLocation;
    private Double lati, longi;
    private final int LOCATION_SETTING_REQUEST_CODE = 10;
    private String stadt;

    private int waitingtime = 3000;

    private int LOCATION_PERMISSION_CODE = 1;

    private TextView appname,appname2;

    Typeface bebasbold, bebasthin;

    ImageView background,background_gradient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        easyWayLocation = new EasyWayLocation(this);
        easyWayLocation.setListener(this);

        background = (ImageView)findViewById(R.id.background);
        background_gradient = (ImageView)findViewById(R.id.background_gradient);
        appname = (TextView)findViewById(R.id.appname);
        appname2 = (TextView)findViewById(R.id.appname2);

        bebasbold = Typeface.createFromAsset(getAssets(), "fonts/BebasNeueBold.otf");
        bebasthin = Typeface.createFromAsset(getAssets(), "fonts/BebasNeueLight.otf");

        appname.setText("PAPER");
        appname2.setText("BOY");
        appname.setTypeface(bebasbold);
        appname2.setTypeface(bebasthin);

        randomBackground();

        if(stadt != null){

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                    onPermissionGrantedi();
                }
            }, waitingtime);
        }

        if (ContextCompat.checkSelfPermission(SplashScreen.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission granted:","You have already granted this permisssion");


        } else {
            requestStoragePermission();
        }
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(SplashScreen.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void locationOn() {
        Toast.makeText(this, "Location ON", Toast.LENGTH_SHORT).show();
        easyWayLocation.beginUpdates();
        lati = easyWayLocation.getLatitude();
        longi = easyWayLocation.getLongitude();

        System.out.println("Lat: " + lati + " / Long:" +longi);
        System.out.println("Adresse: " + EasyWayLocation.getAddress(this, lati, longi, false, false) );
        stadt = EasyWayLocation.getAddress(this, lati, longi, false, false);

        if(stadt != null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                    onPermissionGrantedi();
                }
            }, waitingtime);
        }
    }

    @Override
    public void onPositionChanged() {
        Toast.makeText(this, String.valueOf(easyWayLocation.getLongitude()) + "," + String.valueOf(easyWayLocation.getLatitude()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void locationCancelled() {
        easyWayLocation.showAlertDialog("Location cancelled", "canceled this", null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCATION_SETTING_REQUEST_CODE:
                easyWayLocation.onActivityResult(resultCode);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // make the device update its location
        easyWayLocation.beginUpdates();


    }

    @Override
    protected void onPause() {
        // stop location updates (saves battery)
        easyWayLocation.endUpdates();


        super.onPause();
    }



    public void onPermissionGrantedi(){

        //Hier kommt der Serverrequest mit der Location und return welcher RSS Feed ausgewählt wird
        //wird mit der Aktivity rübergeschickt
        //wenn fail = fallback dann trotzdem weiterschicken zur manuellen eingabe
        //Intent i = new Intent(this, MainActivityPager.class);
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }


    public void randomBackground(){

        Random r = new Random();
        int back1 = r.nextInt(9)+1;

        switch (back1) {
            case 1:
                background.setImageResource(R.drawable.woman);
                break;
            case 2:
                background.setImageResource(R.drawable.local);
                break;
            case 3:
                background.setImageResource(R.drawable.background);
                break;
            case 4:
                background.setImageResource(R.drawable.grandpa);
                break;
            case 5:
                background.setImageResource(R.drawable.portrait);
                break;
            case 6:
                background.setImageResource(R.drawable.reading);
                break;
            case 7:
                background.setImageResource(R.drawable.readingfromback);
                break;
            case 8:
                background.setImageResource(R.drawable.paper);
                break;
            case 9:
                background.setImageResource(R.drawable.girl);
                break;
            default: background.setImageResource(R.drawable.girl);
                break;
        }

        Random r2 = new Random();
        int forderground = r2.nextInt(5)+1;

        switch (forderground) {
            case 1:
                background_gradient.setImageResource(R.drawable.transition_one);
                break;
            case 2:
                background_gradient.setImageResource(R.drawable.gradient_one);
                break;
            case 3:
                background_gradient.setImageResource(R.drawable.gradient_two);
                break;
            case 4:
                background_gradient.setImageResource(R.drawable.gradient_three);
                break;
            case 5:
                background_gradient.setImageResource(R.drawable.gradient_four);
                break;
            default: background.setImageResource(R.drawable.gradient_one);
                break;
        }
    }



}
