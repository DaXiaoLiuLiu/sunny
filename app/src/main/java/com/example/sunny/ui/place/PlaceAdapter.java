package com.example.sunny.ui.place;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sunny.R;
import com.example.sunny.SunnyWeatherApplication;
import com.example.sunny.WeatherActivity;

import com.example.sunny.logic.model.PlaceResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private static List<PlaceResponse.Place>  placeList;
    private static PlaceFragment fragment;

    public PlaceAdapter(PlaceFragment fragment,List<PlaceResponse.Place> placeList){
        this.placeList = placeList;
        this.fragment = fragment;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                PlaceResponse.Place place = placeList.get(position);
                Activity activity = fragment.getActivity();


                if(activity.getClass().equals(WeatherActivity.class)){
                    //搜索城市操作
                    SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
                    DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);

                    Log.d("PlaceAdapter","跳转响应1");
                    ((WeatherActivity) activity).getDrawerLayout().closeDrawers();
                    ((WeatherActivity) activity).getViewModel().locationLng = place.getLocation().getLng();
                    ((WeatherActivity) activity).getViewModel().locationLat = place.getLocation().getLat();
                    ((WeatherActivity) activity).getViewModel().placeName = place.getName();
                    ((WeatherActivity) activity).refreshWeather();

                }
                else{
                    //将数据上传
                    Log.d("PlaceAdapter","跳转响应2");
                    Intent intent = new Intent(parent.getContext(), WeatherActivity.class);
                    intent.putExtra("location_lng",place.getLocation().getLng());
                    intent.putExtra("location_lat",place.getLocation().getLat());
                    intent.putExtra("place_name",place.getName());
                    fragment.startActivity(intent);
                    activity.finish();
                }

                Log.d("PlaceAdapter","存储信息");
                fragment.getViewModel().savePlace(place);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            PlaceResponse.Place place = placeList.get(position);
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
