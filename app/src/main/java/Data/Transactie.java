package Data;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Transactie
{
    private String id;
    private Date timestamp;
    private double totaal;
    private String klantNaam;
    private User ober;
    private User Preparateur;
    private Tafel tafel;
    private ArrayList<Order> orders;
    private Status status;

    public Transactie(String id, double totaal,String klantNaam, User ober,User Preparateur, Tafel tafel, ArrayList<Order> orders, Status status)
    {
        this.id = id;
         this.timestamp = new Timestamp(System.currentTimeMillis());
        this.totaal = totaal;
        this.klantNaam = klantNaam;
        this.ober = ober;
        this.Preparateur = Preparateur;
        this.tafel = tafel;
        this.orders =orders;
        this.status = status;
    }

    public Transactie(String klantNaam, User ober, Tafel tafel)
    {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.totaal = 0;
        this.klantNaam = klantNaam;
        this.ober = ober;
        this.tafel = tafel;
        this.status = Status.Open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(String klantNaam) {
        this.klantNaam = klantNaam;
    }

    public User getOber() {
        return ober;
    }

    public void setOber(User ober) {
        this.ober = ober;
    }

    public User getPreparateur() {
        return Preparateur;
    }

    public void setPreparateur(User preparateur) {
        Preparateur = preparateur;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
