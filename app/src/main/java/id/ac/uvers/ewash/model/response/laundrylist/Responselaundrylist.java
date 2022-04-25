package id.ac.uvers.ewash.model.response.laundrylist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Responselaundrylist{

	@SerializedName("msg")
	private String msg;

	@SerializedName("laundrylists")
	private List<LaundrylistsItem> laundrylists;

	@SerializedName("id_laundry")
	private int idLaundry;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setLaundrylists(List<LaundrylistsItem> laundrylists){
		this.laundrylists = laundrylists;
	}

	public List<LaundrylistsItem> getLaundrylists(){
		return laundrylists;
	}

	public void setIdLaundry(int idLaundry){
		this.idLaundry = idLaundry;
	}

	public int getIdLaundry(){
		return idLaundry;
	}
}