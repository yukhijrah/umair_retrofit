package decloudius.app.portalti16.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import decloudius.app.portalti16.entity.Mahasiswa;
import decloudius.app.portalti16.holder.MahasiswaHolder;

/**
 * Created by Umair on 11/26/2018.
 */


public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder>{

    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        this.mahasiswas;
    }

    @Override
    public MahasiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
