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

public class AddEvent extends AppCompatActivity {
EditText title,description,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        title = (EditText) findViewById(R.id.etTitle);
        description = (EditText) findViewById(R.id.etDescription);
        date = (EditText) findViewById(R.id.etdate);


    }

    public void addevent(View view) {
        addEvent();

    }
    private void addEvent() {
        final String etitle = title.getText().toString().trim();
        final String edesc = description.getText().toString().trim();
        final String edate = date.getText().toString().trim();

        final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.prog));
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(AddEvent.this, "Adding Event.....", Toast.LENGTH_LONG).show();

        //first we will do the validations

        if (TextUtils.isEmpty(etitle)) {
            title.setError("Enter Title");
            title.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(edesc)) {
            description.setError("Enter Description");
            description.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(edate)) {
            date.setError("Enter Date and Time");
            date.requestFocus();
            return;
        }
        else{

            StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsAdduser.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if(response.equalsIgnoreCase("User Added Successfully")){
                                Toast.makeText(AddEvent.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                finish();
                                startActivity(new Intent(getApplicationContext(),Event.class));
                            }


                            else{
                                Toast.makeText(AddEvent.this, response, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddEvent.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("title",etitle);
                    params.put("description",edesc);
                    params.put("date",edate);




                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AddEvent.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}