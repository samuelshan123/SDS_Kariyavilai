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



public class WishmeAdapter extends ArrayAdapter<WishmeData> {
    Context context;
    List<WishmeData> arrayListWishmeData;


    public WishmeAdapter(@NonNull Context context, List<WishmeData> arrayListWishmeData) {
        super(context, R.layout.wishme_list,arrayListWishmeData);

        this.context = context;
        this.arrayListWishmeData = arrayListWishmeData;

    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishme_list,null,true);

        TextView tvTitle = view.findViewById(R.id.Wname);
        TextView tvDescription = view.findViewById(R.id.Wmessage);
        TextView tvDate = view.findViewById(R.id.Wtime);

        tvDate.setText(arrayListWishmeData.get(pos).getWtime());
        tvTitle.setText(arrayListWishmeData.get(pos).getWname());

        tvDescription.setText(arrayListWishmeData.get(pos).getWmessage());

        return view;
    }
}


