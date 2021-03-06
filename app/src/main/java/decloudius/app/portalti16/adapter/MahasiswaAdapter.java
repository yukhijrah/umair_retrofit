package decloudius.app.portalti16.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import decloudius.app.portalti16.DetailMahasiswaActivity;
import decloudius.app.portalti16.R;
import decloudius.app.portalti16.entity.Mahasiswa;
import decloudius.app.portalti16.holder.MahasiswaHolder;
import decloudius.app.portalti16.util.Consts;

/**
 * Created by Umair on 11/26/2018.
 */


public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder>{

    private List<Mahasiswa> mahasiswas;
    private MahasiswaListener listener;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public void setListener(MahasiswaListener listener) {
        this.listener = listener;
    }

    @Override
    public MahasiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        final MahasiswaHolder mahasiswaHolder = new MahasiswaHolder(itemView);

        final Context context = mahasiswaHolder.itemView.getContext();
        mahasiswaHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //definisikan postition untuk getMahasiswa.
                int adapterPosition = mahasiswaHolder.getAdapterPosition();
                Mahasiswa mahasiswa = mahasiswas.get(adapterPosition);


                Intent detailIntent = new Intent(context, DetailMahasiswaActivity.class);
                detailIntent.putExtra("Mahasiswa", mahasiswa);
                detailIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_EDIT);
                context.startActivity(detailIntent);
            }
        });

        return mahasiswaHolder;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, final int position) {
        holder.txtNama.setText(mahasiswas.get(position).getName());
        holder.txtNim.setText(mahasiswas.get(position).getNim());

        //fungsi delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(mahasiswas.get(position).getId());
            }
        });

        //fungsi fav
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavorite(mahasiswas.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public interface MahasiswaListener{
        void onDelete(int mhsId);
        void onFavorite(Mahasiswa mahasiswa);
    }
}

