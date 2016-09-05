import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;
/**
 * Created by spbw0-rep6 on 05.09.2016.
 */
public class DetailContact extends Contact implements Comparable<DetailContact>{
    static final long serialVersionUID = 10L;
    private String address;
    private String workplace;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWorkplace() {
        return workplace;
    }

    @Override
    public int compareTo(DetailContact o) {
        return this.getName().compareTo(o.getName());
    }

    public void getInfo(String line) {
        String[] information = line.split(", ");
        String name = information[0];
        String phone = information[1];
        String email = information[2];
        String address = information[3];
        String workplace = information[4];
        System.out.print (name + ", " + phone + ", " + email + ", " + address + ", " + workplace + "\n");
    }
}
