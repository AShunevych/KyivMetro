package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;


import ashunevich.kiyvmetroguide.databinding.BlueLineActivityBinding;

public class BlueLineFragment extends Fragment {

    private BlueLineActivityBinding binding;
    String LOCALE;
    EventBus bus;

    public BlueLineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = BlueLineActivityBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        LOCALE = Locale.getDefault().getDisplayLanguage();
        setBlueLineButtonsBindings();
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }


    private void setBlueLineButtonsBindings(){
        binding.heroiv.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Heroiv_Dnipra),LOCALE,bus,requireContext()));
        binding.minska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Minska),LOCALE,bus,requireContext()));
        binding.Obolon.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Obolon),LOCALE,bus,requireContext()));
        binding.Pochaina.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Pochaina),LOCALE,bus,requireContext()));
        binding.Kontraktova.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.KontraktovaJSON),LOCALE,bus,requireContext()));
        binding.Poshtova.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.PoshtovaJSON),LOCALE,bus,requireContext()));
        binding.Maidan.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.MaidanJSON),LOCALE,bus,requireContext()));
        binding.LvaTolstoga.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.LvaJSON),LOCALE,bus,requireContext()));
        binding.Olimpiiska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Olimpiiska),LOCALE,bus,requireContext()));
        binding.PalatsUA.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Palats),LOCALE,bus,requireContext()));
        binding.lybidska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Lybidska),LOCALE,bus,requireContext()));
        binding.Demiivska.setOnClickListener (v ->  Utils.setStationInfo(getResources().getString(R.string.Demiivska),LOCALE,bus,requireContext()));
        binding.Holosiivska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Holosiivska),LOCALE,bus,requireContext()));
        binding.Vasylkiivska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vasylkivska),LOCALE,bus,requireContext()));
        binding.Vystavkovyi.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.VystavkovyiJSON),LOCALE,bus,requireContext()));
        binding.Ipodrom.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Ipodrom),LOCALE,bus,requireContext()));
        binding.Teremnky.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Teremky),LOCALE,bus,requireContext()));
        binding.BlueGreenTransfer.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.PalatsSportu),LOCALE,bus,requireContext()));
        binding.BlueRedTransfer.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Khreshchatyk),LOCALE,bus,requireContext()));
    }

}