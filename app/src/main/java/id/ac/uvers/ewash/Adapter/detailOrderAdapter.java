package id.ac.uvers.ewash.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import id.ac.uvers.ewash.model.response.pricelist.PricelistItem;

public class detailOrderAdapter extends BaseAdapter {

    Context ctx;
    List<PricelistItem> detailorder;
    ViewHolder viewHolder;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    private class ViewHolder {

    }
}
