package in.samuel.sds_kariyavilai.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import in.samuel.sds_kariyavilai.R;

public class AdminSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);
    }

    public void addmem(View view) {
        startActivity(new Intent(getApplicationContext(), Members.class));
    }



    public void AddEvent(View view) {
        startActivity(new Intent(getApplicationContext(), Event.class));


    }
}