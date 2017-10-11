package com.example.alunos.mapmarker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button butLocalizar;
    Button butMapa;
    TextView txtLatitude;
    TextView txtLongitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission .ACCESS_FINE_LOCATION;
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            if(ActivityCompat.checkSelfPermission(this,mPermission) != PackageManager .PERMISSION_GRANTED){
                ActivityCompat .requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        butLocalizar = (Button) findViewById(R.id.btnLocalizar);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);

        butLocalizar .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gps = new GPSTracker (MainActivity.this);

                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    txtLatitude .setText(String.valueOf(latitude));
                    txtLongitude .setText(String.valueOf(longitude));
                }else{
                    txtLatitude . setText("Não disponivel...");
                    txtLongitude .setText("Nao disponivel...");
                    gps.showSettingsAlert();
                }


            }

        });

        butMapa = (Button) findViewById(R.id.btnMapa);
        butMapa .setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick (View view){
                Intent it = new Intent (MainActivity.this, MapsActivity.class);
                startActivity(it);
            }
        });


    }

}
