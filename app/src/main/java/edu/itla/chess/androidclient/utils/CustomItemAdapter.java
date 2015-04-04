package edu.itla.chess.androidclient.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.itla.chess.androidclient.utils.viewholders.ViewHolder;

/**
 * Created by Manuel Inoa on 3/29/15.
 */
public class CustomItemAdapter<T, V extends ViewHolder> extends ArrayAdapter<T> {

    private final LayoutInflater inflater;
    private List<T> items;
    private ViewHolder viewHolder;
    private final int layoutId;

    public CustomItemAdapter(Context context, int resource, List<T> objects,
                             Class<V> viewHolderClass) throws Exception {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.items = objects;
        this.layoutId = resource;
        this.viewHolder = viewHolderClass.newInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final Object item = items.get(position);

            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder.setView(convertView);
            convertView.setTag(viewHolder);

            viewHolder.setValues(item);
        } catch (Exception e) {

        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void updateData(List<T> objects) {
        this.items = objects;
        notifyDataSetChanged();
    }
}
