package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Helpsupport extends AppCompatActivity {

    EditText editTextSubject, editTextMessage;
    String recp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpsupport);


        editTextSubject = (EditText) findViewById(R.id.emailtitle);
        editTextMessage = (EditText) findViewById(R.id.emailmessage);

    }


    @SuppressLint("LongLogTag")
    public void sendemail(View view) {
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(subject)){
            editTextSubject.setError("subject can't be empty");
            editTextSubject.requestFocus();
            return;
        } else if (TextUtils.isEmpty(message)){
            editTextMessage.setError("message cant't be empty");
            editTextMessage.requestFocus();
            return;
        }
        else {

            recp = "samferoz123@gmail.com";

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{recp});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            //need this to prompts email client only
            email.setType("message/rfc822");
            try {
                email.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(email, "Send mail..."));
                finish();
                Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Helpsupport.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
            //startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }
    }
}