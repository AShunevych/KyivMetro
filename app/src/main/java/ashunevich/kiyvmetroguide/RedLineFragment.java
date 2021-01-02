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
    String LOCALE;
    EventBus bus;


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
        LOCALE = Locale.getDefault().getDisplayLanguage();
        setRedLineButtonsBindings();
     //   Log.d("Locale is", LOCALE);
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void setRedLineButtonsBindings(){
        binding.akadem.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Akademmistechko)));
        binding.zhyto.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Zhytomyrska)));
        binding.sviatoshyn.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Sviatoshyn)));
        binding.nyvky.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Nyvky)));
        binding.berest.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Beresteiska)));
        binding.shuliavka.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Shuliavska)));
        binding.politech.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.PolitechJSON)));
        binding.vokzal.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Vokzalna)));
        binding.univer.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Universytet)));
        binding.teatr.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Teatralna)));
        binding.krest.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Khreshchatyk)));
        binding.arsenal.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Arsenalna)));
        binding.dnipro.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Dnipro)));
        binding.park.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Hidropark)));
        binding.livoberezhna.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Livoberezhna)));
        binding.darnytsia.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Darnytsia)));
        binding.chernihiv.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Chernihivska)));
        binding.lisova.setOnClickListener (v ->mSetAndSendText(getResources().getString(R.string.Lisova)));
    }

    private void mSetAndSendText(String stationName) {
        if(LOCALE.toLowerCase().contains(("uk"))){
            LineFragmentUtils.jsonObjToText(bus,"StationInfo_Ukr.json",stationName,requireContext());
        }
        else{
            LineFragmentUtils.jsonObjToText(bus,"StationInfo.json",stationName,requireContext());
        }
    }
}




