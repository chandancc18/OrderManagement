/**
 * Chandan Chhaparia
 * 991549130
 * Final Project
 */
package content;

public class Order {

    private String OrdID;
    private String CustID;
    private String product;
    private String shipping;

    public Order(String OrdID) {
        this.OrdID = OrdID;
    }

    public String getOrdID() {
        return OrdID;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

}
