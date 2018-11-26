package decloudius.app.portalti16.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Umair on 11/26/2018.
 */


public class Network {

    public static Retrofit request(){
        return new Retrofit.Builder()
                .baseUrl("https://ti16.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}
