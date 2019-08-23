package ashunevich.kiyvmetroguide;

import android.os.Bundle;
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

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlueLineFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blue_line_activity, container, false);
        LOCALE = Locale.getDefault().getDisplayLanguage();
        //   Log.d("Locale is", LOCALE);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.heroiv, R.id.minska, R.id.Obolon, R.id.Pochaina,
            R.id.Kontraktova, R.id.Poshtova, R.id.Maidan, R.id.LvaTolstoga, R.id.Olimpiiska, R.id.PalatsUA, R.id.lybidska,
            R.id.Demiivska, R.id.Holosiivska, R.id.Vasylkiivska, R.id.Vystavkovyi,R.id.Ipodrom,R.id.Teremnky})
    protected void setViewOnClickEvent(View view) {
        switch (view.getId()) {
            case R.id.heroiv:
                mSetAndSendText(getResources().getString(R.string.Heroiv_Dnipra));
                break;
            case R.id.minska:
                mSetAndSendText(getResources().getString(R.string.Minska));
                break;
            case R.id.Obolon:
                mSetAndSendText(getResources().getString(R.string.Obolon));
                break;
            case R.id.Pochaina:
                mSetAndSendText(getResources().getString(R.string.Pochaina));
                break;
            case R.id.Kontraktova:
                mSetAndSendText(getResources().getString(R.string.KontraktovaJSON));
                break;
            case R.id.Poshtova:
                mSetAndSendText(getResources().getString(R.string.PoshtovaJSON));
                break;
            case R.id.Maidan:
                mSetAndSendText(getResources().getString(R.string.MaidanJSON));
                break;
            case R.id.LvaTolstoga:
                mSetAndSendText(getResources().getString(R.string.LvaJSON));
                break;
            case R.id.Olimpiiska:
                mSetAndSendText(getResources().getString(R.string.Olimpiiska));
                break;
            case R.id.PalatsUA:
                mSetAndSendText(getResources().getString(R.string.Palats));
                break;
            case R.id.lybidska:
                mSetAndSendText(getResources().getString(R.string.Lybidska));
                break;
            case R.id.Demiivska:
                mSetAndSendText(getResources().getString(R.string.Demiivska));
                break;
            case R.id.Holosiivska:
                mSetAndSendText(getResources().getString(R.string.Holosiivska));
                break;
            case R.id.Vasylkiivska:
                mSetAndSendText(getResources().getString(R.string.Vasylkivska));
                break;
            case R.id.Vystavkovyi:
                mSetAndSendText(getResources().getString(R.string.VystavkovyiJSON));
                break;
            case R.id.Ipodrom:
                mSetAndSendText(getResources().getString(R.string.Ipodrom));
                break;
            case R.id.Teremnky:
                mSetAndSendText(getResources().getString(R.string.Teremky));
                break;
        }
    }

    private void mSetAndSendText(String stationName) {
        if(LOCALE.equals("русский")){
            jsonObjToText("StationInfo_Ukr.json",stationName);
        }
        else{
            jsonObjToText("StationInfo_.json",stationName);
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