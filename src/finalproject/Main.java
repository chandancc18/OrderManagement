/**
 * Chandan Chhaparia
 * 991549130
 * Final Project
 */


package finalproject;

import content.Order;
import content.OrderFile;
import diff.StageOne;
import diff.StageThree;
import diff.StageTwo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Label tagline = new Label("Order Information");
    private Label lblOrder = new Label("Order ID");
    private TextField txtOrder = new TextField();
    private Label lblCust = new Label("Customer ID");
    private TextField txtCust = new TextField();
    private Label lblProd = new Label("Product");
    private TextField txtProd = new TextField();
    private Label lblShipping = new Label("Shipping Method");
    private TextField txtShipping = new TextField();

    private Button btnFirst = new Button("First");
    private Button btnNext = new Button("Next");
    private Button btnPrev = new Button("Previous");
    private Button btnLast = new Button("Last");
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");
    private Button btnAdd = new Button("Add");
    private int sub = 0;

    private HBox box = new HBox(10, btnFirst, btnNext, btnPrev, btnLast);

    private Alert dlgConfirm = new Alert(AlertType.CONFIRMATION);
    private ArrayList<Order> orderList = new ArrayList<>();

    private TextField txtSearchCust = new TextField();
    private Button btnSearchCust = new Button("Search Customer Info");

    private TextField txtSearchProd = new TextField();
    private Button btnSearchProd = new Button("Search Product Info");

    private Button btnAllInfo = new Button("View All Order");

    @Override
    public void start(Stage primaryStage) {
        try {
            orderList = OrderFile.getRecords();
            OrderFile.setRecords(orderList);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Iterator<Order> iterator = orderList.iterator();
        Order one = iterator.next();

        txtOrder.setText(one.getOrdID());
        txtCust.setText(one.getCustID());
        txtProd.setText(one.getProduct());
        txtShipping.setText(one.getShipping());

        btnFirst.setOnMouseClicked(e -> {
            sub = 0;
            txtOrder.setText(orderList.get(0).getOrdID());
            txtCust.setText(orderList.get(0).getCustID());
            txtProd.setText(orderList.get(0).getProduct());
            txtShipping.setText(orderList.get(0).getShipping());
        });

        btnNext.setOnMouseClicked(e -> {
            if (sub != orderList.size() - 1) {
                sub++;
                Order two = orderList.get(sub);
                txtOrder.setText(two.getOrdID());
                txtCust.setText(two.getCustID());
                txtProd.setText(two.getProduct());
                txtShipping.setText(two.getShipping());

            }

        });

        btnPrev.setOnMouseClicked(e -> {
            if (sub != 0) {
                sub--;
                Order two = orderList.get(sub);
                txtOrder.setText(two.getOrdID());
                txtCust.setText(two.getCustID());
                txtProd.setText(two.getProduct());
                txtShipping.setText(two.getShipping());

            }
        });

        btnLast.setOnMouseClicked(e -> {
            sub = orderList.size() - 1;
            txtOrder.setText(orderList.get(orderList.size() - 1).getOrdID());
            txtCust.setText(orderList.get(orderList.size() - 1).getCustID());
            txtProd.setText(orderList.get(orderList.size() - 1).getProduct());
            txtShipping.setText(orderList.get(orderList.size() - 1)
                    .getShipping());
        });

        btnUpdate.setOnMouseClicked(e -> {
            try {
                if (txtOrder.getText().equals("")
                        || txtCust.getText().equals("")
                        || txtProd.getText().equals("")
                        || txtShipping.getText().equals(" ")) {
                    throw new Exception("Enter The Empty Field");
                }

                Optional<ButtonType> result = dlgConfirm.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (sub < orderList.size()) {
                        Order three = orderList.get(sub);
                        if (!(txtOrder.getText().equals(three.getOrdID()))
                                || !(txtCust.getText().equals(three.getCustID()))) {
                            Alert dlgMessage = new Alert(AlertType.ERROR);
                            dlgMessage.setContentText("OrderId and CustomerId "
                                    + "cant be Changed");
                            dlgMessage.show();
                        }

                        three.setProduct(txtProd.getText());
                        three.setShipping(txtShipping.getText());
                        txtOrder.setText(three.getOrdID());
                        txtCust.setText(three.getCustID());
                    } else {
                        Order four = new Order(txtOrder.getText());
                        four.setCustID(txtCust.getText());
                        four.setProduct(txtProd.getText());
                        four.setShipping(txtShipping.getText());
                        orderList.add(four);
                    }
                } else {
                    Order three = orderList.get(sub);
                    txtOrder.setText(three.getOrdID());
                    txtCust.setText(three.getCustID());
                    txtProd.setText(three.getProduct());
                    txtShipping.setText(three.getShipping());
                }

            } catch (Exception k) {
                Alert dlgMessage = new Alert(AlertType.ERROR);
                dlgMessage.setContentText("There are Empty Field \n"
                        + "Please enter Information");
                dlgMessage.show();
            }
            try {
                OrderFile.setRecords(orderList);
            } catch (IOException s) {
                System.out.println(s.getMessage());
            }

        });

        btnAdd.setOnMouseClicked(e -> {
            sub = orderList.size();
            txtOrder.clear();
            txtCust.clear();
            txtProd.clear();
            txtShipping.clear();

            txtOrder.requestFocus();
        });

        btnDelete.setOnMouseClicked(e -> {
            Optional<ButtonType> result = dlgConfirm.showAndWait();
            if (result.get() == ButtonType.OK) {

                orderList.remove(sub);

                try {
                    OrderFile.setRecords(orderList);
                } catch (IOException s) {
                    System.out.println(s.getMessage());
                }

                if (sub == orderList.size()) {
                    sub = sub - 1;
                } else {
                    sub = sub;
                }
                Order five = orderList.get(sub);
                txtOrder.setText(five.getOrdID());
                txtCust.setText(five.getCustID());
                txtProd.setText(five.getProduct());
                txtShipping.setText(five.getShipping());

            } else {
                Alert dlgMessage = new Alert(AlertType.INFORMATION);
                String message = "The record is not deleted";
                dlgMessage.setContentText(message);
                dlgMessage.show();
            }

        });

        btnSearchCust.setOnMouseClicked(e -> {
            String custInfo = txtSearchCust.getText();
            StageOne stOne = new StageOne(orderList, custInfo);
            stOne.show();
        });

        btnSearchProd.setOnMouseClicked(e -> {
            String prodInfo = txtSearchProd.getText();
            StageTwo stTwo = new StageTwo(orderList, prodInfo);
            stTwo.show();
        });

        btnAllInfo.setOnMouseClicked(e -> {
            StageThree stThree = new StageThree(orderList);
            stThree.show();
        });

        Scene scene = new Scene(getGrid(), 600, 600);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private GridPane getGrid() {
        GridPane pane = new GridPane();
        pane.add(tagline, 0, 0);
        pane.add(lblOrder, 0, 1);
        pane.add(txtOrder, 1, 1);
        pane.add(lblCust, 0, 2);
        pane.add(txtCust, 1, 2);
        pane.add(lblProd, 0, 3);
        pane.add(txtProd, 1, 3);
        pane.add(lblShipping, 0, 4);
        pane.add(txtShipping, 1, 4);
        pane.add(box, 0, 5);
        pane.add(btnUpdate, 2, 2);
        pane.add(btnAdd, 2, 3);
        pane.add(btnDelete, 2, 4);

        pane.add(txtSearchCust, 0, 8);
        pane.add(btnSearchCust, 1, 8);
        pane.add(txtSearchProd, 0, 9);
        pane.add(btnSearchProd, 1, 9);
        pane.add(btnAllInfo, 1, 10);
        pane.setVgap(10);
        pane.setHgap(5);

        return pane;

    }

}
