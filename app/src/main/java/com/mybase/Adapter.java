package com.mybase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.mvc.IDataAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 俞智威
 * on 2016-06-08
 * 09:18
 * Procedure Explain:
 */
public class Adapter extends BaseAdapter implements IDataAdapter<List<TextBean>> {

    private List<TextBean> books = new ArrayList<TextBean>();
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context) {
        super();
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_text, parent, false);
            holder=new Holder();
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        }else
            holder = (Holder) convertView.getTag();
        holder.textView.setText(books.get(position).getText());
        holder. textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public void notifyDataChanged(List<TextBean> data, boolean isRefresh) {
        if (isRefresh) {
            books.clear();
        }
        books.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public List<TextBean> getData() {
        return books;
    }

    public class Holder{
        TextView textView;
    }
}
