package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import ashunevich.kiyvmetroguide.databinding.SearchActivityBinding;

public class JSONSearchFragment extends Fragment {
    String TAG = JSONSearchFragment.class.getSimpleName();
    private String LOCALE;
    private final ArrayList<JSONSearchItem> listContentArr = new ArrayList<>();
    JSONSearchItem item = new JSONSearchItem();
    JSONSearchItemAdapter adapter;
    private SearchActivityBinding binding;

    public JSONSearchFragment() {
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
        binding = SearchActivityBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        LOCALE = Locale.getDefault().getDisplayLanguage();
        binding.jsonRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new JSONSearchItemAdapter(listContentArr);
        binding.jsonRecycler.setHasFixedSize(true);
        buildRecyclerWithJson();
        binding.filterTextView.addTextChangedListener(watcher);
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
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
        if(LOCALE.toLowerCase().contains(("українська"))){
            loadJsonToRecycler("SearchInfo_Ukr.json");
        }
        else{
            loadJsonToRecycler("SearchInfo.json");
        }
    }

    private void loadJsonToRecycler(String loadJSON)  {
        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJsonEvent(loadJSON,requireContext()));
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

}