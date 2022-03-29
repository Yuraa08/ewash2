package id.ac.uvers.ewash.model.laundrylist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responlaundrylist {

    private List<laundrylist> laundrylists;
    private String msg;
    private int id_laundry;

    @SerializedName("message")
    private String massage;

    public String getMsg() {return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public List<laundrylist> getLaundrylists() {
        return laundrylists;
    }

    public void setLaundrylists(List<laundrylist> laundrylists) {
        this.laundrylists = laundrylists;
    }

    public int getId_laundry() {
        return id_laundry;
    }

    public void setId_laundry(int id_laundry) {
        this.id_laundry = id_laundry;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
