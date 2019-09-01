package ashunevich.kiyvmetroguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationFragment extends Fragment {


    @BindView(R.id.stationName)
    TextView station;
    @BindView(R.id.notesText)
    TextView notes;
    @BindView(R.id.busRoutes)
    TextView buses;
    @BindView(R.id.trolleyRoutes)
    TextView trolleys;
    @BindView(R.id.taxiCabRouts)
    TextView taxicabsU;
    @BindView(R.id.taxiCabRouts_SU)
    TextView taxicabSU;
    @BindView(R.id.streetsView)
    TextView streets;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart(){
        if (!EventBus.getDefault().isRegistered(this)) { EventBus.getDefault().register(this); }
        super.onStart();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.information_layout, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Subscribe
    public void getText (SendTextEvent event){
      station.setText(event.stationName);
      buses.setText(event.busRoutes);
      trolleys.setText(event.trolleyRoutes);
      taxicabsU.setText(event.taxicabRoutes);
      taxicabSU.setText(event.taxicabRoutes_SU);
      streets.setText(event.streets);
      notes.setText(event.notesText);
    }


    @Override
    public void onDetach() {
        if (EventBus.getDefault().isRegistered(this)) { EventBus.getDefault().unregister(this); }
        super.onDetach();
    }

}

