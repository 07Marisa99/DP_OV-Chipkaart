package P2;

import java.util.List;

public interface ProductDAO {
    List<Product> readAllProducts(List<OVChipkaart> ovChipkaarts);
    List<Product> readProductByOV(OVChipkaart ovChipkaart);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
}
