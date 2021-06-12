package in.samuel.sds_kariyavilai.ChurchMembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import in.samuel.sds_kariyavilai.R;

public class PrayerRequest extends AppCompatActivity {
    String url = "https://unbruised-dive.000webhostapp.com/sdsRetrivePrivateprayer.php";
    ListView plistView;
    PrayerAdapter prayerAdapter;
    public static ArrayList<PrayerData> prayerDataArrayList = new ArrayList<>();
    PrayerData prayerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_request);
        retrievePrivatePrayerData();

        plistView = findViewById(R.id.pListView);
        prayerAdapter = new PrayerAdapter(this, prayerDataArrayList);
        plistView.setAdapter(prayerAdapter);

        plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // TODO Auto-generated method stub
                startActivity(new Intent(PrayerRequest.this,PrayerDetails.class)
                        .putExtra("position",position));

            }
        });
    }

    public void retrievePrivatePrayerData() {
        final ProgressBar progress = findViewById(R.id.pprogress);
        progress.setVisibility(View.VISIBLE);
        Toast.makeText(PrayerRequest.this,"Loading Prayer Requests", Toast.LENGTH_SHORT).show();

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


                                for(int i=jsonArray.length()-1;i>=0;i--){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String mname = object.getString("aname");
                                    String mprayer = object.getString("aprayer");
                                    String mdate = object.getString("adate");


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