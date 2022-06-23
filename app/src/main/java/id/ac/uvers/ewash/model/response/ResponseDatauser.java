package id.ac.uvers.ewash.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDatauser {

	@SerializedName("msg")
	private String msg;

	@SerializedName("datauser")
	private List<DatauserItem> datauser;

	@SerializedName("username")
	private String username;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setDatauser(List<DatauserItem> datauser){
		this.datauser = datauser;
	}

	public List<DatauserItem> getDatauser(){
		return datauser;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}