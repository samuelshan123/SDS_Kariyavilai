package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import in.samuel.sds_kariyavilai.Admin.AdminDashBoard;
import in.samuel.sds_kariyavilai.Admin.AdminUser;
import in.samuel.sds_kariyavilai.ChurchMembers.AddPrayerRequest;
import in.samuel.sds_kariyavilai.ChurchMembers.Dashboard;
import in.samuel.sds_kariyavilai.ChurchMembers.MemberPrayerRequest;
import in.samuel.sds_kariyavilai.ChurchMembers.PrayerAdapter;
import in.samuel.sds_kariyavilai.ChurchMembers.PrayerData;
import in.samuel.sds_kariyavilai.ChurchMembers.PrayerDetails;
import in.samuel.sds_kariyavilai.ChurchMembers.PrayerRequest;
import in.samuel.sds_kariyavilai.ChurchMembers.User;
import in.samuel.sds_kariyavilai.ChurchMembers.UserEvents;
import in.samuel.sds_kariyavilai.SDS_SharedPreference.SharedPrefManager;
import in.samuel.sds_kariyavilai.SDS_SharedPreference.SharedPreference;

public class WishMe extends AppCompatActivity {
    EditText etwmessage;
    String etwname,etwtime;
    String url = "https://unbruised-dive.000webhostapp.com/sdsWishmeRetrive.php";
    ListView wlistView;
    WishmeAdapter wishmeAdapter;
    public static ArrayList<WishmeData> wishmeDataArrayList = new ArrayList<>();
WishmeData wishmeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_me);
retriveWishme();
        wlistView = findViewById(R.id.wishListView);
        wishmeAdapter = new WishmeAdapter(this, wishmeDataArrayList);
        wlistView.setAdapter(wishmeAdapter);

etwmessage =(EditText)findViewById(R.id.messageEditText);




    }

    public void retriveWishme() {
        final ProgressBar progress = findViewById(R.id.wprogress);
progress.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.setVisibility(View.GONE);
                        wishmeDataArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("wishdata");

                            if (sucess.equals("1")) {


                                for(int i=jsonArray.length()-1;i>=0;i--){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String wname = object.getString("wname");
                                    String wmessage = object.getString("wmessage");
                                    String wtime = object.getString("wtime");


                                    wishmeData = new WishmeData(wname, wmessage, wtime);
                                    wishmeDataArrayList.add(wishmeData);
                                    wishmeAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WishMe.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void send(View view) {
        AddWish();
    }
    private void AddWish() {
        final String wmessage = etwmessage.getText().toString().trim();
        String wnmae,wtime;

        User user = SharedPrefManager.getInstance(WishMe.this).getUser();
       wnmae = user.getname();

        SimpleDateFormat f = new SimpleDateFormat("MMM");
        SimpleDateFormat f1 = new SimpleDateFormat("dd");
        SimpleDateFormat f2 = new SimpleDateFormat("a");
        int h,m;


        Calendar.getInstance().get(Calendar.HOUR);
        Calendar.getInstance().get(Calendar.MINUTE);

        h = Calendar.getInstance().get(Calendar.HOUR);
        m=Calendar.getInstance().get(Calendar.MINUTE);
      String  time =
                f1.format(new Date())
                +f.format(new Date())+
                "/"
                +h+"."+m+f2.format(new Date());
           wtime = time;

        final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.wprogress));
        progressBar.setVisibility(View.VISIBLE);


        //first we will do the validations

        if (TextUtils.isEmpty(wmessage)) {
            etwmessage.setError("Message Cannot be Empty");
            etwmessage.requestFocus();
            return;
        }

        else{

            StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsWishmesend.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if(response.equalsIgnoreCase("wishes added")){
                                Toast.makeText(WishMe.this, response, Toast.LENGTH_SHORT).show();
                                retriveWishme();
                            }


                            else{
                                Toast.makeText(WishMe.this, response, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(WishMe.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);


                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();


                    params.put("wname",wnmae);
                    params.put("wmessage",wmessage);
                   params.put("wtime",wtime);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(WishMe.this);
            requestQueue.add(request);
        }

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}