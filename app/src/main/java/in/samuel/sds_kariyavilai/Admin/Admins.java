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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.samuel.sds_kariyavilai.R;

public class Admins extends AppCompatActivity {

    ListView listView;
    AdminAdapter adminAdapter;
    public static ArrayList<AdminUser> adminArrayList = new ArrayList<>();
    String url = "https://unbruised-dive.000webhostapp.com/sdsAdminRetrive.php";
AdminUser adminUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins);
        retrieveAdData();

        listView = findViewById(R.id.adListView);
        adminAdapter = new AdminAdapter(this,adminArrayList);
        listView.setAdapter(adminAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


                CharSequence[] dialogItem = {"Delete Admin"};
                builder.setTitle(adminArrayList.get(position).getAdminname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (i == 0) {
                            deleteAdmin(adminArrayList.get(position).getAdminname());
                        }
                    }
                });


                builder.create().show();
                retrieveAdData();

            }


        });

    }

    private void deleteAdmin(final String title) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://unbruised-dive.000webhostapp.com/sdsDeleteAdmin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Admin Deleted")){

                            Toast.makeText(Admins.this, "Admin Deleted Successfully", Toast.LENGTH_SHORT).show();
                            retrieveAdData();
                        }

                        else{
                            Toast.makeText(Admins.this, "Admin not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Admins.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("name", title);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    public void retrieveAdData(){
        final ProgressBar progress = findViewById(R.id.adprogress);
        progress.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.setVisibility(View.GONE);
                        adminArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=jsonArray.length()-1;i>=0;i--){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                 //   String id = object.getString("id");
                                    String name = object.getString("name");


                                    adminUser = new AdminUser(name);
                                    adminArrayList.add(adminUser);
                                    adminAdapter.notifyDataSetChanged();
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
                Toast.makeText(Admins.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

    public void btn_add_admin(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Admin.class));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}