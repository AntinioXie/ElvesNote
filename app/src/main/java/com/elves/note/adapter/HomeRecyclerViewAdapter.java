package com.elves.note.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elves.note.R;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.VH> {
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.home_view_list_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tittle.setText( data.get( position ) );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    ArrayList<String> data = new ArrayList<>(  );

    public ArrayList<String> getData() {
        return data;
    }

    public void addData(String s){
        data.add( s );
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tittle;
        public VH(@NonNull View itemView) {
            super( itemView );
            tittle = itemView.findViewById( R.id.content );
        }
    }
}
