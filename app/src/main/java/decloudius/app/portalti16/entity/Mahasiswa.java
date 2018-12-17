package decloudius.app.portalti16.entity;

import java.io.Serializable;

/**
 * Created by Umair on 11/26/2018.
 */


public class Mahasiswa implements Serializable{

    private int id;
    private String name;
    private String nim;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
