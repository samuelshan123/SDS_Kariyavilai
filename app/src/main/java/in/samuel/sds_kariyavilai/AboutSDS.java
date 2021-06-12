package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutSDS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sds);

        CircleImageView circleImageView = (CircleImageView)findViewById(R.id.pastor);
        ImageView fam =(ImageView)findViewById(R.id.pasfam);
        ImageView men =(ImageView)findViewById(R.id.mens);
        ImageView women =(ImageView)findViewById(R.id.women);
        circleImageView.setImageResource(R.drawable.pastor);
        fam.setImageResource(R.drawable.pasfam);
        men.setImageResource(R.drawable.mens);
        women.setImageResource(R.drawable.women);



    }
}