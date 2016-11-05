package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Page_Fragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

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
                hari = "Senin";
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
        View view = inflater.inflate(R.layout.activity_list_jadwal, container, false);
        //TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        //tvTitle.setText("Fragment #" + mPage);
        if (mPage == 1) {
            //muncul jadwal hari senin
        }




        return view;
    }


}