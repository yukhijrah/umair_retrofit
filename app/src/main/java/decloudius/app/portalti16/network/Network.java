package decloudius.app.portalti16.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Umair on 11/26/2018.
 */


public class Network {

    public static Retrofit request(){
        //instance interceptor dengan cara
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        //setiap ada request ke network, kita monitoring body nya, dengan cara
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //buat client agar bisa menggunakan interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                //main url dari web
                .baseUrl("http://35.186.145.167:1337/")
                //tambah client okhttp
                .client(client)
                //ini melakukan konversi dari json string ke java object
                .addConverterFactory(GsonConverterFactory.create())
                //built the f-ing code!!
                .build();
    }

}
