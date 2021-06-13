package in.samuel.sds_kariyavilai.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import in.samuel.sds_kariyavilai.ChurchMembers.MemberPrayerRequest;
import in.samuel.sds_kariyavilai.ChurchMembers.PrayerRequest;
import in.samuel.sds_kariyavilai.R;

public class Prayers_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers_category);
    }

    public void publicprayer(View view) {
        Intent intent = new Intent(Prayers_Category.this, MemberPrayerRequest.class);
        startActivity(intent);

    }

    public void privateprayer(View view) {
        Intent intent = new Intent(Prayers_Category.this, PrayerRequest.class);
        startActivity(intent);

    }
}