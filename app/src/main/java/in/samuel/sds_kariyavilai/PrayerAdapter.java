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

public class PrayerAdapter  extends ArrayAdapter<PrayerData> {
    Context context;
    List<PrayerData> arrayListPrayer;


    public PrayerAdapter(@NonNull Context context, List<PrayerData> arrayListPrayer) {
        super(context, R.layout.prayerrequest_list,arrayListPrayer);

        this.context = context;
        this.arrayListPrayer = arrayListPrayer;

    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prayerrequest_list,null,true);

        TextView tvTitle = view.findViewById(R.id.memName);
        TextView tvDescription = view.findViewById(R.id.prayer);
        TextView tvDate = view.findViewById(R.id.memdate);

        tvDate.setText(arrayListPrayer.get(pos).getMemDate());
        tvTitle.setText(arrayListPrayer.get(pos).getMemName());

        tvDescription.setText(arrayListPrayer.get(pos).getMemPrayer());

        return view;
    }
}
