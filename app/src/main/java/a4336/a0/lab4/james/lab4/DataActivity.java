package a4336.a0.lab4.james.lab4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelSensor;
    private double ax,ay,az;
    private double[] gravity = new double[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        //Initialisation code setting up sensors and managers.
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this,accelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        for(int i = 0; i < 3; i ++){
            gravity[i] = 0;
        }
        //Code for back button to MainActivity
        final Button btn4 = (Button) findViewById(R.id.retButton2);
        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //unregister all sensors and return to MainActivity
                unRegister();
                finish();

            }
        });
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

    //Reading data from Accelerometer
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //TYPE_LINEAR_ACCELERATION automatically adjusts for gravity.
        //Only supported on Android 2.3 and above.
        TextView titleDataS = (TextView) findViewById(R.id.textData1);
        TextView title2 = (TextView) findViewById(R.id.textView3);
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){


            //if phone does support TYPE_LINEAR_ACCELERATION
            titleDataS.setText("Acceleration Force excluding gravity:");
            ax = sensorEvent.values[0];
            ay = sensorEvent.values[1];
            az = sensorEvent.values[2];
        }else{


            //if phone does not support TYPE_LINEAR_ACCELERATION
            titleDataS.setText("Acceleration Force excluding gravity w/ low pass filter:");
            // alpha is calculated as t / (t + dT)
            // with t, the low-pass filter's time-constant
            // and dT, the event delivery rate
            /*Low-pass filter (large value for α, say 0.8 ) to extract gravity from mems reading*/



            final double alpha = 0.8;
            gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];
            ax = sensorEvent.values[0] - gravity[0];
            ay = sensorEvent.values[1] - gravity[1];
            az = sensorEvent.values[2] - gravity[2];

            title2.setText("acceleraton force with gravity");
            TextView data2 = (TextView) findViewById(R.id.textView4);
            data2.setText("X: " + String.valueOf(sensorEvent.values[0]) + "\n" + "Y: " + String.valueOf(sensorEvent.values[1]) + "\n" + "Z: " + String.valueOf(sensorEvent.values[2]) + "\n");
        }


        //display data.
        TextView dataS = (TextView) findViewById(R.id.textData2);
        dataS.setText("X: " + String.valueOf(ax) + "\n" + "Y: " + String.valueOf(ay) + "\n" + "Z: " + String.valueOf(az) + "\n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
