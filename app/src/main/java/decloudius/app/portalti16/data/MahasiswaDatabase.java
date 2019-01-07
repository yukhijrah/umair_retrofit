package decloudius.app.portalti16.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import decloudius.app.portalti16.entity.Mahasiswa;

/**
 * Created by Umair on 1/7/2019.
 */

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class MahasiswaDatabase extends RoomDatabase {
    public abstract MahasiswaDao mahasiswaDao();
}
