package P2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPostgres implements ProductDAO{
    private Connection connection;

    public ProductDAOPostgres(Connection myConn) {
        connection = myConn;
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

        Product product = null;
        if (ovChipkaart.getId() == kaart_nummer) {
            if (product_Nummer == product_nummer) {
                product = new Product(product_Nummer, naam, beschrijving, prijs, status, last_update, ovChipkaart);
            }
        }
        return product;
    }
    @Override
    public List<Product> readAllProducts(List<OVChipkaart> ovChipkaarts) {
        List<Product> products = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet productRs = myStmt.executeQuery("SELECT * FROM product;");
            while (productRs.next()) {
                Statement myStmt2 = connection.createStatement();
                ResultSet OVProduct = myStmt2.executeQuery("SELECT * FROM ov_chipkaart_product;");
                while (OVProduct.next()) {
                    for (OVChipkaart ovChipkaart : ovChipkaarts) {
                        Product product = toProduct(productRs, OVProduct, ovChipkaart);
                        if (product != null) {
                            products.add(product);
                        }
                    }
                }
            }
        } catch (Exception e) {

        } return products;
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
                    Product product = toProduct(productRs, OVProduct, ovChipkaart);
                    if (product != null) {
                        products.add(product);

                    }
                }
            }
        } catch (Exception e) {

        } return products;
    }

    @Override
    public boolean createProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }
}
