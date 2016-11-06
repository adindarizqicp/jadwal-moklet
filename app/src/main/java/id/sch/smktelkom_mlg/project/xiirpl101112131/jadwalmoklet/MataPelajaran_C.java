package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

/**
 * Created by Adinda Rizqi on 11/5/2016.
 */

public class MataPelajaran_C {
    private String Nama, XIIRPL, XIITKJ, XIRPL, XITKJ, XRPL, XTKJ;

    public String getNama() {
        return Nama;
    }

    public String getJadwal_C(String kelas) {
        switch (kelas) {
            case "XRPL":
                return getXRPL();
            case "XIRPL":
                return getXIRPL();
            case "XIIRPL":
                return getXIIRPL();
            case "XTKJ":
                return getXTKJ();
            case "XITKJ":
                return getXITKJ();
            case "XIITKJ":
                return getXIITKJ();
        }
        return null;
    }

    public String getXRPL() {
        return XRPL;
    }

    public String getXTKJ() {
        return XTKJ;
    }

    public String getXIRPL() {
        return XIRPL;
    }

    public String getXITKJ() {
        return XITKJ;
    }

    public String getXIIRPL() {
        return XIIRPL;
    }

    public String getXIITKJ() {
        return XIITKJ;
    }
}
