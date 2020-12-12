package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ashunevich.kiyvmetroguide.databinding.InformationLayoutBinding;

public class InformationFragment extends Fragment {


    private InformationLayoutBinding binding;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart(){
        if (!EventBus.getDefault().isRegistered(this)) { EventBus.getDefault().register(this); }
        super.onStart();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = InformationLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Subscribe
    public void getText (SendTextEvent event){
        binding.stationName.setText(event.stationName);
        binding.busRoutes.setText(event.busRoutes);
        binding.trolleyRoutes.setText(event.trolleyRoutes);
        binding.taxiCabRouts.setText(event.taxicabRoutes);
        binding.taxiCabRoutsSU.setText(event.taxicabRoutes_SU);
        binding.streetsView.setText(event.streets);
        binding.notesText.setText(event.notesText);
    }


    @Override
    public void onDetach() {
        if (EventBus.getDefault().isRegistered(this)) { EventBus.getDefault().unregister(this); }
        super.onDetach();
    }

}

