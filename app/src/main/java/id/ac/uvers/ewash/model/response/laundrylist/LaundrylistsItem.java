package id.ac.uvers.ewash.model.response.laundrylist;

import com.google.gson.annotations.SerializedName;

public class LaundrylistsItem{

	@SerializedName("jambuka")
	private String jambuka;

	@SerializedName("rating")
	private String rating;

	@SerializedName("jamtutup")
	private String jamtutup;

	@SerializedName("id_laundry")
	private String idLaundry;

	@SerializedName("namalaundry")
	private String namalaundry;

	@SerializedName("selfdelivery")
	private String selfdelivery;

	@SerializedName("kurirdelivery")
	private String kurirdelivery;


	public void setJambuka(String jambuka){
		this.jambuka = jambuka;
	}

	public String getJambuka(){
		return jambuka;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setJamtutup(String jamtutup){
		this.jamtutup = jamtutup;
	}

	public String getJamtutup(){
		return jamtutup;
	}

	public void setIdLaundry(String idLaundry){
		this.idLaundry = idLaundry;
	}

	public String getIdLaundry(){
		return idLaundry;
	}

	public void setNamalaundry(String namalaundry){
		this.namalaundry = namalaundry;
	}

	public String getNamalaundry(){
		return namalaundry;
	}

	public String getSelfdelivery() {
		return selfdelivery;
	}

	public void setSelfdelivery(String selfdelivery) {
		this.selfdelivery = selfdelivery;
	}

	public String getKurirdelivery() {
		return kurirdelivery;
	}

	public void setKurirdelivery(String kurirdelivery) {
		this.kurirdelivery = kurirdelivery;
	}
}