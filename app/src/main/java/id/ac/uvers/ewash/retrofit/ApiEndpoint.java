package id.ac.uvers.ewash.retrofit;

import java.util.List;

import id.ac.uvers.ewash.model.laundrylist.laundrylist;
import id.ac.uvers.ewash.model.laundrylist.responlaundrylist;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {

    @GET ("selectlaundry3.php")
    Call<responlaundrylist> lnlist();

}
