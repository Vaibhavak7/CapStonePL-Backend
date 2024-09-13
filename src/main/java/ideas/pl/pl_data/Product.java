package ideas.pl.pl_data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String categoryName;
    private float productPrice;
    private int productRating;

    public Product() {
        // TODO Auto-generated constructor stub
    }

    public Product(Integer productId, String productName, String categoryName, float productPrice, int productRating) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.productPrice = productPrice;
        this.productRating = productRating;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    @Override
    public String toString() {
        return "\nProduct [productId=" + productId + ", productName=" + productName + ", categoryName=" + categoryName
                + ", productPrice=" + productPrice + ", productRating=" + productRating + "]\n";
    }

}
