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

public class MemberPrayerAdapter extends ArrayAdapter<MemberPrayerdata> {
    Context context;
    List<MemberPrayerdata> arrayListmemberprayerdata;


    public MemberPrayerAdapter(@NonNull Context context, List<MemberPrayerdata> arrayListmemberprayerdata) {
        super(context, R.layout.prayerrequest_list,arrayListmemberprayerdata);

        this.context = context;
        this.arrayListmemberprayerdata = arrayListmemberprayerdata;

    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberprayerrequest_list,null,true);

        TextView tvTitle = view.findViewById(R.id.MemberName);
        TextView tvDescription = view.findViewById(R.id.Memberprayer);
        TextView tvDate = view.findViewById(R.id.MemberName);

        tvDate.setText(arrayListmemberprayerdata.get(pos).getMdate());
        tvTitle.setText(arrayListmemberprayerdata.get(pos).getMname());

        tvDescription.setText(arrayListmemberprayerdata.get(pos).getMdesc());

        return view;
    }
}
