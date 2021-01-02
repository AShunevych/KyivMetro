package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;
import ashunevich.kiyvmetroguide.databinding.GreenLineActivityBinding;

public class GreenLineFragment extends Fragment {

    String LOCALE;
    EventBus bus;
    private GreenLineActivityBinding binding;

    public GreenLineFragment() {
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
       binding = GreenLineActivityBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        LOCALE = Locale.getDefault().getDisplayLanguage();
        //   Log.d("Locale is", LOCALE);
        setGreenLineButtonsBindings();
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void setGreenLineButtonsBindings(){
        binding.Syrets.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Syrets),LOCALE,bus,requireContext()));
        binding.doroh.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Dorohozhychi),LOCALE,bus,requireContext()));
        binding.lukyanivska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Lukianivska),LOCALE,bus,requireContext()));
        binding.vorota.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vorota),LOCALE,bus,requireContext()));
        binding.palats.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.PalatsSportu),LOCALE,bus,requireContext()));
        binding.klovska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Klovska),LOCALE,bus,requireContext()));
        binding.pecherska.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Pecherska),LOCALE,bus,requireContext()));
        binding.druzby.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.DruzhbyJSON),LOCALE,bus,requireContext()));
        binding.vydubychi.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vydubychi),LOCALE,bus,requireContext()));
        binding.slavutych.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Slavutych),LOCALE,bus,requireContext()));
        binding.osokorky.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Osokorky),LOCALE,bus,requireContext()));
        binding.pozniaky.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Pozniaky),LOCALE,bus,requireContext()));
        binding.kharkiv.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Kharkivska),LOCALE,bus,requireContext()));
        binding.vyrlytsia.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Vyrlytsia),LOCALE,bus,requireContext()));
        binding.boryspil.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.Boryspilska),LOCALE,bus,requireContext()));
        binding.redhutir.setOnClickListener (v -> Utils.setStationInfo(getResources().getString(R.string.KhutirJSON),LOCALE,bus,requireContext()));
    }

}
