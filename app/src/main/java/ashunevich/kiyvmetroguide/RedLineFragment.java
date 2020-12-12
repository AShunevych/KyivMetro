package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        if(LOCALE.equals("русский")){
            jsonObjToText("StationInfo_Ukr.json",stationName);
        }
        else{
            jsonObjToText("StationInfo.json",stationName);
        }
    }


    public void jsonObjToText(String createdJsonName, String stationName) {
        bus = EventBus.getDefault();
        try {
            JSONObject emp = (new JSONObject(loadJsonEvent(createdJsonName))).getJSONObject(stationName);
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

    public String loadJsonEvent(String jsonName) {
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open(jsonName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


}




