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
        binding.Syrets.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Syrets)));
        binding.doroh.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Dorohozhychi)));
        binding.lukyanivska.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Lukianivska)));
        binding.vorota.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Vorota)));
        binding.palats.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.PalatsSportu)));
        binding.klovska.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Klovska)));
        binding.pecherska.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Pecherska)));
        binding.druzby.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.DruzhbyJSON)));
        binding.vydubychi.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Vydubychi)));
        binding.slavutych.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Slavutych)));
        binding.osokorky.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Osokorky)));
        binding.pozniaky.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Pozniaky)));
        binding.kharkiv.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Kharkivska)));
        binding.vyrlytsia.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Vyrlytsia)));
        binding.boryspil.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Boryspilska)));
        binding.redhutir.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.KhutirJSON)));
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
