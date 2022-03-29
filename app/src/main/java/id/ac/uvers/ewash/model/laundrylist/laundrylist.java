package id.ac.uvers.ewash.model.laundrylist;

public class laundrylist {

    String namalaundry;
    String jambuka;
    String jamtutup;
    int rating;
    int id_laundry;


    public laundrylist() {}
    public laundrylist (int id_laundry, String namalaundry, String jambuka, String jamtutup, int rating){
        this.id_laundry = id_laundry;
        this.namalaundry = namalaundry;
        this.jambuka = jambuka;
        this.jamtutup = jamtutup;
        this.rating = rating;
    }



    public int getId_laundry() {
        return id_laundry;
    }

    public void setId_laundry(int id_laundry) {
        this.id_laundry = id_laundry;
    }

    public String getNamalaundry() {
        return namalaundry;
    }

    public void setNamalaundry(String namalaundry) {
        this.namalaundry = namalaundry;
    }

    public String getJambuka() {
        return jambuka;
    }

    public void setJambuka(String jambuka) {
        this.jambuka = jambuka;
    }

    public String getJamtutup() {
        return jamtutup;
    }

    public void setJamtutup(String jamtutup) {
        this.jamtutup = jamtutup;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
