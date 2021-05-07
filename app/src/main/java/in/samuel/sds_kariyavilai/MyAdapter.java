package in.samuel.sds_kariyavilai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<ChurchUsers> {

    Context context;
    List<ChurchUsers> arrayListMember;


    public MyAdapter(@NonNull Context context, List<ChurchUsers> arrayListMember) {
        super(context, R.layout.member_list,arrayListMember);

        this.context = context;
        this.arrayListMember = arrayListMember;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListMember.get(position).getId());

        tvName.setText(arrayListMember.get(position).getName());

        return view;
    }
}
