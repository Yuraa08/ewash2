package id.ac.uvers.ewash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.uvers.ewash.R;
import id.ac.uvers.ewash.detailLaundry;
import id.ac.uvers.ewash.model.response.laundrylist.LaundrylistsItem;

public class adapterC extends RecyclerView.Adapter<adapterC.HolderData> {

    private Context ctx;
    private List<LaundrylistsItem> LaundrylistsItem;
    private int idlaundry;
    private String id_lempar, namaln, jambk, jamttp, rate, selfdeliv, kurirdeliv;

    public adapterC(Context ctx, List<LaundrylistsItem> LaundrylistsItem)
    {
        this.ctx = ctx;
        this.LaundrylistsItem = LaundrylistsItem;
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

        LaundrylistsItem lnl = LaundrylistsItem.get(position);

        String selfdel = lnl.getSelfdelivery();
        String kurirdel = lnl.getKurirdelivery();

        holder.id_laundry.setText(lnl.getIdLaundry());
        holder.namalaundry.setText(String.valueOf(lnl.getNamalaundry()));
        holder.jambuka.setText(lnl.getJambuka());
        holder.jamtutup.setText(lnl.getJamtutup());
        holder.rating.setText(lnl.getRating());
        holder.selfdelivery.setText(selfdel);
        holder.kurirdelivery.setText(kurirdel);
        if (selfdel.equals("1")) {
            holder.selfdelivery.setText("self delivery");
        }
        else  {
            holder.selfdelivery.setVisibility(View.GONE);
        }
        if (kurirdel.equals("1")){
            holder.kurirdelivery.setText("kurir delivery");
        }
        else {
            holder.kurirdelivery.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return LaundrylistsItem.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView id_laundry, namalaundry, jambuka, jamtutup, rating, selfdelivery, kurirdelivery;

        public HolderData(View itemView){
            super(itemView);
            id_laundry = itemView.findViewById(R.id.id_ln);
            namalaundry = itemView.findViewById(R.id.laundryName);
            jambuka = itemView.findViewById(R.id.jamBuka);
            jamtutup = itemView.findViewById(R.id.jamTutup);
            rating = itemView.findViewById(R.id.txtrating);
            selfdelivery = itemView.findViewById(R.id.selfdelivery);
            kurirdelivery = itemView.findViewById(R.id.kurirdelivery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idlaundry = Integer.valueOf(id_laundry.getText().toString());
                    id_lempar = String.valueOf(idlaundry);
                    namaln = namalaundry.getText().toString();
                    jambk = jambuka.getText().toString();
                    jamttp = jamtutup.getText().toString();
                    rate = rating.getText().toString();
                    selfdeliv = selfdelivery.getText().toString();
                    kurirdeliv = kurirdelivery.getText().toString();

                    Intent intent = new Intent(ctx, detailLaundry.class);
                    intent.putExtra("id_laundry",id_lempar);
                    intent.putExtra("nama",namaln);
                    intent.putExtra("jambuka",jambk);
                    intent.putExtra("jamtutup",jamttp);
                    intent.putExtra("rating",rate);
                    intent.putExtra("selfdelivery",selfdeliv);
                    intent.putExtra("kurirdeliver",kurirdeliv);
                    ctx.startActivities(new Intent[]{intent});
                }
            });

        }
    }

}
