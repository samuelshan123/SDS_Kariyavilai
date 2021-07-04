package in.samuel.sds_kariyavilai.Admin;

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
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.WanderingCubes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.samuel.sds_kariyavilai.ChurchMembers.UserEvents;
import in.samuel.sds_kariyavilai.R;

public class Event extends AppCompatActivity {

    String url = "https://unbruised-dive.000webhostapp.com/sdsEventRetrive.php";
    ListView mlistView;
    EventAdapter eventAdapter;
    public static ArrayList<EventDatas> eventDatasArrayList = new ArrayList<>();
    EventDatas eventDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        retrieveData();

        mlistView = findViewById(R.id.evListView);
        eventAdapter = new EventAdapter(this,eventDatasArrayList);
        mlistView.setAdapter(eventAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


                CharSequence[] dialogItem = {"Delete Event"};
                builder.setTitle(eventDatasArrayList.get(position).getEventTitle());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (i == 0) {
                            deleteEvent(eventDatasArrayList.get(position).getEventTitle());
                        }
                    }
                });


                builder.create().show();
                retrieveData();
            }



        });


    }

    private void deleteEvent(final String title) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsDeleteEvent.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Event Deleted")){

                            Toast.makeText(Event.this, "Event Deleted Successfully", Toast.LENGTH_SHORT).show();
                            retrieveData();
                        }

                        else{
                            Toast.makeText(Event.this, "Event Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("title", title);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void retrieveData(){
        final ProgressBar progress = findViewById(R.id.evprogress);
        Sprite doubleBounce = new CubeGrid();
        progress.setIndeterminateDrawable(doubleBounce);
        progress.setVisibility(View.VISIBLE);
        Toast.makeText(Event.this,"Loading Events", Toast.LENGTH_SHORT).show();

        StringRequest request = new StringRequest(Request.Method.GET, url,
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
                Toast.makeText(Event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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