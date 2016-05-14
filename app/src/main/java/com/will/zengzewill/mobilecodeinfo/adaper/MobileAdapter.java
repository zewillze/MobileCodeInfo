package com.will.zengzewill.mobilecodeinfo.adaper;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.will.zengzewill.mobilecodeinfo.R;
import com.will.zengzewill.mobilecodeinfo.model.InfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ze on 16/4/22.
 */
public class MobileAdapter extends BaseAdapter{
    Activity context;
    List<InfoModel> models;

    public MobileAdapter(Activity context, List<InfoModel> models){
        super();
        this.context = context;
        this.models = models;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    private class ViewHolder{
        TextView phoneTitle;
        TextView infoTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.mobilecell, null);
            holder = new ViewHolder();
            holder.phoneTitle = (TextView)convertView.findViewById(R.id.phone);
            holder.infoTitle = (TextView)convertView.findViewById(R.id.info);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        InfoModel m = (InfoModel) getItem(position);

        holder.phoneTitle.setText(m.getPhone());
        holder.infoTitle.setText(m.getJsonString());
        return convertView;
    }
}
