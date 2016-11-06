package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Page_Fragment extends ListFragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    View view;
    JadwalDB jadwal;
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

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    public void ambilData(int nHari) {
        String hari;
        switch (nHari) {
            case 1:
                hari = "MataPelajaran";
                break;
            case 2:
                hari = "Selasa";
                break;
            case 3:
                hari = "Rabu";
                break;
            case 4:
                hari = "Kamis";
                break;
            case 5:
                hari = "Jumat";
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_jadwal, container, false);
        //TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        //tvTitle.setText("Fragment #" + mPage);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        jadwal = new JadwalDB(view.getContext());

        view.findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jadwal.getSenin();
                jadwal.convert();
            }
        });

        if (mPage == 1) {
            //muncul jadwal hari senin

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, jadwal.DBSeninToArray());
            adapter.notifyDataSetChanged();
            try {
                getListView().setAdapter(adapter);
            } catch (Exception e) {
                Log.e("INI LO", "onActivityCreated: " + e.toString());
            }

            //error("Mau kesini nggk?");
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