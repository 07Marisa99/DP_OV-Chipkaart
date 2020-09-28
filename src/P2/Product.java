package P2;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private String status;
    private String last_update;
    private List<OVChipkaart> ovChipkaarts = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, double prijs, String status, String last_update) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.status = status;
        this.last_update = last_update;
    }

    public void addToOV(OVChipkaart ovChipkaart) {
        ovChipkaarts.add(ovChipkaart);
        ovChipkaart.addProduct(this);
    }

    public List<OVChipkaart> getOvChipkaarts() {
        return ovChipkaarts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarts.add(ovChipkaart);
        ovChipkaart.addProduct(this);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public void deleteProductFromOV(OVChipkaart ovChipkaart) {
        if (ovChipkaart.getProducts().contains(this) && ovChipkaarts.contains(ovChipkaart)) {
            ovChipkaart.getProducts().remove(this);
            ovChipkaarts.remove(ovChipkaart);
        }
    }
}
