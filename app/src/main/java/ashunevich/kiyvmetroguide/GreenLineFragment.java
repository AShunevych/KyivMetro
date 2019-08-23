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

public class GreenLineFragment extends Fragment {

    String LOCALE;
    EventBus bus;

    public GreenLineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.green_line_activity, container, false);
        LOCALE = Locale.getDefault().getDisplayLanguage();
        //   Log.d("Locale is", LOCALE);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.Syrets,R.id.doroh,R.id.lukyanivska,R.id.vorota,R.id.palats,
            R.id.klovska,R.id.pecherska,R.id.druzby,R.id.vydubychi,R.id.slavutych,R.id.osokorky,R.id.pozniaky,
            R.id.kharkiv,R.id.vyrlytsia,R.id.boryspil,R.id.redhutir})
    protected void setViewOnClickEvent(View view) {
        switch (view.getId()) {
            case R.id.Syrets:mSetAndSendText(getResources().getString(R.string.Syrets));break;
            case R.id.doroh:mSetAndSendText(getResources().getString(R.string.Dorohozhychi));break;
            case R.id.lukyanivska:mSetAndSendText(getResources().getString(R.string.Lukianivska));break;
            case R.id.vorota:mSetAndSendText(getResources().getString(R.string.Vorota));break;
            case R.id.palats:mSetAndSendText(getResources().getString(R.string.PalatsSportu));break;
            case R.id.klovska:mSetAndSendText(getResources().getString(R.string.Klovska));break;
            case R.id.pecherska:mSetAndSendText(getResources().getString(R.string.Pecherska));break;
            case R.id.druzby:mSetAndSendText(getResources().getString(R.string.DruzhbyJSON));break;
            case R.id.vydubychi:mSetAndSendText(getResources().getString(R.string.Vydubychi));break;
            case R.id.slavutych:mSetAndSendText(getResources().getString(R.string.Slavutych));break;
            case R.id.osokorky:mSetAndSendText(getResources().getString(R.string.Osokorky));break;
            case R.id.pozniaky:mSetAndSendText(getResources().getString(R.string.Pozniaky));break;
            case R.id.kharkiv:mSetAndSendText(getResources().getString(R.string.Kharkivska));break;
            case R.id.vyrlytsia:mSetAndSendText(getResources().getString(R.string.Vyrlytsia));break;
            case R.id.boryspil:mSetAndSendText(getResources().getString(R.string.Boryspilska));break;
            case R.id.redhutir:mSetAndSendText(getResources().getString(R.string.KhutirJSON));break;
        }
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
