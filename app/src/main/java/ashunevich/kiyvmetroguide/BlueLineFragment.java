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
        binding.heroiv.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Heroiv_Dnipra)));
        binding.minska.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Minska)));
        binding.Obolon.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Obolon)));
        binding.Pochaina.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Pochaina)));
        binding.Kontraktova.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.KontraktovaJSON)));
        binding.Poshtova.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.PoshtovaJSON)));
        binding.Maidan.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.MaidanJSON)));
        binding.LvaTolstoga.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.LvaJSON)));
        binding.Olimpiiska.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Olimpiiska)));
        binding.PalatsUA.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Palats)));
        binding.lybidska.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Lybidska)));
        binding.Demiivska.setOnClickListener (v ->  mSetAndSendText(getResources().getString(R.string.Demiivska)));
        binding.Holosiivska.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Holosiivska)));
        binding.Vasylkiivska.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Vasylkivska)));
        binding.Vystavkovyi.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.VystavkovyiJSON)));
        binding.Ipodrom.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Ipodrom)));
        binding.Teremnky.setOnClickListener (v -> mSetAndSendText(getResources().getString(R.string.Teremky)));
    }

    private void mSetAndSendText(String stationName) {
        if(LOCALE.toLowerCase().contains(("українська"))){
            LineFragmentUtils.jsonObjToText(bus,"StationInfo_Ukr.json",stationName,requireContext());
        }
        else{
            LineFragmentUtils.jsonObjToText(bus,"StationInfo.json",stationName,requireContext());
        }
    }
}