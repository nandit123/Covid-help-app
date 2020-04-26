package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
//    private ArrayList<String> list;
    private ArrayList<ItemData> itemsData;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewTitle;
        public TextView textViewText;
        public ImageView iView;
        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
//            textViewTitle = (TextView) itemLayoutView.findViewById(R.id.title);
//            textViewText = (TextView) itemLayoutView.findViewById(R.id.text);
//            iView = (ImageView) itemLayoutView.findViewById(R.id.imageView);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
//    public MyAdapter(String[] myDataset) {
    public MyAdapter(ArrayList<ItemData> myItemsData) {
//        mDataset = myDataset;
//        list = mylist;
        itemsData = myItemsData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;

        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, null);

        // create ViewHolder

        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset[position]);
//        holder.textView.setText(list.get(position));
//        holder.textViewTitle.setText(itemsData.get(position).getTitle());
//        holder.textViewText.setText(itemsData.get(position).getAddress());
//        holder.iView.setImageURI(Uri.parse(itemsData.get(position).getImageId()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        return mDataset.length;
        return itemsData.size();
    }
}

