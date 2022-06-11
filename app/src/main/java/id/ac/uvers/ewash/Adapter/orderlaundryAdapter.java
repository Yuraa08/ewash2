package id.ac.uvers.ewash.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import id.ac.uvers.ewash.R;
import id.ac.uvers.ewash.model.response.pricelist.PricelistItem;

public class orderlaundryAdapter extends BaseAdapter {

    Context ctx;
    Context mContext;
    List<PricelistItem> orderlaundry;
    ViewHolder viewHolder;
    //TextView hargabj, hargabk, hargabc, hargasl1, hargasl2, hargagr, hargastrk;
    String kategoris, serviss, namal;
    LinearLayout lyboneka, lybedcover, lyselimutb, lyselimutk, lygorder;
    EditText bjhqty, bkqty, bcqty, slqty, slqty2, grqty;
    ImageView bjhmin, bjhplus, bkmin, bkplus, bcmin, bcplus, slmin, slplus, slmin2, slplus2, grmin, grplus;
    int ibjhqty = 0, ibkqty = 0, ibcqty = 0, islqty = 0, islqty2 = 0, igrqty = 0;

    public orderlaundryAdapter(Context ctx, List<PricelistItem> orderlaundry) {
        super();
        this.ctx = ctx;
        this.orderlaundry = orderlaundry;
    }

    @Override
    public int getCount() {
        return orderlaundry.size();
    }

    @Override
    public Object getItem(int i) {
        return orderlaundry.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            LayoutInflater layoutInflater2 = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.productlist, null);
            view = layoutInflater2.inflate(R.layout.activity_orderlaundry,null);

            viewHolder = new ViewHolder();

            viewHolder.hargabjh = view.findViewById(R.id.hargabjhd);
            viewHolder.hargabk = view.findViewById(R.id.hargabkd);
            viewHolder.hargabc = view.findViewById(R.id.hargabcd);
            viewHolder.hargasl1 = view.findViewById(R.id.hargasld1);
            viewHolder.hargasl2 = view.findViewById(R.id.hargasld2);
            viewHolder.hargagr = view.findViewById(R.id.hargagrd);
//            viewHolder.kategori1 = view.findViewById(R.id.kategori1);
//            viewHolder.servis1 = view.findViewById(R.id.service1);
//            viewHolder.namalaundrytest = view.findViewById(R.id.namalaundrytest);
            lyboneka = view.findViewById(R.id.lyboneka);
            lybedcover = view.findViewById(R.id.lybedcover);
            lyselimutb = view.findViewById(R.id.lyselimutb);
            lyselimutk = view.findViewById(R.id.lyselimutk);
            lygorder = view.findViewById(R.id.lygorden);
            bjhmin = view.findViewById(R.id.bjhmin);
            bjhplus = view.findViewById(R.id.bjhplus);
            bjhqty = view.findViewById(R.id.bjhqty);
            bkmin = view.findViewById(R.id.bkmin);
            bkplus = view.findViewById(R.id.bkplus);
            bkqty = view.findViewById(R.id.bkqty);
            bcmin = view.findViewById(R.id.bcmin);
            bcplus = view.findViewById(R.id.bcplus);
            bcqty = view.findViewById(R.id.bcqty);
            slmin = view.findViewById(R.id.slmin1);
            slplus = view.findViewById(R.id.slplus1);
            slqty = view.findViewById(R.id.slqty1);
            slmin2 = view.findViewById(R.id.slmin2);
            slplus2 = view.findViewById(R.id.slplus2);
            slqty2 = view.findViewById(R.id.slqty2);
            grmin = view.findViewById(R.id.grmin);
            grplus = view.findViewById(R.id.grplus);
            grqty = view.findViewById(R.id.grqty);

            bjhqty.setText(String.valueOf(ibjhqty));
            bkqty.setText(String.valueOf(ibkqty));
            bcqty.setText(String.valueOf(ibcqty));
            slqty.setText(String.valueOf(islqty));
            slqty2.setText(String.valueOf(islqty2));
            grqty.setText(String.valueOf(igrqty));

//            Intent order = ((Activity) mContext).getIntent();
//            kategoris = order.getStringExtra("kategori");
//            serviss = order.getStringExtra("service");
//            namal = order.getStringExtra("namalaundry");

//            viewHolder.kategori1.setText(kategoris);
//            viewHolder.servis1.setText(serviss);

//            if (kategoris.equals("Normal")) {
//                viewHolder.hargabjh.setText("2000");
//            }

            final PricelistItem pli = (PricelistItem) getItem(position);

            if (kategoris == "Normal" && serviss == "Complete") {
                viewHolder.hargabjh.setText("2000");
//            hargabk.setText(pli.getBonekaN());
//            hargabc.setText(pli.getBedcoverN());
//            hargasl1.setText(pli.getSelimutbN());
//            hargasl2.setText(pli.getSelimutkN());
//            hargagr.setText(pli.getGordenN());
            } else if (viewHolder.kategori1.equals("Express") && viewHolder.servis1.equals("Complete")) {
                viewHolder.hargabjh.setText("5000");
//            hargabk.setText(pli.getBonekaE());
//            hargabc.setText(pli.getBedcoverE());
//            hargasl1.setText(pli.getSelimutbE());
//            hargasl2.setText(pli.getSelimutkE());
//            hargagr.setText(pli.getGordenE());
            }

//        else if (viewHolder.kategori1.equals("Normal")&&viewHolder.servis1.equals("Setrika")){
//            viewHolder.hargabjh.setText(pli.getSetrikaN());
//            lyboneka.setVisibility(lyboneka.GONE);
//            lybedcover.setVisibility(lybedcover.GONE);
//            lyselimutb.setVisibility(lyselimutb.GONE);
//            lyselimutk.setVisibility(lyselimutk.GONE);
//            lygorder.setVisibility(lygorder.GONE);
//        }
//
//        else if (viewHolder.kategori1.equals("Express")&&viewHolder.servis1.equals("Setrika")){
//            viewHolder.hargabjh.setText(pli.getSetrikaE());
//            lyboneka.setVisibility(lyboneka.GONE);
//            lybedcover.setVisibility(lybedcover.GONE);
//            lyselimutb.setVisibility(lyselimutb.GONE);
//            lyselimutk.setVisibility(lyselimutk.GONE);
//            lygorder.setVisibility(lygorder.GONE);
//        }
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        bjhmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibjhqty > 0) {
                    bjhmin.setClickable(true);
                    ibjhqty = ibjhqty - 1;
                    bjhqty.setText(String.valueOf(ibjhqty));
                } else if (ibjhqty < 0) {
                    bjhmin.setClickable(false);
                }
            }
        });

        bjhplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibjhqty = ibjhqty + 1;
                bjhqty.setText(String.valueOf(ibjhqty));
            }
        });

        bkmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibkqty > 0) {
                    bkmin.setClickable(true);
                    ibkqty = ibkqty - 1;
                    bkqty.setText(String.valueOf(ibkqty));
                } else if (ibkqty < 0) {
                    bkmin.setClickable(false);
                }
            }
        });

        bkplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibkqty = ibkqty + 1;
                bkqty.setText(String.valueOf(ibkqty));
            }
        });

        bcmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibcqty > 0) {
                    bcmin.setClickable(true);
                    ibcqty = ibcqty - 1;
                    bcqty.setText(String.valueOf(ibcqty));
                } else if (ibcqty < 0) {
                    bcmin.setClickable(false);
                }
            }
        });

        bcplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibcqty = ibcqty + 1;
                bcqty.setText(String.valueOf(ibcqty));
            }
        });

        slmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty > 0) {
                    slmin.setClickable(true);
                    islqty = islqty - 1;
                    slqty.setText(String.valueOf(islqty));
                } else if (islqty < 0) {
                    slmin.setClickable(false);
                }
            }
        });

        slplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                islqty = islqty + 1;
                slqty.setText(String.valueOf(islqty));
            }
        });

        slmin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty2 > 0) {
                    slmin2.setClickable(true);
                    islqty2 = islqty2 - 1;
                    slqty2.setText(String.valueOf(islqty2));
                } else if (islqty2 < 0) {
                    slmin2.setClickable(false);
                }
            }
        });

        slplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                islqty2 = islqty2 + 1;
                slqty2.setText(String.valueOf(islqty2));
            }
        });

        grmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (igrqty > 0) {
                    grmin.setClickable(true);
                    igrqty = igrqty - 1;
                    grqty.setText(String.valueOf(igrqty));
                } else if (igrqty < 0) {
                    grmin.setClickable(false);
                }
            }
        });

        grplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                igrqty = igrqty + 1;
                grqty.setText(String.valueOf(igrqty));
            }
        });

        return view;
    }

    public class ViewHolder {
        TextView hargabjh;
        TextView hargabk;
        TextView hargabc;
        TextView hargasl1;
        TextView hargasl2;
        TextView hargagr;
        TextView kategori1;
        TextView servis1;
        TextView namalaundrytest;
    }
}