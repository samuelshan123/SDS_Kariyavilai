package in.samuel.sds_kariyavilai.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import in.samuel.sds_kariyavilai.ChurchMembers.UserEvents;
import in.samuel.sds_kariyavilai.R;

public class EventDetails extends AppCompatActivity {
TextToSpeech t1;
    TextView tvtitle, tvdesc;
    int position;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);



        //Initializing Views
        tvdesc = findViewById(R.id.eventdesc);
        tvtitle = findViewById(R.id.eventtit);
        button =findViewById(R.id.speak);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvtitle.setText(UserEvents.eventDatasArrayList.get(position).getEventTitle());
        tvdesc.setText(UserEvents.eventDatasArrayList.get(position).getEventDescription());



        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = tvdesc.getText().toString();
                t1.speak(input, TextToSpeech.QUEUE_FLUSH, null);




                /* if(input.contains("hi")){
                    txttwo=(TextView) findViewById(R.id.bottxt);
                    out = "i'm fine.How can i help u";
                    txttwo.setText(out);
                    t1.speak(out, TextToSpeech.QUEUE_FLUSH, null);
                }
                else {
String err = "sorry i don't no";
                    txttwo.setText(err);
                    t1.speak(err, TextToSpeech.QUEUE_FLUSH, null);
                }*/

            }
        });


    }

}