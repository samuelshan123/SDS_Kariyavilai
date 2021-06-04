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

public class EventAdapter extends ArrayAdapter<EventDatas> {
    Context context;
    List<EventDatas> arrayListEvent;


    public EventAdapter(@NonNull Context context, List<EventDatas> arrayListEvent) {
        super(context, R.layout.event_list,arrayListEvent);

        this.context = context;
        this.arrayListEvent = arrayListEvent;

    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list,null,true);

        TextView tvTitle = view.findViewById(R.id.evtitle);
        TextView tvDescription = view.findViewById(R.id.evdesc);
        TextView tvDate = view.findViewById(R.id.dati);

tvDate.setText(arrayListEvent.get(pos).getEventDate());
        tvTitle.setText(arrayListEvent.get(pos).getEventTitle());

        tvDescription.setText(arrayListEvent.get(pos).getEventDescription());

        return view;
    }
}


