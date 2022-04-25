package id.ac.uvers.ewash.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import id.ac.uvers.ewash.R;
import id.ac.uvers.ewash.model.response.pricelist.PricelistItem;

public class orderlaundryAdapter2 extends BaseAdapter {

    Activity activity;
    List<PricelistItem> items;
    private LayoutInflater inflater;
    private TextView hargabj, hargabk, hargabc, hargasl1, hargasl2, hargagr, hargastrk;
    private TextView kategori, servis;
    private LinearLayout lyboneka, lybedcover, lyselimutb, lyselimutk, lygorder;
    

    public orderlaundryAdapter2 (Activity activity, List<PricelistItem> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) view = inflater.inflate(R.layout.productlist,null);

        hargabj = (TextView) view.findViewById(R.id.hargabjhd);
        hargabk = (TextView) view.findViewById(R.id.hargabkd);
        hargabc = (TextView) view.findViewById(R.id.hargabcd);
        hargasl1 = (TextView) view.findViewById(R.id.hargasld1);
        hargasl2 = (TextView) view.findViewById(R.id.hargasld2);
        hargagr = (TextView) view.findViewById(R.id.hargagrd);
        kategori = (TextView) view.findViewById(R.id.kategori);
        servis = (TextView) view.findViewById(R.id.service);
        lyboneka = view.findViewById(R.id.lyboneka);
        lybedcover = view.findViewById(R.id.lybedcover);
        lyselimutb = view.findViewById(R.id.lyselimutb);
        lyselimutk = view.findViewById(R.id.lyselimutk);
        lygorder = view.findViewById(R.id.lygorden);

        PricelistItem pli = items.get(position);

        if (kategori.equals("Normal")&&servis.equals("Complete")){
            hargabj.setText(pli.getBajuN());
            hargabk.setText(pli.getBonekaN());
            hargabc.setText(pli.getBedcoverN());
            hargasl1.setText(pli.getSelimutbN());
            hargasl2.setText(pli.getSelimutkN());
            hargagr.setText(pli.getGordenN());
        }

        else if (kategori.equals("Express")&&servis.equals("Complete")){
            hargabj.setText(pli.getBajuE());
            hargabk.setText(pli.getBonekaE());
            hargabc.setText(pli.getBedcoverE());
            hargasl1.setText(pli.getSelimutbE());
            hargasl2.setText(pli.getSelimutkE());
            hargagr.setText(pli.getGordenE());
        }

        else if (kategori.equals("Normal")&&servis.equals("Setrika")){
            hargabj.setText(pli.getSetrikaN());
            lyboneka.setVisibility(lyboneka.GONE);
            lybedcover.setVisibility(lybedcover.GONE);
            lyselimutb.setVisibility(lyselimutb.GONE);
            lyselimutk.setVisibility(lyselimutk.GONE);
            lygorder.setVisibility(lygorder.GONE);
        }

        else if (kategori.equals("Express")&&servis.equals("Setrika")){
            hargabj.setText(pli.getSetrikaE());
            lyboneka.setVisibility(lyboneka.GONE);
            lybedcover.setVisibility(lybedcover.GONE);
            lyselimutb.setVisibility(lyselimutb.GONE);
            lyselimutk.setVisibility(lyselimutk.GONE);
            lygorder.setVisibility(lygorder.GONE);
        }

        return view;
    }
}
