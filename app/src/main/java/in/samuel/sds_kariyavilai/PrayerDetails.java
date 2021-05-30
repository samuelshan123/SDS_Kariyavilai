package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PrayerDetails extends AppCompatActivity {

    TextView tvtitle,tvdesc;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_details);


        //Initializing Views
        tvdesc = findViewById(R.id.pprayer);
        tvtitle = findViewById(R.id.pname);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvtitle.setText(PrayerRequest.prayerDataArrayList.get(position).getMemName());
        tvdesc.setText(PrayerRequest.prayerDataArrayList.get(position).getMemPrayer());


    }
}