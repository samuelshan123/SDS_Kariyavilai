package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrayerRequest extends AppCompatActivity {
    String url = "https://unbruised-dive.000webhostapp.com/sdsRetrivePrayer.php";
    ListView plistView;
    PrayerAdapter prayerAdapter;
    public static ArrayList<PrayerData> prayerDataArrayList = new ArrayList<>();
    PrayerData prayerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_request);
        retrievePrayerData();

        plistView = findViewById(R.id.pListView);
        prayerAdapter = new PrayerAdapter(this, prayerDataArrayList);
        plistView.setAdapter(prayerAdapter);


    }

    public void retrievePrayerData() {
        final ProgressBar progress = findViewById(R.id.pprogress);
        progress.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.setVisibility(View.GONE);
                        prayerDataArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("prayerdata");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String mname = object.getString("yname");
                                    String mprayer = object.getString("yprayer");
                                    String mdate = object.getString("ydate");


                                    prayerData = new PrayerData(mname, mprayer, mdate);
                                    prayerDataArrayList.add(prayerData);
                                    prayerAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PrayerRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}