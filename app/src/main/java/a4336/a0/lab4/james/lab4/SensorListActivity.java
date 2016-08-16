package a4336.a0.lab4.james.lab4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SensorListActivity extends AppCompatActivity{


    private SensorManager sensorManager;
    private List<Sensor> sensorList;


    //disregard this code
    /*public SensorListActivity() {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    } */                              // NOT Context.getSystemService

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        //SensorManager returns list of sensors on device, calls displaySensors();
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        System.out.println("about to display");
        displaySensors();



    }

    public void displaySensors(){

        //first, converts data into single string for display in listView.
        String[] sensorDetails = new String[sensorList.size()];
        int i = 0;
        for(Sensor temp : sensorList){

            String t1 = temp.getName() + "\n";
            String t2 = temp.getVendor() + "\n";
            String t3 = "version: " + String.valueOf(temp.getVersion()) + "\n";
            String t4 = "maximum range: " + String.valueOf(temp.getMaximumRange()) + "\n";
            String t5 = "min delay: " + String.valueOf(temp.getMinDelay()) + "\n";

            sensorDetails[i] = t1 + t2 + t3 + t4 + t5;
            i ++;
        }

        //initialises listView and displays Sensor Details
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorDetails);
        ListView listView = (ListView) findViewById(R.id.sensorList);
        listView.setAdapter(adapter);

    }



}
