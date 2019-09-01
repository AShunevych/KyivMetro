package ashunevich.kiyvmetroguide;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JSONSearchItemAdapter extends RecyclerView.Adapter<JSONSearchItemAdapter.MyViewHolder> {
    //Creating an arraylist of POJO objects


    private ArrayList<JSONSearchItem> pad_list;
    View view;
    MyViewHolder holder;

    public JSONSearchItemAdapter(ArrayList<JSONSearchItem> data){
        this.pad_list = data;
    }
    //This method inflates view present in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        holder = new MyViewHolder(view);
        return holder;
    }

    //Binding the data using get() method of POJO object
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final JSONSearchItem list_items = pad_list.get(position);
        holder.stationName.setText(list_items.getNameStation());
        holder.lineName.setText(list_items.getStationLine());


        holder.checkInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SearchResultActivity.class);
                intent.putExtra("StationName",list_items.getNameStation());
                //Log.d("Station name",list_items.getNameStation());
                view.getContext().startActivity(intent);
            }
        });
    }

    public void filterList(ArrayList<JSONSearchItem> filterdNames) {
        this.pad_list = filterdNames;
        notifyDataSetChanged();
    }

    //Setting the arraylist
    public void setListContent(ArrayList <JSONSearchItem> pad_list) {
        this.pad_list = pad_list;
    }

    @Override
    public int getItemCount() {
        return pad_list.size();
    }

    //View holder class, where all view components are defined
    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stationView) TextView stationName;
        @BindView(R.id.lineView) TextView lineName;
        @BindView(R.id.checkInfo) ImageButton checkInfoButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



}


