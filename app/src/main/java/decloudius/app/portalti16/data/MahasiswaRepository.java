package decloudius.app.portalti16.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import decloudius.app.portalti16.entity.Mahasiswa;

/**
 * Created by Umair on 1/7/2019.
 */


public class MahasiswaRepository {

    private final static String DB_NAME = "datalokal";
    private MahasiswaDatabase mahasiswaDatabase;

    public MahasiswaRepository(Context context){
        mahasiswaDatabase = Room.databaseBuilder(
                context,
                MahasiswaDatabase.class,
                DB_NAME
        ).build();
    }

    public void insertMahasiswa(final Mahasiswa mahasiswa){
        new AsyncTask<Void, Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                mahasiswaDatabase.mahasiswaDao().insert(mahasiswa);
                return null;
            }
        }.execute();
    }
}
