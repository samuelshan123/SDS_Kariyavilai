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
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.samuel.sds_kariyavilai.Admin.AddEvent;
import in.samuel.sds_kariyavilai.Admin.EventAdapter;
import in.samuel.sds_kariyavilai.Admin.EventDatas;
import in.samuel.sds_kariyavilai.Admin.EventDetails;
import in.samuel.sds_kariyavilai.R;

public class UserEvents extends AppCompatActivity{
    String url = "https://unbruised-dive.000webhostapp.com/sdsEventRetrive.php";
    ListView mlistView;
    EventAdapter eventAdapter;
    public static ArrayList<EventDatas> eventDatasArrayList = new ArrayList<>();
    EventDatas eventDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        retrieveData();

        mlistView = findViewById(R.id.evListView);
        eventAdapter = new EventAdapter(this, eventDatasArrayList);
        mlistView.setAdapter(eventAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // TODO Auto-generated method stub
                startActivity(new Intent(UserEvents.this, EventDetails.class)
                        .putExtra("position",position));

            }
        });
    }

    public void retrieveData(){
        final ProgressBar progress = findViewById(R.id.evprogress);
        progress.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.setVisibility(View.GONE);
                        eventDatasArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("eventdata");

                            if(sucess.equals("1")){


                                for(int i=jsonArray.length()-1;i>=0;i--){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String EventTitle = object.getString("title");
                                    String EventDescription = object.getString("description");
                                    String EventDate = object.getString("date");


                                    eventDatas = new EventDatas(EventTitle,EventDescription,EventDate);
                                    eventDatasArrayList.add(eventDatas);
                                    eventAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserEvents.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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


    public void addev(View view) {
        startActivity(new Intent(getApplicationContext(), AddEvent.class));

    }


}