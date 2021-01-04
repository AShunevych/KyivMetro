package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import java.util.Locale;

import ashunevich.kiyvmetroguide.databinding.RedLineActivityBinding;



public class RedLineFragment extends Fragment {

    private RedLineActivityBinding binding;
    String CURRENT_LOCALE;
    EventBus busEvent;


    public RedLineFragment() {
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
        binding = RedLineActivityBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        CURRENT_LOCALE = Locale.getDefault().getDisplayLanguage();
        setRedLineButtonsBindings();
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    //Buttons click listeners
    @SuppressWarnings("ConstantConditions")
    private void setRedLineButtonsBindings(){
        binding.akadem.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Akademmistechko), CURRENT_LOCALE, busEvent,requireContext()));
        binding.zhyto.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Zhytomyrska), CURRENT_LOCALE, busEvent,requireContext()));
        binding.sviatoshyn.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Sviatoshyn), CURRENT_LOCALE, busEvent,requireContext()));
        binding.nyvky.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Nyvky), CURRENT_LOCALE, busEvent,requireContext()));
        binding.berest.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Beresteiska), CURRENT_LOCALE, busEvent,requireContext()));
        binding.shuliavka.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Shuliavska), CURRENT_LOCALE, busEvent,requireContext()));
        binding.politech.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.PolitechJSON), CURRENT_LOCALE, busEvent,requireContext()));
        binding.vokzal.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vokzalna), CURRENT_LOCALE, busEvent,requireContext()));
        binding.univer.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Universytet), CURRENT_LOCALE, busEvent,requireContext()));
        binding.teatr.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Teatralna), CURRENT_LOCALE, busEvent,requireContext()));
        binding.krest.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Khreshchatyk), CURRENT_LOCALE, busEvent,requireContext()));
        binding.arsenal.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Arsenalna),CURRENT_LOCALE, busEvent,requireContext()));
        binding.dnipro.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Dnipro), CURRENT_LOCALE, busEvent,requireContext()));
        binding.park.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Hidropark), CURRENT_LOCALE, busEvent,requireContext()));
        binding.livoberezhna.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Livoberezhna), CURRENT_LOCALE, busEvent,requireContext()));
        binding.darnytsia.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Darnytsia), CURRENT_LOCALE, busEvent,requireContext()));
        binding.chernihiv.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Chernihivska), CURRENT_LOCALE, busEvent,requireContext()));
        binding.redBlueTransfer.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.MaidanJSON), CURRENT_LOCALE, busEvent,requireContext()));
        binding.redGreenTransfer.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vorota), CURRENT_LOCALE, busEvent,requireContext()));
    }

}




