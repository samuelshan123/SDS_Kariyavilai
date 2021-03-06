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

public class MemberPrayerRequest extends AppCompatActivity {

    String url = "https://unbruised-dive.000webhostapp.com/sdsRetrivePrayer.php";
    ListView mplistView;
    MemberPrayerAdapter memberPrayerAdapter;
    public static ArrayList<MemberPrayerdata> memberPrayerdataArrayList = new ArrayList<>();
    MemberPrayerdata memberPrayerdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_prayer_request);
        retrievePrayerData();

        mplistView = findViewById(R.id.mpListView);
        memberPrayerAdapter = new MemberPrayerAdapter(this, memberPrayerdataArrayList);
        mplistView.setAdapter(memberPrayerAdapter);

        mplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MemberPrayerRequest.this,MemberPrayerDetails.class)
                        .putExtra("position",position));

            }
        });
    }

    public void retrievePrayerData() {
        final ProgressBar progress = findViewById(R.id.mpprogress);
        progress.setVisibility(View.VISIBLE);
        Toast.makeText(MemberPrayerRequest.this,"Loading Prayer Requests", Toast.LENGTH_SHORT).show();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.setVisibility(View.GONE);
                        memberPrayerdataArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("prayerdata");

                            if (sucess.equals("1")) {


                                for(int i=jsonArray.length()-1;i>=0;i--){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String mname = object.getString("yname");
                                    String mprayer = object.getString("yprayer");
                                    String mdate = object.getString("ydate");

                                    memberPrayerdata = new MemberPrayerdata(mname, mprayer, mdate);
                                    memberPrayerdataArrayList.add(memberPrayerdata);
                                    memberPrayerAdapter.notifyDataSetChanged();

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MemberPrayerRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void addpr(View view) {
        Intent intent = new Intent(MemberPrayerRequest.this, AddPrayerRequest.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}