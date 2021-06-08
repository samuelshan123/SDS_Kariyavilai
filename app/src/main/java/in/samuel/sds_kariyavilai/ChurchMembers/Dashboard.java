package in.samuel.sds_kariyavilai.ChurchMembers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import in.samuel.sds_kariyavilai.AboutSDS;
import in.samuel.sds_kariyavilai.Admin.AdminDashBoard;
import in.samuel.sds_kariyavilai.Contacts;
import in.samuel.sds_kariyavilai.EventGallery.Event_Gallery;
import in.samuel.sds_kariyavilai.MainActivity;
import in.samuel.sds_kariyavilai.Offerings;
import in.samuel.sds_kariyavilai.R;
import in.samuel.sds_kariyavilai.SDS_SharedPreference.SharedPrefManager;
import in.samuel.sds_kariyavilai.WishMe;
import in.samuel.sds_kariyavilai.Worshiptime;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
TextView textViewUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textViewUsername);
        navUsername.setText(user.getname());
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);



    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Contacts:
                Toast.makeText(this, "Contacts", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9150944537"));
                startActivity(intent);
                break;


            case R.id.aboutus:

                break;
            case R.id.visit:
                String link = "https://goo.gl/maps/QeD1TJqTG3bH2unt7";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void buttonLogout(MenuItem item) {
        finish();
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }

    public void contact(MenuItem item) {
        Intent intent = new Intent(Dashboard.this, Contacts.class);
        startActivity(intent);
    }

    public void wortime(View view) {
        Intent intent = new Intent(Dashboard.this, Worshiptime.class);
        startActivity(intent);
    }


    public void eventgallery(View view) {
        Intent intent = new Intent(Dashboard.this, Event_Gallery.class);
        startActivity(intent);
    }

    public void userevent(View view) {
        Intent intent = new Intent(Dashboard.this, UserEvents.class);
        startActivity(intent);

    }

    public void prayreq(View view) {
        Intent intent = new Intent(Dashboard.this,MemberPrayerRequest.class);
        startActivity(intent);
    }

    public void wish(View view) {
        Intent intent = new Intent(Dashboard.this, WishMe.class);
        startActivity(intent);
    }

    public void offer(View view) {
        Intent intent = new Intent(Dashboard.this, Offerings.class);
        startActivity(intent);
    }

    public void aboutus(MenuItem item) {
        Intent intent = new Intent(Dashboard.this, AboutSDS.class);
        startActivity(intent);
    }
}