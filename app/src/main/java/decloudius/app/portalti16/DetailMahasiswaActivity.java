package decloudius.app.portalti16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import decloudius.app.portalti16.entity.Mahasiswa;
import decloudius.app.portalti16.network.Network;
import decloudius.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMahasiswaActivity extends AppCompatActivity {

    private EditText edtName, edtNim;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtNim = (EditText) findViewById(R.id.edt_nim);
        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String nim = edtNim.getText().toString();
                addNewMahasiswa(name, nim);
            }
        });
    }

    private void addNewMahasiswa(String name, String nim){
        Routes services = Network.request().create(Routes.class);

        //lalu lakukan post terhadap post pada mahasiswa baru terhadap API /add.php
        services.postMahasiswa(name, nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if (response.isSuccessful()){
                    finish();//kembalik ke aktifitas sebelumnya
                } else {
                    onErrorMahasiswa();
                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {

                onErrorMahasiswa();
            }
        });
    }
    private void onErrorMahasiswa(){
        Toast.makeText(DetailMahasiswaActivity.this, "Maaf, terjadi kesalahan", Toast.LENGTH_SHORT).show();

    }
}
