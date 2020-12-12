package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;


import ashunevich.kiyvmetroguide.databinding.SearchActivityBinding;

public class JSONSearchActivity extends AppCompatActivity {
    String TAG = JSONSearchActivity.class.getSimpleName();
    private String LOCALE;
    private final ArrayList<JSONSearchItem> listContentArr = new ArrayList<>();
    JSONSearchItem item = new JSONSearchItem();
    JSONSearchItemAdapter adapter;
    private SearchActivityBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SearchActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        LOCALE = Locale.getDefault().getDisplayLanguage();
        binding.jsonRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JSONSearchItemAdapter(listContentArr);
        binding.jsonRecycler.setHasFixedSize(true);
        buildRecyclerWithJson();
        binding.filterTextView.addTextChangedListener(watcher);
    }


    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            filter(editable.toString());
        }
    };

    private void filter(String text) {
        ArrayList<JSONSearchItem> filteredList = new ArrayList<>();

        for (JSONSearchItem item : listContentArr) {
            if (item.getNameStation().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);

    }


    private void buildRecyclerWithJson(){
        if(LOCALE.equals("русский")){
            loadJsonToRecycler("SearchInfo_Ukr.json");
        }
        else{
            loadJsonToRecycler("SearchInfo.json");
        }
    }

    private void loadJsonToRecycler(String loadJSON)  {
        try {
            JSONObject jsonObject = new JSONObject(loadJsonEvent(loadJSON));
            JSONArray message = jsonObject.getJSONArray("Station");
            Gson gson =new Gson();
            for (int i = 0; i <= message.length(); i++) {
                JSONObject temp =message.getJSONObject(i);
                item = gson.fromJson(temp.toString(), JSONSearchItem.class);
                listContentArr.add(item);
                adapter.notifyDataSetChanged();
                adapter.setListContent(listContentArr);
                binding.jsonRecycler.setAdapter(adapter);
            }
    } catch (Exception ex) {
        Log.e(TAG, ex.getMessage());
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

