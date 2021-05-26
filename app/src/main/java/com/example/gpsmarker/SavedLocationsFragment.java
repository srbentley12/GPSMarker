package com.example.gpsmarker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gpsmarker.databinding.FragmentSavedLocationsBinding;
import com.example.gpsmarker.databinding.FragmentTitleBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedLocationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedLocationsFragment extends Fragment {

    private FragmentSavedLocationsBinding binding;
    private RecyclerView recyclerView;
    public MainActivity mainActivity;
    private ArrayList<Coordinates> coordinatesList;

    public SavedLocationsFragment() {
        // Required empty public constructor
    }

    public static SavedLocationsFragment newInstance() {
        SavedLocationsFragment fragment = new SavedLocationsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSavedLocationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainActivity = (MainActivity) getActivity();
        recyclerView = binding.recyclerView;
        coordinatesList = mainActivity.coordinatesList;

        setAdapter();


        binding.currentLocationButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_savedLocationsFragment_to_titleFragment));


        return view;
    }

    private void setAdapter() {

        RecyclerAdapter adapter = new RecyclerAdapter(coordinatesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}