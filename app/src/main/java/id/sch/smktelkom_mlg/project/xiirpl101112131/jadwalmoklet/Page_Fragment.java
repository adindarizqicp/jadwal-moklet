package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.adapter.JadwalPelajaran_adapter;
import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.model.JadwalPelajaran;

public class Page_Fragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    View view;
    JadwalDB jadwal;
    ArrayList<JadwalPelajaran> jpList = new ArrayList<>();
    JadwalPelajaran_adapter jpAdapter;
    private int mPage;

    public static Page_Fragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Page_Fragment fragment = new Page_Fragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_jadwal, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        jadwal = new JadwalDB(view.getContext());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        jpAdapter = new JadwalPelajaran_adapter(jpList);
        recyclerView.setAdapter(jpAdapter);

        String[] myKode, myMapel, myGuru;

        if (mPage == 1) {
            myKode = jadwal.getArray("Senin000");
            myMapel = jadwal.getArray("Senin001");
            myGuru = jadwal.getArray("Senin002");

            for (int i = 0; i < myKode.length; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        } else if (mPage == 2) {
            /*JadwalDB jadwal = new JadwalDB();
            jadwal.getSelasa();
            adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, jadwal.getList());
            adapter.notifyDataSetChanged();
            getListView().setAdapter(adapter);
            //message("hah?!");*/
        }
    }

    public void error(String teks) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(teks);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}