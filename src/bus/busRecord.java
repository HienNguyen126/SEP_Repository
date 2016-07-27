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
 * @author bthac
 */
public class busRecord {

    public String ListRecord(String uid, String dtfrom, String dtto) {
        String re = "";
        String url = "http://localhost:8080/Proxy/Record?userid=" + uid + "&datefrom=" + dtfrom + "&dateto=" + dtto;
        re = Connect(url);
        return re;
    }

    public String AddRecord(String tid, String date, String starttime, String endtime, String des) {
        String re = "";
        String url = "http://localhost:8080/Proxy/AddRecord?tagid=" + tid + "&date=" + date + "&start=" + starttime + "&end=" + endtime + "&description=" + des;
        re = Connect(url);
        return re;
    }

    public String EditRecord(String rid, String tid, String date, String st, String end, String des) {
        String re = "";
        String url = "http://localhost:8080/Proxy/EditRecord?recordid=" + rid + "&tagid=" + tid + "&date=" + date + "&start=" + st + "&end=" + end + "&description=" + des;
        re = Connect(url);
        return re;
    }

    public String DeleteRecord(String rid) {
        String re = "";
        String url = "http://localhost:8080/Proxy/DeleteRecord?recordid=" + rid;
        re = Connect(url);
        return re;
    }

    public String ListOne(String rid) {
        String re = "";
        String url = "http://localhost:8080/Proxy/OneRecord?recordid=" + rid;
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
