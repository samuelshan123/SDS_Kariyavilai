package in.samuel.sds_kariyavilai.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import in.samuel.sds_kariyavilai.R;

public class AdminAdapter extends ArrayAdapter<AdminUser> {


    Context context;
    List<AdminUser> arrayListAdmin;


    public AdminAdapter(@NonNull Context context, List<AdminUser> arrayListAdmin) {
        super(context, R.layout.admin_list, arrayListAdmin);

        this.context = context;
        this.arrayListAdmin = arrayListAdmin;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list, null, true);

     //   TextView tvID = view.findViewById(R.id.txt_adminid);
        TextView tvName = view.findViewById(R.id.txt_adminname);

        //tvID.setText(arrayListAdmin.get(position).getAdminid());

        tvName.setText(arrayListAdmin.get(position).getAdminname());

        return view;
    }


}



