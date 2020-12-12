package ashunevich.kiyvmetroguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;

import ashunevich.kiyvmetroguide.databinding.ListItemBinding;

public class JSONSearchItemAdapter extends RecyclerView.Adapter<JSONSearchItemAdapter.MyViewHolder> {
    //Creating an arraylist of POJO objects

    private ArrayList<JSONSearchItem> pad_list;


    public JSONSearchItemAdapter(ArrayList<JSONSearchItem> data){
        this.pad_list = data;
    }
    //This method inflates view present in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    //Binding the data using get() method of POJO object
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final JSONSearchItem list_items = pad_list.get(position);
        holder.binding.stationView.setText(list_items.getNameStation());
        holder.binding.lineView.setText(list_items.getStationLine());


        holder.binding.checkInfo.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),SearchResultActivity.class);
            intent.putExtra("StationName",list_items.getNameStation());
            //Log.d("Station name",list_items.getNameStation());
            view.getContext().startActivity(intent);
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
    static class MyViewHolder extends RecyclerView.ViewHolder {
     private final ListItemBinding binding;
        public MyViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}


