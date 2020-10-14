package P2;

import P1.MainFuncties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPostgres implements ProductDAO{
    private Connection connection;
    private OVChipkaartDAOPostgres ovChipkaartDAOPostgres;
    private MainFuncties mainFuncties;


    public ProductDAOPostgres(Connection myConn, MainFuncties mainFuncties) {
        connection = myConn;
        this.mainFuncties = mainFuncties;
    }

    public void setOvChipkaartDAOPostgres(OVChipkaartDAOPostgres ovChipkaartDAOPostgres) {
        this.ovChipkaartDAOPostgres = ovChipkaartDAOPostgres;
    }

    private Product exists(int productId) {
        for (Product product : mainFuncties.getProducts()) {
            if (product.getProduct_nummer() == productId) {
                return product;
            }
        } return null;
    }

    private Product toProduct(ResultSet productRs, ResultSet OVProduct, OVChipkaart ovChipkaart) throws SQLException {

        int kaart_nummer = OVProduct.getInt("kaart_nummer");
        int product_Nummer = OVProduct.getInt("product_nummer");
        String status = OVProduct.getString("status");
        String last_update = OVProduct.getString("last_update");

        int product_nummer = productRs.getInt("product_nummer");
        String naam = productRs.getString("naam");
        String beschrijving = productRs.getString("beschrijving");
        double prijs = productRs.getDouble("prijs");

//        doe dit aan de hand van OVChipkaart omdat het pas echt uitmaakt of het product compleet is als ik alle producten wil zien
//        in dat geval roep ik readAllProducts() aan en dan worden alle producten aangemaakt en alle OVChips gelezen en goed toegevoegd
//        in het geval van readByOV() is het belangrijker om te zien welke producten er bij het OV horen.
//        ook zou het aanroepen van de OVChipkaartDAO reader zorgen voor StackOverflow omdat die deze functie weer aanroept.

        Product product = null;
        if (exists(product_nummer) == null) {
            if (product_Nummer == product_nummer) {
                product = new Product(product_Nummer, naam, beschrijving, prijs, status, last_update);
                if (ovChipkaart.getId() == kaart_nummer) {
                    product.addToOV(ovChipkaart);
                    mainFuncties.addProduct(product);
                }
            }
        } else {
            product = exists(product_nummer);
            product.setBeschrijving(beschrijving);
            product.setNaam(naam);
            product.setPrijs(prijs);
        } return product;
    }
    @Override
    public List<Product> readAllProducts() {
        List<OVChipkaart> ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart();
        List<Product> products = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet productRs = myStmt.executeQuery("SELECT * FROM product;");
            while (productRs.next()) {
                Statement myStmt2 = connection.createStatement();
                ResultSet OVProduct = myStmt2.executeQuery("SELECT * FROM ov_chipkaart_product;");
                while (OVProduct.next()) {
                    for (OVChipkaart ovChipkaart : ovChipkaarts) {
                        toProduct(productRs, OVProduct, ovChipkaart);
                        products.add(toProduct(productRs, OVProduct, ovChipkaart));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainFuncties.getProducts();
    }

    @Override
    public List<Product> readProductByOV(OVChipkaart ovChipkaart) {
        List<Product> products = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet productRs = myStmt.executeQuery("SELECT * FROM product;");
            while (productRs.next()) {
                Statement myStmt2 = connection.createStatement();
                ResultSet OVProduct = myStmt2.executeQuery("SELECT * FROM ov_chipkaart_product;");
                while (OVProduct.next()) {
                    toProduct(productRs, OVProduct, ovChipkaart);
                    products.add(toProduct(productRs, OVProduct, ovChipkaart));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return ovChipkaart.getProducts();
    }

    @Override
    public boolean createProduct(Product product) {
        try {
            Statement myStmt = connection.createStatement();
                String sql = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) " +
                    "VALUES (" + product.getProduct_nummer() + ", '" + product.getNaam() + "', '" + product.getBeschrijving() + "', '" + product.getPrijs() + "');";
            myStmt.executeUpdate(sql);
            if (mainFuncties.getOvChipkaarts().size() != 0) {
                for (OVChipkaart ovChipkaart : product.getOvChipkaarts()) {
                    sql = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update)" +
                            "VALUES (" + ovChipkaart.getId() + ", '" + product.getProduct_nummer() + "', '" + product.getStatus() + "', '" + product.getLast_update() + "');";
                    myStmt.executeUpdate(sql);
                    System.out.println(sql);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("UPDATE product SET naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?;");
            myStmt.setString(1, product.getNaam());
            myStmt.setString(2, product.getBeschrijving());
            myStmt.setDouble(3, product.getPrijs());
            myStmt.setInt(4, product.getProduct_nummer());
            myStmt.executeUpdate();

            myStmt = connection.prepareStatement("DELETE FROM ov_chipkaart_product WHERE product_id = " + product.getProduct_nummer() + ";");

            for (OVChipkaart ovChipkaart : product.getOvChipkaarts()) {
                myStmt = connection.prepareStatement("INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer)" +
                        "VALUES ("+ ovChipkaart.getId() + ", " + product.getProduct_nummer() + ");");
                myStmt.setInt(1, ovChipkaart.getId());
                myStmt.setInt(1, product.getProduct_nummer());
                myStmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart_product WHERE product_nummer = " + product.getProduct_nummer() + ";";
            myStmt.executeUpdate(sql);
            sql = "DELETE FROM product WHERE product_nummer = " + product.getProduct_nummer() + ";";
            myStmt.executeUpdate(sql);
            int i1 = product.getOvChipkaarts().size();
            int i2 = 0;
            while (i1 > i2) {
                if (i1 >= product.getOvChipkaarts().size()) {
                    if (product.getOvChipkaarts().get(i2).getProducts().contains(product) || product.getOvChipkaarts().contains(product.getOvChipkaarts().get(i2))) {
                        mainFuncties.getProducts().remove(product);
                        deleteProductFromOV(product, product.getOvChipkaarts().get(i2));
                    } else {
                        i2++;
                    }
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
    @Override
    public boolean deleteProductFromOV(Product product, OVChipkaart ovChipkaart) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart_product WHERE product_nummer =" + product.getProduct_nummer() + " AND kaart_nummer = " + ovChipkaart.getId() + ";";
            myStmt.executeUpdate(sql);
            product.deleteProductFromOV(ovChipkaart);
        } catch (Exception e) {
            e.printStackTrace();
        } return true;
    }
}
