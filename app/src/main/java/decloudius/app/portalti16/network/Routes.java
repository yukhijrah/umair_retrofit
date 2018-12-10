package decloudius.app.portalti16.network;

import decloudius.app.portalti16.entity.DaftarMahasiswa;
import decloudius.app.portalti16.entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Umair on 11/26/2018.
 */


public interface Routes {

    /**
    medefinisikan route dari web services yang disediakan
    jika di deskripsikan, berarti:
    https://ti16.herokuapp.com/list.php
     */
    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    /**
    medefinisikan route dari web services yang disediakan
    jika di deskripsikan, berarti:
    https://ti16.herokuapp.com/add.php
     @param name
     @param nim
     */
    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );

}
