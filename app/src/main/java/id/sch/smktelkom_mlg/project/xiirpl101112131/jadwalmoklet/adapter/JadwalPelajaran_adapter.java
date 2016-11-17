package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.R;
import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.model.JadwalPelajaran;

/**
 * Created by Adinda Rizqi on 11/11/2016.
 */

public class JadwalPelajaran_adapter extends RecyclerView.Adapter<JadwalPelajaran_adapter.ViewHolder> {
    ArrayList<JadwalPelajaran> JP_list;

    public JadwalPelajaran_adapter(ArrayList<JadwalPelajaran> JP_list) {
        this.JP_list = JP_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JadwalPelajaran jp = JP_list.get(position);
        holder.tvNo.setText(jp.no);
        holder.tvKode.setText(jp.kode);
        holder.tvMapel.setText(jp.mapel);
        holder.tvGuru.setText(jp.guru);
    }

    @Override
    public int getItemCount() {
        if (JP_list != null)
            return JP_list.size();
        return 0;
    }

    public void swap(ArrayList<JadwalPelajaran> jp) {
        this.JP_list = jp;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKode, tvMapel, tvGuru, tvNo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNo = (TextView) itemView.findViewById(R.id.textViewNo);
            tvKode = (TextView) itemView.findViewById(R.id.textViewKode);
            tvMapel = (TextView) itemView.findViewById(R.id.textViewMapel);
            tvGuru = (TextView) itemView.findViewById(R.id.textViewGuru);
        }
    }
}
