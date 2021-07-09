package com.example.sunny.ui.place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunny.R;
import com.example.sunny.logic.model.Place;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private static List<Place>  placeList;

    public PlaceAdapter(Fragment fragment,List<Place> placeList){
        this.placeList = placeList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            Place place = placeList.get(position);
            holder.placeName.setText(place.getName());
            holder.placeAddress.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView placeName;
        TextView placeAddress;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeAddress = (TextView) itemView.findViewById(R.id.placeAddress);
        }
    }
}
