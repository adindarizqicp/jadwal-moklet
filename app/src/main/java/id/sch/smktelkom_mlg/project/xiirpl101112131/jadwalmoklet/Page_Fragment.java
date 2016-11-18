package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    RecyclerView recyclerView;
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
        setHasOptionsMenu(true);
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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        jpAdapter = new JadwalPelajaran_adapter(jpList);
        recyclerView.setAdapter(jpAdapter);

        String[] myKode, myMapel, myGuru;

        if (mPage == 1) {
            myKode = jadwal.getArray("Senin000");
            myMapel = jadwal.getArray("Senin001");
            myGuru = jadwal.getArray("Senin002");

            jpList.clear();
            for (int i = 0; i < myKode.length - 1; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        } else if (mPage == 2) {
            myKode = jadwal.getArray("Selasa000");
            myMapel = jadwal.getArray("Selasa001");
            myGuru = jadwal.getArray("Selasa002");

            jpList.clear();
            for (int i = 0; i < myKode.length - 1; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        } else if (mPage == 3) {
            myKode = jadwal.getArray("Rabu000");
            myMapel = jadwal.getArray("Rabu001");
            myGuru = jadwal.getArray("Rabu002");

            jpList.clear();
            for (int i = 0; i < myKode.length - 1; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        } else if (mPage == 4) {
            myKode = jadwal.getArray("Kamis000");
            myMapel = jadwal.getArray("Kamis001");
            myGuru = jadwal.getArray("Kamis002");

            jpList.clear();
            for (int i = 0; i < myKode.length - 1; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        } else if (mPage == 5) {
            myKode = jadwal.getArray("Jumat000");
            myMapel = jadwal.getArray("Jumat001");
            myGuru = jadwal.getArray("Jumat002");

            jpList.clear();
            for (int i = 0; i < myKode.length - 1; i++) {
                jpList.add(new JadwalPelajaran("" + (i + 1), myKode[i], myMapel[i], myGuru[i]));
            }
            jpAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_keluar) {


            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //sharedpreferences nya dihapus
                            Intent intent = new Intent(getContext(), activity_jadwal.class);
                            startActivity(intent);

                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Are you sure want to exit?").setNegativeButton("Yes", dialogClickListener)
                    .setPositiveButton("No", dialogClickListener).show();
            return true;


        }
        if (item.getItemId() == R.id.action_update) {
            if (jadwal.internetConnectionAvailable(5000) == true) {
                jadwal.updateDB();
            } else {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Your phone is not connected to internet, please make sure you have an internet connection").setNegativeButton("OK", dialogClickListener).show();
                //muncullll
            }
            //jpAdapter.swap(jpList);
            //jpAdapter.notifyDataSetChanged();
            //recyclerView.swapAdapter(jpAdapter, true);
            return true;
        }
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(getActivity(), EditJadwal.class);
            intent.putExtra("intHari", mPage);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(getActivity(), AboutUs.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
