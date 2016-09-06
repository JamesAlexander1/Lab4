package a4336.a0.lab4.james.lab4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrientActivity extends AppCompatActivity implements SensorEventListener  {

    private Sensor accelSensor;
    private double ax,ay,az;
    OrientationEventListener myOrientationEventListener;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orient);


        /*****************************************************/



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);



        //Code for back button to MainActivity
        final Button btn4 = (Button) findViewById(R.id.exitButton3);
        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

               // return to MainActivity
                unRegister();
                finish();

            }
        });



       /*  ******** disregard below code ************


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        ax = sensorEvent.values[0];
        ay = sensorEvent.values[1];
        az = sensorEvent.values[2];
        TextView orientText = (TextView) findViewById(R.id.orientStatusText);
        double check = 8;
        double negCheck = -8;
        if(az > check){
            orientText.setText("ON THE TABLE");
        }else if(ay > check){
            orientText.setText("DEFAULT");
        }else if(ay < negCheck){
            orientText.setText("UPSIDE DOWN");
        }else if(ax > check){
            orientText.setText("LEFT");
        }else if(ax < negCheck){
            orientText.setText("RIGHT");
        }else{
            orientText.setText("Determining orientation...");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void unRegister(){
        sensorManager.unregisterListener(this);
    }
}
