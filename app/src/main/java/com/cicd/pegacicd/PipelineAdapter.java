package com.cicd.pegacicd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PipelineAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public PipelineAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Pipelines object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        PipelineHolder pipelineHolder = new PipelineHolder();

        if (row == null)
        {
            LayoutInflater layoutInflater =  (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);

            pipelineHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            row.setTag(pipelineHolder);

        }
        else
        {
            pipelineHolder = (PipelineHolder) row.getTag();
        }

        Pipelines pipelines = (Pipelines) this.getItem(position);
        pipelineHolder.tx_name.setText(pipelines.getPipelinename());

        return row;
    }

    static class PipelineHolder
    {
        TextView tx_name;
    }
}
