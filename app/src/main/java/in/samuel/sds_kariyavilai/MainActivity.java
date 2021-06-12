package in.samuel.sds_kariyavilai;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public void onStop() {
        super.onStop();
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    Animation anim;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternetConenction();
        scheduleAlarm();

        CardView imageView =(CardView) findViewById(R.id.li) ;
        LinearLayout backgroundView =(LinearLayout)findViewById(R.id.back) ;
        TextView textView=(TextView)findViewById(R.id.ver) ;

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sise_slide); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                // HomeActivity.class is the activity to go after showing the splash screen.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(anim);
        backgroundView.startAnimation(anim);
        textView.startAnimation(anim);


       /* int SPLASH_TIME_OUT = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }
        }, SPLASH_TIME_OUT);*/
    }
    private void checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, "  Please connect to internet ", Toast.LENGTH_LONG).show();
        }
    }
    private void scheduleAlarm() {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("data", "New Notification Recived");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, R.string.default_notification_channel_id, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntent);


      /*long afterTwoMinutes = SystemClock.elapsedRealtime() + 10* 1000;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle
                    (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            afterTwoMinutes, pendingIntent);
        else
            alarmManager.setExact
                    (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            afterTwoMinutes, pendingIntent);
*/

        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        if (now.after(calendar)) {
            Log.d("Hey","Added a day");
            calendar.add(Calendar.DATE, 1);
        }


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

}

