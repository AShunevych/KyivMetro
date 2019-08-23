package ashunevich.kiyvmetroguide;

public class SendTextEvent {


    public String stationName;
    public String busRoutes;
    public String trolleyRoutes;
    public String taxicabRoutes;
    public String taxicabRoutes_SU;
    public String streets;
    public String notesText;




    public SendTextEvent(String stationName, String busRoutes, String trolleyRoutes,
                         String taxicabRoutes, String taxicabRoutes_SU, String streets, String notesText) {
        this.stationName = stationName;
        this.busRoutes = busRoutes;
        this.trolleyRoutes = trolleyRoutes;
        this.taxicabRoutes =taxicabRoutes;
        this.streets = streets;
        this.notesText = notesText;
        this.taxicabRoutes_SU = taxicabRoutes_SU;
    }
}

