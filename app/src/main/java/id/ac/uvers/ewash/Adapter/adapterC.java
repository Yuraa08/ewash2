package id.ac.uvers.ewash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.uvers.ewash.R;
import id.ac.uvers.ewash.model.laundrylist.laundrylist;

public class adapterC extends RecyclerView.Adapter<adapterC.HolderData> {

    private Context ctx;
    private List<laundrylist> laundrylists;
    private int id_laundry, id_lempar;
    private String namalaundry, jambuka, jamtutup, rating;

    public adapterC(Context ctx, List<laundrylist> laundrylists)
    {
        this.ctx = ctx;
        this.laundrylists = laundrylists;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laundrylay,parent,false);
        HolderData holder = new HolderData(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        laundrylist lnl = laundrylists.get(position);

        holder.id_laundry.setText(lnl.getId_laundry());
        holder.namalaundry.setText(String.valueOf(lnl.getNamalaundry()));
        holder.jambuka.setText(lnl.getJambuka());
        holder.jamtutup.setText(lnl.getJamtutup());
        holder.rating.setText(lnl.getRating());
    }

    @Override
    public int getItemCount() {
        return laundrylists.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView id_laundry, namalaundry, jambuka, jamtutup, rating;

        public HolderData(View itemView){
            super(itemView);
            id_laundry = itemView.findViewById(R.id.id_ln);
            namalaundry = itemView.findViewById(R.id.laundryName);
            jambuka = itemView.findViewById(R.id.jamBuka);
            jamtutup = itemView.findViewById(R.id.jamTutup);
            rating = itemView.findViewById(R.id.txtrating);


        }
    }

}
