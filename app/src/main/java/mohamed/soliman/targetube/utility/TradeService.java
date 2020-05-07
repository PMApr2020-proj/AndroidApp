package mohamed.soliman.targetube.utility;


import java.util.HashMap;
import java.util.List;

import mohamed.soliman.targetube.model.CartLineUi;
import mohamed.soliman.targetube.model.Product;
import mohamed.soliman.targetube.model.SignInResponse;
import mohamed.soliman.targetube.model.SignUpResponse;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface TradeService {
    @GET(Urls.GetProducts)
    Call<List<Product>> getAllProducts();

    @GET(Urls.remoce_cart)
    Call<ResponseBody> removeCart(@Path("userId") Integer userId,@Header("Authorization") String token);



    @POST(Urls.SIGNIN)
    Call<SignInResponse>signIn(@Body HashMap registerApiPayload);

    @POST(Urls.SIGNUP)
    Call<SignUpResponse>signUp(@Body HashMap registerApiPayload);

    @GET(Urls.getAllCart)
    Call<CartLineUi> getAllCart(@Header("Authorization") String token);

    @HTTP(method = "PUT", path = Urls.EditCart, hasBody = true)
    Call<ResponseBody>editCart(@Body HashMap registerApiPayload,@Header("Authorization") String token);

    @HTTP(method = "DELETE", path = Urls.DeleteCart, hasBody = true)
    Call<ResponseBody>deleteCart(@Body HashMap registerApiPayload,@Header("Authorization") String token);

    @POST(Urls.AddToCart)
    Call<ResponseBody>addToCart(@Body HashMap registerApiPayload, @Header("Authorization") String token);


}
