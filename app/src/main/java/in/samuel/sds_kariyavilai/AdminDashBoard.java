package in.samuel.sds_kariyavilai;

import android.annotation.SuppressLint;
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

public class AdminDashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout admindrawer;
    TextView textViewadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        if (!SharedPreference.getInstance(this).isLogIn()) {
            finish();
            startActivity(new Intent(AdminDashBoard.this, Login.class));
        }
        Toolbar adtoolbar = findViewById(R.id.admintoolbar);
        setSupportActionBar(adtoolbar);

        admindrawer = findViewById(R.id.admindrawer_layout);
        NavigationView navigationView = findViewById(R.id.adminnav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, admindrawer, adtoolbar,
                R.string.adminnav_open, R.string.adminnav_close);
        admindrawer.addDrawerListener(toggle);
        toggle.syncState();



        //getting the current user
        AdminUser adminUser = SharedPreference.getInstance(AdminDashBoard.this).getAdminUser();

        View header = navigationView.getHeaderView(0);
        textViewadmin = (TextView) findViewById(R.id.textViewAdmin);
        TextView navAdminname = (TextView) header.findViewById(R.id.textViewAdmin);
        navAdminname.setText(adminUser.getAdminname());






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

        admindrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (admindrawer.isDrawerOpen(GravityCompat.START)) {
            admindrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void buttonLogout(MenuItem item) {
        finish();
        SharedPreference.getInstance(getApplicationContext()).logout();
    }

    public void contact(MenuItem item) {
        Intent intent = new Intent(AdminDashBoard.this,Contacts.class);
        startActivity(intent);
    }

    public void wortime(View view) {
        Intent intent = new Intent(AdminDashBoard.this,Worshiptime.class);
        startActivity(intent);
    }


    public void eventgallery(View view) {
        Intent intent = new Intent(AdminDashBoard.this,Event_Gallery.class);
        startActivity(intent);
    }

    public void adminst(MenuItem item) {
        Intent intent = new Intent(AdminDashBoard.this,AdminSettings.class);
        startActivity(intent);
    }

    public void event(View view) {
        Intent intent = new Intent(AdminDashBoard.this,UserEvents.class);
        startActivity(intent);

    }
}