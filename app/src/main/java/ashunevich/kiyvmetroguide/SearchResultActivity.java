package ashunevich.kiyvmetroguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.Locale;

import ashunevich.kiyvmetroguide.databinding.InformationResultLayoutBinding;

public class SearchResultActivity extends AppCompatActivity {

     String LOCALE;
    private InformationResultLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InformationResultLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        String value = intent.getStringExtra("StationName");
        LOCALE = Locale.getDefault().getDisplayLanguage();
        Log.d("LOCALE",LOCALE);
        setResult(value);

    }

    private void setResult(String stationName) {
        if(LOCALE.toLowerCase().contains(("українська"))){
            serResultTextFromJson("StationInfo_Ukr.json",stationName);
        }
      else{
            serResultTextFromJson("StationInfo.json",stationName);
        }
    }

    public void serResultTextFromJson(String loadedJson, String loadedIntentValue) throws NullPointerException {
        try {
            JSONObject emp = (new JSONObject(Utils.loadJsonEvent(loadedJson,this))).getJSONObject(loadedIntentValue);
            binding.infoStation.setText(emp.getString("Station"));
            binding.infoLine.setText(emp.getString("Line"));
            binding.busInfo.setText(emp.getString("Bus"));
            binding.trolleyInfo.setText(emp.getString("Trolleybus"));
            binding.infoUTaxiCub.setText(emp.getString("TaxicabUrban"));
            binding.infoSuTaxiCub.setText(emp.getString("TaxicabSuburban"));
            binding.infoStreets.setText(emp.getString("Exit To"));
            binding.infoNotes.setText(emp.getString("Notes"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
