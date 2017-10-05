package com.example.yassine.tp3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yassine on 04/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NameViewHolder> {

    private final ListItemClickListener listener;
    private Context context;
    private int mNumberItems;
    private List<String> data = Collections.emptyList();

    public MyAdapter(int mNumberItems, ListItemClickListener listener) {
        this.mNumberItems = mNumberItems;
        this.listener = listener;

        data = Arrays.asList(new String[]{"Yassine", "John", "Max", "Sam", "Jaymie", "Trenton", "Magnolia", "Williemae", "Ghislaine",
                "Glennie", "Celeste", "Rolanda", "Collin", "Kenyatta", "Janessa", "Delora", "Norberto", "Duane", "Carter", "Gwenda"});
    }


    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForItem, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NameViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.nameDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You clicked on " + holder.getName(), Toast.LENGTH_SHORT);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameDisplay;

        public NameViewHolder(View itemView) {
            super(itemView);
            this.nameDisplay = itemView.findViewById(R.id.nameView);
            itemView.setOnClickListener(this);
        }

        public String getName(){
            return (String)nameDisplay.getText();
        }

        public void bind(String name) {
            this.nameDisplay.setText(name);
        }


        @Override
        public void onClick(View view) {
            listener.onListItemClick(nameDisplay.getText().toString());
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(String name);
    }


}


