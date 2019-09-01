package ashunevich.kiyvmetroguide;

import com.google.gson.annotations.SerializedName;


public class JSONSearchItem {

    @SerializedName("Line")
    private String stationLine;
    @SerializedName("Name")
    private String nameStation;

    public JSONSearchItem() {

    }

    public String getStationLine() {
        return stationLine;
    }


    public String getNameStation() {
        return nameStation;
    }





}

