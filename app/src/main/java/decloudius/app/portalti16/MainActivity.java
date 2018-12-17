package decloudius.app.portalti16;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import decloudius.app.portalti16.adapter.MahasiswaAdapter;
import decloudius.app.portalti16.entity.DaftarMahasiswa;
import decloudius.app.portalti16.entity.Mahasiswa;
import decloudius.app.portalti16.network.Network;
import decloudius.app.portalti16.network.Routes;
import decloudius.app.portalti16.util.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Umair
 */


public class MainActivity extends AppCompatActivity{

    RecyclerView lstMahasiswa;
    FloatingActionButton btnMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting recycler
        lstMahasiswa = (RecyclerView)findViewById(R.id.lst_mahasiswa);
        lstMahasiswa.setLayoutManager(new LinearLayoutManager(this));

        btnMahasiswa = (FloatingActionButton) findViewById(R.id.btn_to_add);

        requestDaftarMahasiswa();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
        onButtonMahasiswa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menampilkan menu di activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                //ketica icon di click, maka....
                requestDaftarMahasiswa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestDaftarMahasiswa(){
        //pertama, memanggil request dari retrofit yang sudah dibuat
        final Routes services = Network.request().create(Routes.class);

        //kite melakukan request terhadap getMahasiswa()
        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
               //cek request yang dilakukan, berhasil atau tidak
                if(response.isSuccessful()){
                    //casting data yang didapatkan menjadi DaftarMahasiswa
                    DaftarMahasiswa mahasiswas = response.body();

                    //get title
                    Log.d("TI16", mahasiswas.getTitle());

                    //tampilkan daftar mahasiswa di recycler view
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getData());
                    //handle delete button
                    adapter.setListener(new MahasiswaAdapter.MahasiswaListener(){
                        @Override
                        public void onDelete(int mhsId) {
                            String id = String.valueOf(mhsId); //konversi int to string
                            deleteMahasiswa(services, id);

                        }
                    });
                    lstMahasiswa.setAdapter(adapter);
                }else{
                    onMahasiswaError();
                }

            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {

            }
        });
    }

    private void onMahasiswaError(){
        Toast.makeText(MainActivity.this,"Gagal, Silahkan periksa koneksi internet anda",Toast.LENGTH_LONG).show();
    }

    private void onButtonMahasiswa(){
        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, DetailMahasiswaActivity.class);
                addIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_ADD);
                startActivity(addIntent);
            }
        });
    }

    private void deleteMahasiswa(final Routes service,final String mhsId){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("Are you sure ?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                service.deleteMahasiswa(mhsId).enqueue(new Callback<Mahasiswa>() {
                    @Override
                    public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                        if (response.isSuccessful()){
                            requestDaftarMahasiswa();
                        } else {
                            onMahasiswaError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mahasiswa> call, Throwable t) {
                        onMahasiswaError();
                    }
                });

            }
        });
        alert.show();


    }


}
