package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);
    }

    public void addmem(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Member.class));
    }
}