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


public class RedLineFragment extends Fragment {

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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.red_line_activity, container, false);
        LOCALE = Locale.getDefault().getDisplayLanguage();
     //   Log.d("Locale is", LOCALE);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.akadem,R.id.zhyto,R.id.sviatoshyn,R.id.nyvky,R.id.berest,
          R.id.shuliavka,R.id.politech,R.id.vokzal,R.id.univer,R.id.teatr,R.id.krest,R.id.arsenal,
            R.id.dnipro,R.id.park,R.id.livoberezhna,R.id.darnytsia,R.id.chernihiv,R.id.lisova})
    protected void setViewOnClickEvent(View view) {
        switch (view.getId()) {
            case R.id.akadem:mSetAndSendText(getResources().getString(R.string.Akademmistechko));break;
            case R.id.zhyto:mSetAndSendText(getResources().getString(R.string.Zhytomyrska));break;
            case R.id.sviatoshyn:mSetAndSendText(getResources().getString(R.string.Sviatoshyn));break;
            case R.id.nyvky:mSetAndSendText(getResources().getString(R.string.Nyvky));break;
            case R.id.berest:mSetAndSendText(getResources().getString(R.string.Beresteiska));break;
            case R.id.shuliavka:mSetAndSendText(getResources().getString(R.string.Shuliavska));break;
            case R.id.politech:mSetAndSendText(getResources().getString(R.string.PolitechJSON));break;
            case R.id.vokzal:mSetAndSendText(getResources().getString(R.string.Vokzalna));break;
            case R.id.univer:mSetAndSendText(getResources().getString(R.string.Universytet));break;
            case R.id.teatr:mSetAndSendText(getResources().getString(R.string.Teatralna));break;
            case R.id.krest:mSetAndSendText(getResources().getString(R.string.Khreshchatyk));break;
            case R.id.arsenal:mSetAndSendText(getResources().getString(R.string.Arsenalna));break;
            case R.id.dnipro:mSetAndSendText(getResources().getString(R.string.Dnipro));break;
            case R.id.park:mSetAndSendText(getResources().getString(R.string.Hidropark));break;
            case R.id.livoberezhna:mSetAndSendText(getResources().getString(R.string.Livoberezhna));break;
            case R.id.darnytsia:mSetAndSendText(getResources().getString(R.string.Darnytsia));break;
            case R.id.chernihiv:mSetAndSendText(getResources().getString(R.string.Chernihivska));break;
            case R.id.lisova:mSetAndSendText(getResources().getString(R.string.Lisova));break;
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




