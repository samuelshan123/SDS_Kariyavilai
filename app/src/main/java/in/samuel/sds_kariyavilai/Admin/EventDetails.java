package in.samuel.sds_kariyavilai.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import in.samuel.sds_kariyavilai.ChurchMembers.UserEvents;
import in.samuel.sds_kariyavilai.R;

public class EventDetails extends AppCompatActivity {

    TextView tvtitle,tvdesc;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);


        //Initializing Views
        tvdesc = findViewById(R.id.eventdesc);
        tvtitle = findViewById(R.id.eventtit);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvtitle.setText(UserEvents.eventDatasArrayList.get(position).getEventTitle());
        tvdesc.setText(UserEvents.eventDatasArrayList.get(position).getEventDescription());


    }
}
