package ashunevich.kiyvmetroguide;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


abstract class Utils {

    static protected String loadJsonEvent(String jsonName, Context context) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(jsonName);
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



    static public void jsonObjToText(EventBus bus,String createdJsonName, String stationName,Context context) {
        bus = EventBus.getDefault();
        try {
            JSONObject emp = (new JSONObject(loadJsonEvent(createdJsonName,
                    Objects.requireNonNull(context)))).getJSONObject(stationName);
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

    static protected void setStationInfo ( String stationName, String locale, EventBus bus, Context context){
        if(locale.toLowerCase().contains(("українська"))){
           jsonObjToText(bus,"StationInfo_Ukr.json",stationName,context);
        }
        else{
           jsonObjToText(bus,"StationInfo.json",stationName,context);
        }
    }



}
