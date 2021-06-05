package in.samuel.sds_kariyavilai.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

import in.samuel.sds_kariyavilai.ChurchMembers.User;
import in.samuel.sds_kariyavilai.R;
import in.samuel.sds_kariyavilai.SDS_SharedPreference.SharedPrefManager;
import in.samuel.sds_kariyavilai.SDS_SharedPreference.SharedPreference;
import in.samuel.sds_kariyavilai.WishMe;
import in.samuel.sds_kariyavilai.WishmeAdapter;
import in.samuel.sds_kariyavilai.WishmeData;

public class AdminWish extends AppCompatActivity {

    EditText etwmessage;
    String etwname,etwtime;
    String url = "https://unbruised-dive.000webhostapp.com/sdsWishmeRetrive.php";
    ListView wlistView;
    WishmeAdapter wishmeAdapter;
    public static ArrayList<WishmeData> wishmeDataArrayList = new ArrayList<>();
    WishmeData wishmeData;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_wish);
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


                                for(int i=0;i< jsonArray.length();i++){

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
                Toast.makeText(AdminWish.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void send(View view) {
        AddWish();
        etwmessage.setText("");
        closeKeyBoard();
    }

    private void closeKeyBoard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void AddWish() {
        final String wmessage = etwmessage.getText().toString().trim();
        String wnmae,wtime;

      AdminUser adminUser = SharedPreference.getInstance(this).getAdminUser();
      wnmae =adminUser.getAdminname();

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


        Toast.makeText(AdminWish.this, "posting....", Toast.LENGTH_SHORT).show();


        //first we will do the validations

        if (TextUtils.isEmpty(wmessage)) {
            etwmessage.setError("Message Cannot be Empty");
            etwmessage.requestFocus();
            return;
        }

        else{
            final ProgressBar progressBar = (ProgressBar)(findViewById(R.id.wprogress));
            progressBar.setVisibility(View.VISIBLE);
            StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsWishmesend.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressBar.setVisibility(View.GONE);
                            if(response.equalsIgnoreCase("wishes added")){
                                Toast.makeText(AdminWish.this, response, Toast.LENGTH_SHORT).show();
                                retriveWishme();

                            }


                            else{
                                Toast.makeText(AdminWish.this, response, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AdminWish.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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


            RequestQueue requestQueue = Volley.newRequestQueue(AdminWish.this);
            requestQueue.add(request);
        }

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}