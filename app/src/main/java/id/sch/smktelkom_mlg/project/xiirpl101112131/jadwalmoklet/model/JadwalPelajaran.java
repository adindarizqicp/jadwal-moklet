package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.model;

/**
 * Created by Adinda Rizqi on 11/11/2016.
 */

public class JadwalPelajaran {
    public String kode, mapel, guru;
    public String no;

    public JadwalPelajaran(String no, String kode, String mapel, String guru) {
        this.no = no;
        this.kode = kode;
        this.mapel = mapel;
        this.guru = guru;
    }
}
