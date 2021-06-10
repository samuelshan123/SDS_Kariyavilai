package in.samuel.sds_kariyavilai.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void addadmin(View view) {
        startActivity(new Intent(getApplicationContext(), Admins.class));

    }

    public void notify(View view) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdzshn3.onesignalapi");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {

            Toast.makeText(getApplicationContext(),"coudn't find notification package please install from playstore",Toast.LENGTH_LONG).show();
            Intent i2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.sdzshn3.onesignalapi&hl=en"));
            startActivity(i2);
        }

    }
}