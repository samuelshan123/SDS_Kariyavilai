package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddPrayerRequest extends AppCompatActivity {
EditText etyname,etyprayer,etydate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prayer_request);

        etyname = (EditText) findViewById(R.id.yourname);
        etyprayer = (EditText) findViewById(R.id.yourrequest);
        etydate = (EditText) findViewById(R.id.yourdate);

    }

    public void addprayer(View view) {
addPrayerreq();
    }

    private void addPrayerreq() {
        final String yname = etyname.getText().toString().trim();
        final String yprayer = etyprayer.getText().toString().trim();
        final String ydate = etydate.getText().toString().trim();

        final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.seprog));
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(AddPrayerRequest.this, "please Wait.....", Toast.LENGTH_LONG).show();

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
                                startActivity(new Intent(getApplicationContext(),PrayerRequest.class));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}