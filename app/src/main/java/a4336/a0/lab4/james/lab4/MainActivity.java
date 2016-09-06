package a4336.a0.lab4.james.lab4;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button) findViewById(R.id.Sensebutton);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //code to start SensorListActivity (q1)
                Intent intent = new Intent(MainActivity.this, SensorListActivity.class);
                startActivity(intent);

                String temp = "Search again?";
                btn.setText(temp);
            }
        });


        final Button btn2 = (Button) findViewById(R.id.dataButton);
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //code to start DataActivity (q2)
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);

                String temp = "View Data Again?";
                btn2.setText(temp);
            }
        });


        final Button btn3 = (Button) findViewById(R.id.orientButton);
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //code to start OrientationActivity (q3)
                Intent intent = new Intent(MainActivity.this, OrientActivity.class);
                startActivity(intent);

                String temp = "Display Orientation Again ?";
                btn3.setText(temp);
            }
        });
    }
}
