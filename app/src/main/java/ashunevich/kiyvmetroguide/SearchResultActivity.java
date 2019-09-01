package ashunevich.kiyvmetroguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {

     String LOCALE;
    public static boolean mIsNightMode = false;

    @BindView(R.id.info_Station)
    TextView station;
    @BindView(R.id.info_Line)
    TextView line;
    @BindView(R.id.bus_Info)
    TextView bus;
    @BindView(R.id.trolley_info)
    TextView trolley;
    @BindView(R.id.info_UTaxiCub)
    TextView uTaxiCub;
    @BindView(R.id.info_SuTaxiCub)
    TextView suTaxiCub;
    @BindView(R.id.info_Streets)
    TextView streets;
    @BindView(R.id.info_Notes)
    TextView notes;
    @BindView(R.id.finishThisAct)
    ImageButton backToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_result_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
         String value = intent.getStringExtra("StationName");
        LOCALE = Locale.getDefault().getDisplayLanguage();
        setJsonOnCreate(value);

        backToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });
    }

    private void setJsonOnCreate(String stationName) {
        if(LOCALE.equals("русский")){

            jsonToTextView("StationInfo_Ukr.json",stationName);
        }
        else{
            jsonToTextView("StationInfo.json",stationName);
        }
    }


    public void jsonToTextView(String loadedJson, String loadedIntentVAlue) throws NullPointerException {
        try {
            JSONObject emp = (new JSONObject(loadJsonEvent(loadedJson))).getJSONObject(loadedIntentVAlue);
            station.setText(emp.getString("Station"));
            line.setText(emp.getString("Line"));
            bus.setText(emp.getString("Bus"));
            trolley.setText(emp.getString("Trolleybus"));
            uTaxiCub.setText(emp.getString("TaxicabUrban"));
            suTaxiCub.setText(emp.getString("TaxicabSuburban"));
            streets.setText(emp.getString("Exit To"));
            notes.setText(emp.getString("Notes"));
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
