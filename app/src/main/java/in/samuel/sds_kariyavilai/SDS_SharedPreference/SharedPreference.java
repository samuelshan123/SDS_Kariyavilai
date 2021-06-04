package in.samuel.sds_kariyavilai.SDS_SharedPreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import in.samuel.sds_kariyavilai.Admin.AdminUser;
import in.samuel.sds_kariyavilai.MainActivity;

/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPreference {

    //the constants
    private static final String ADMIN_SHARED_PREF_NAME = "sharedpref";
    private static final String KEY_ADMINNAME = "keyadmin";



    @SuppressLint("StaticFieldLeak")
    private static SharedPreference mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;

    private SharedPreference(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void useradLogin(AdminUser adminUser) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(ADMIN_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ADMINNAME, adminUser.getAdminname());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLogIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(ADMIN_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ADMINNAME, null) != null;

    }

    //this method will give the logged in user
    public AdminUser getAdminUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(ADMIN_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new AdminUser(

                sharedPreferences.getString(KEY_ADMINNAME, "null")

        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(ADMIN_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}