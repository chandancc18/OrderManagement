/**
 * Chandan Chhaparia
 * 991549130
 * Final Project
 */
package diff;

import content.Order;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StageTwo extends Stage {

    private Label txtInfo = new Label();

    private ArrayList<Order> orderList = new ArrayList<>();
    private String prodInfo;

    public StageTwo(ArrayList<Order> orderList, String prodInfo) {
        this.orderList = orderList;
        this.prodInfo = prodInfo;
        setScene(addScene());
    }

    private Scene addScene() {
        String sData = new String();
        for (int i = 0; i < orderList.size(); i++) {
            if (prodInfo.equals(orderList.get(i).getProduct())) {
                sData += "Order ID: " + orderList.get(i).getOrdID() + "\n"
                        + "Customer ID: " + orderList.get(i).getCustID() + "\n"
                        + "Product: " + orderList.get(i).getProduct() + "\n"
                  + "Shipping Method: " + orderList.get(i).getShipping() + "\n";
            }
        }
        txtInfo.setText(sData);
        Pane pane = new Pane(txtInfo);
        Scene scene = new Scene(pane, 600, 600);
        return scene;
    }

}
