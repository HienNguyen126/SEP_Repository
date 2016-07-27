/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Hien
 */
public class busReport {

    public String Report(String uid, String dtfrom, String dtto) {
        String re = "";
        String url = "http://localhost:8080/Proxy/Report?userid=" + uid + "&datefrom=" + dtfrom + "&dateto=" + dtto;
        re = Connect(url);
        return re;
    }

    public String DetailReport(String uid, String dtfrom, String dtto) {
        String re = "";
        String url = "http://localhost:8080/Proxy/DetailReport?userid=" + uid + "&datefrom=" + dtfrom + "&dateto=" + dtto;
        re = Connect(url);
        return re;
    }

    public String Connect(String URL) {
        String re = "";
        long len = 0;
        int ch = 0;
        String s = "";
        try {
            // open connection
            HttpConnection conn = (HttpConnection) Connector.open(URL);
            StringBuffer sb = new StringBuffer();
            // get source
            InputStream in = conn.openInputStream();
            len = conn.getLength();
            if (len != -1) {
                for (int i = 0; i < len; i++) {
                    if ((ch = in.read()) != -1) {
                        sb.append((char) ch);
                    }
                }
            } else {
                while ((ch = in.read()) != -1) {
                    len = in.available();
                    sb.append((char) ch);
                }
            }
            s = sb.toString();
            re = s;
        } catch (IOException ex) {
            re = ex.toString();
        }
        return re;
    }
}
