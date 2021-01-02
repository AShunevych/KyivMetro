package ashunevich.kiyvmetroguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import ashunevich.kiyvmetroguide.databinding.InformationResultLayoutBinding;

public class SearchResultActivity extends AppCompatActivity {

     String LOCALE;
    private InformationResultLayoutBinding bindingl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingl = InformationResultLayoutBinding.inflate(getLayoutInflater());
        View view = bindingl.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        String value = intent.getStringExtra("StationName");
        LOCALE = Locale.getDefault().getDisplayLanguage();
        Log.d("LOCALE",LOCALE);
        setResult(value);

    }

    private void setResult(String stationName) {
        if(LOCALE.toLowerCase().contains(("uk"))){
            jsonToTextView("StationInfo_Ukr.json",stationName);
        }
      else{
            jsonToTextView("StationInfo.json",stationName);
        }
    }


    public void jsonToTextView(String loadedJson, String loadedIntentVAlue) throws NullPointerException {
        try {
            JSONObject emp = (new JSONObject(loadJsonEvent(loadedJson))).getJSONObject(loadedIntentVAlue);
            bindingl.infoStation.setText(emp.getString("Station"));
            bindingl.infoLine.setText(emp.getString("Line"));
            bindingl.busInfo.setText(emp.getString("Bus"));
            bindingl.trolleyInfo.setText(emp.getString("Trolleybus"));
            bindingl.infoUTaxiCub.setText(emp.getString("TaxicabUrban"));
            bindingl.infoSuTaxiCub.setText(emp.getString("TaxicabSuburban"));
            bindingl.infoStreets.setText(emp.getString("Exit To"));
            bindingl.infoNotes.setText(emp.getString("Notes"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public String loadJsonEvent(String jsonName) {
        String json = null;
        try {
            InputStream inputStream = getApplicationContext().getAssets().open(jsonName);
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
