package com.example.gpsmarker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpsmarker.databinding.FragmentTitleBinding;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleFragment extends Fragment {

    public MainActivity mainActivity;
    private FragmentTitleBinding binding;
    private QueryController queryController;

    public TitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TitleFragment newInstance() {
        TitleFragment fragment = new TitleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTitleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextView latCoordsTextView = binding.latCoordinatesTextView;
        TextView longCoordsTextView= binding.longCoordinatesTextView;
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        binding.savedLocationsButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_savedLocationsFragment));


        mainActivity = (MainActivity) getActivity();
        queryController = mainActivity.queryController;
        binding.markLocationButton.setOnClickListener(v -> {
            addCoordinates();
            queryController.addRow((String)latCoordsTextView.getText(), (String)longCoordsTextView.getText());
        });

        queryController = mainActivity.queryController;


        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latCoordsTextView.setText(String.valueOf(location.getLatitude()));
                longCoordsTextView.setText(String.valueOf(location.getLongitude()));
            }
        });

        return view;
    }


    private void addCoordinates(){
        mainActivity.coordinatesList.add(new Coordinates((String)binding.latCoordinatesTextView.getText(), (String)binding.longCoordinatesTextView.getText()));
    }



}