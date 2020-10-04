/**
 * Chandan Chhaparia
 * 991549130
 * Final Project
 */
package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OrderFile {

    public static ArrayList<Order> getRecords() throws IOException {
        FileReader fr = new FileReader("Order.dat");
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Order> orderList = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens()) {
                String id = st.nextToken();
                Order one = new Order(id);
                one.setCustID(st.nextToken());
                one.setProduct(st.nextToken());
                one.setShipping(st.nextToken());
                orderList.add(one);
            }

            line = br.readLine();
        }
        br.close();
        fr.close();
        return orderList;
    }

    public static void setRecords(ArrayList<Order> orderList)
            throws IOException {

        FileWriter fw = new FileWriter("Order.dat");
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < orderList.size(); i++) {
            Order one = orderList.get(i);
            String record = one.getOrdID() + "," + one.getCustID() + ","
                    + one.getProduct() + "," + one.getShipping();
            bw.write(record);
            bw.flush();
            bw.newLine();
        }

        bw.close();
        fw.close();
    }
}
