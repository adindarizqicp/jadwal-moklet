package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.R;

/**
 * Created by dell on 28/08/2016.
 */
public class JurusanAngkaAdapter extends ArrayAdapter<String> {

    String mKelas = "";

    public JurusanAngkaAdapter(Context context, ArrayList<String> listKota) {
        super(context, R.layout.row_spinner_kelas, listKota);
    }

    public void setKelas(String kelas) {
        this.mKelas = kelas;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return getCustomView(position, view, parent);
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        return getCustomView(position, view, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_spinner_kelas, parent, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.textViewTitle);
        tvTitle.setText(getItem(position).substring(0, getItem(position).indexOf(" "))); //INI
        TextView tvJurusanAngka = (TextView) view.findViewById(R.id.textViewJurusanAngka);
        tvJurusanAngka.setText(getItem(position));
        TextView tvKelas = (TextView) view.findViewById(R.id.textViewKelas);
        tvKelas.setText(mKelas);

        return view;
    }
}
