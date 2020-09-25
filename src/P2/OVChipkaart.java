package P2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    private int id;
    private Date enddate;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;
    private List<Product> products = new ArrayList<>();

    public OVChipkaart(int ovID, String end, int klas, double sal, Reiziger reiziger) {
        id = ovID;
        enddate = java.sql.Date.valueOf(end);
        klasse = klas;
        saldo = sal;
        this.reiziger = reiziger;
        reiziger.addOvChipkaart(this);
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public int getId() {
        return id;
    }

    public Date getEnddate() {
        return enddate;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
