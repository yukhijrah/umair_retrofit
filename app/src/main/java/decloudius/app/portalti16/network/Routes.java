package decloudius.app.portalti16.network;

import decloudius.app.portalti16.entity.DaftarMahasiswa;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Umair on 11/26/2018.
 */


public interface Routes {

    /*
    medefinisikan route dari web services yang disediakan
    jika di deskripsikan, berarti:
    https://ti16.herokuapp.com/list.php
     */

    @GET("list.php")
    Call<DaftarMahasiswa> getMahasiswa();

}
