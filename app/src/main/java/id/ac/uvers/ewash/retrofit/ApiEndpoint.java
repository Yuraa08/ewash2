package id.ac.uvers.ewash.retrofit;

import id.ac.uvers.ewash.model.response.laundrylist.Responselaundrylist;

import id.ac.uvers.ewash.model.response.pricelist.ResponsePricelist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET ("selectlaundry3.php")
    Call<Responselaundrylist> lnlist();

//    @GET ("pricelist.php")
//    Call<ResponsePricelist> pllist();

    @GET("pricelist.php")
    Call<ResponsePricelist> pllist(@Query("namalaundry") String namalaundry);

//    @GET("pricelistaa.php")
//    Call<ResponsePricelist> pllist(@Query("userlaundry") String userlaundry);

}
