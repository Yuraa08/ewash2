package id.ac.uvers.ewash.model.response.pricelist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePricelist {

	@SerializedName("msg")
	private String msg;

	@SerializedName("pricelist")
	private List<PricelistItem> pricelist;

	@SerializedName("id")
	private int id;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setPricelist(List<PricelistItem> pricelist){
		this.pricelist = pricelist;
	}

	public List<PricelistItem> getPricelist(){
		return pricelist;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}