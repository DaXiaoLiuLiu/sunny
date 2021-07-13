package com.example.sunny.ui.place;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunny.MainActivity;
import com.example.sunny.R;

import com.example.sunny.WeatherActivity;
import com.example.sunny.logic.model.PlaceResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlaceFragment extends Fragment {
    private PlaceViewModel viewModel;
    //private Adapter PlaceAdapter;


    public PlaceViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        return inflater.inflate(R.layout.fragment_place,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getClass().equals(MainActivity.class) && viewModel.isPlaceSaved()){
            PlaceResponse.Place place = viewModel.getSavedPlace();
            Intent intent = new Intent(getContext(), WeatherActivity.class);
            intent.putExtra("location_lng",place.getLocation().getLng());
            intent.putExtra("location_lat",place.getLocation().getLat());
            intent.putExtra("place_name",place.getName());

            startActivity(intent);
            getActivity().finish();
            return;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        PlaceAdapter adapter = new PlaceAdapter(this,viewModel.getPlaceList());
        recyclerView.setAdapter(adapter);

        ImageView bgImageView;
        bgImageView = getActivity().findViewById(R.id.bgImageView);

        EditText searchPlaceEdit = getActivity().findViewById(R.id.searchPlaceEdit);
        searchPlaceEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if(content.isEmpty()){
                    recyclerView.setVisibility(View.GONE);

                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.listClear();
                    adapter.notifyDataSetChanged();
                }
                else {
                    viewModel.searchPlace(content);
                }
            }

        });

        viewModel.placeLiveData.observe(getViewLifecycleOwner(), new Observer<List<PlaceResponse.Place>>() {
            @Override
            public void onChanged(List<PlaceResponse.Place> placeList) {
                if(placeList != null){
                    recyclerView.setVisibility(View.VISIBLE);
                    bgImageView.setVisibility(View.GONE);
                    viewModel.listClear();
                    viewModel.addAllList(placeList);
                    adapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(getActivity(),"未能查询到任何地点",Toast.LENGTH_SHORT).show();
                    //错误处理
                }
            }
        });
    }


}
