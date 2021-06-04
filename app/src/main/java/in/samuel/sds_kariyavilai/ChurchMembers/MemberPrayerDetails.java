package in.samuel.sds_kariyavilai.ChurchMembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import in.samuel.sds_kariyavilai.R;

public class MemberPrayerDetails extends AppCompatActivity {

    TextView tvtitle,tvdesc;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_prayer_details);


        //Initializing Views
        tvdesc = findViewById(R.id.mprayer);
        tvtitle = findViewById(R.id.mname);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvtitle.setText(MemberPrayerRequest.memberPrayerdataArrayList.get(position).getMname());
        tvdesc.setText(MemberPrayerRequest.memberPrayerdataArrayList.get(position).getMdesc());


    }
}