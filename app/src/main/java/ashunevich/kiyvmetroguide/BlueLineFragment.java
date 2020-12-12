package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Objects;

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
        if(LOCALE.equals("русский") | LOCALE.equals("українська") ){
            jsonObjToText("StationInfo_Ukr.json",stationName );
        }
        else{
            jsonObjToText("StationInfo_.json",stationName);
        }
    }

    public void jsonObjToText(String createdJsonName, String stationName) {
        bus = EventBus.getDefault();
        try {
            JSONObject emp = (new JSONObject(LineFragmentUtils.loadJsonEvent(createdJsonName, Objects.requireNonNull(getContext())))).getJSONObject(stationName);
            String mStation = emp.getString("Station");
            String mBusRoutes = emp.getString("Bus");
            String mTrolleyRoutes = emp.getString("Trolleybus");
            String mTaxicabRoutesU = emp.getString("TaxicabUrban");
            String mTaxicabRoutesSU = emp.getString("TaxicabSuburban");
            String mStreets = emp.getString("Exit To");
            String mNotes = emp.getString("Notes");
            bus.post(new SendTextEvent(mStation,
                    mBusRoutes,mTrolleyRoutes,
                    mTaxicabRoutesU,mTaxicabRoutesSU
                    ,mStreets,mNotes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}