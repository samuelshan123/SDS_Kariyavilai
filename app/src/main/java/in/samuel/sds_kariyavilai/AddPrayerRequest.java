package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddPrayerRequest extends AppCompatActivity {
EditText etyname,etyprayer,etydate;
    private RadioGroup radioGroup;
    private RadioButton postpublic,postprivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prayer_request);

        etyname = (EditText) findViewById(R.id.yourname);
        etyprayer = (EditText) findViewById(R.id.yourrequest);
        etydate = (EditText) findViewById(R.id.yourdate);
        SimpleDateFormat f = new SimpleDateFormat("MMM");
        SimpleDateFormat f1 = new SimpleDateFormat("dd");
        SimpleDateFormat f2 = new SimpleDateFormat("a");
        int h,m;


        Calendar.getInstance().get(Calendar.HOUR);
        Calendar.getInstance().get(Calendar.MINUTE);

        h = Calendar.getInstance().get(Calendar.HOUR);
        m=Calendar.getInstance().get(Calendar.MINUTE);
        String filename=""
                +f1.format(new Date())
                +f.format(new Date())+
                "/"
                +h+"."+m+f2.format(new Date());
        etydate.setText(filename);


        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        postpublic=(RadioButton)findViewById(R.id.postpub);
        postprivate=(RadioButton)findViewById(R.id.postpriv);


        // Uncheck or reset the radio buttons initially
        radioGroup.clearCheck();

        // Add the Listener to the RadioGroup
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {

                        // Get the selected Radio Button
                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                    }
                });

    }

    public void addprayer(View view) {
        // Ge the Radio Button which is set
        // If no Radio Button is set, -1 will be returned
        int selectedId = radioGroup.getCheckedRadioButtonId();

         if(postpublic.isChecked()){


            // Now display the value of selected item
            // by the Toast message
            Toast.makeText(AddPrayerRequest.this,
                    "Posting Public...",
                    Toast.LENGTH_SHORT)
                    .show();
            addPrayerreq();

        }
         else if(postprivate.isChecked()){

            // Now display the value of selected item
            // by the Toast message
            Toast.makeText(AddPrayerRequest.this,
                    "Posting Privately..",
                    Toast.LENGTH_SHORT)
                    .show();
            addPrivatePrayerreq();        }
        else {
            Toast.makeText(AddPrayerRequest.this,
                    "Select any of the Post Method...",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }


    private void addPrayerreq() {
        final String yname = etyname.getText().toString().trim();
        final String yprayer = etyprayer.getText().toString().trim();
        final String ydate = etydate.getText().toString().trim();

        final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.seprog));
        progressBar.setVisibility(View.VISIBLE);
        //first we will do the validations

        if (TextUtils.isEmpty(yname)) {
            etyname.setError("Enter Your Name");
            etyname.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(yprayer)) {
            etyprayer.setError("Enter Prayer Request");
            etyprayer.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(ydate)) {
            etydate.setError("Enter Date and Time");
            etydate.requestFocus();
            return;
        }
        else{

            StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsaddPrayer.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if(response.equalsIgnoreCase("Prayer Added")){
                                Toast.makeText(AddPrayerRequest.this, "Prayer Request Posted successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                finish();
                                startActivity(new Intent(getApplicationContext(),MemberPrayerRequest.class));
                            }


                            else{
                                Toast.makeText(AddPrayerRequest.this, response, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddPrayerRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("yname",yname);
                    params.put("yprayer",yprayer);
                    params.put("ydate",ydate);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AddPrayerRequest.this);
            requestQueue.add(request);
        }

    }

    private void addPrivatePrayerreq() {
        final String yname = etyname.getText().toString().trim();
        final String yprayer = etyprayer.getText().toString().trim();
        final String ydate = etydate.getText().toString().trim();

        final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.seprog));
        progressBar.setVisibility(View.VISIBLE);


        //first we will do the validations

        if (TextUtils.isEmpty(yname)) {
            etyname.setError("Enter Your Name");
            etyname.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(yprayer)) {
            etyprayer.setError("Enter Prayer Request");
            etyprayer.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(ydate)) {
            etydate.setError("Enter Date and Time");
            etydate.requestFocus();
            return;
        }
        else{

            StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsAddPrivateprayer.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if(response.equalsIgnoreCase("Prayer Added")){
                                Toast.makeText(AddPrayerRequest.this, "Prayer Request Posted successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                finish();
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                            }


                            else{
                                Toast.makeText(AddPrayerRequest.this, response, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddPrayerRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("aname",yname);
                    params.put("aprayer",yprayer);
                    params.put("adate",ydate);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AddPrayerRequest.this);
            requestQueue.add(request);
        }

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}