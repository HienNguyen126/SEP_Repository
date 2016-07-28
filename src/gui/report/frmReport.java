/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.report;

import bus.busReport;
import gui.frmMain;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 *
 * @author Thang Nguyen
 */
public class frmReport extends Form implements CommandListener {

    Display display;
    private Gauge gauge;
    boolean isSafeToExit;
    Command cmdDetail = new Command("Detail", Command.OK, 1);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    public String uid;
    Calendar cal1;
    Calendar cal2;
    String[][] dalist = new String[100][2];

    public frmReport(Display display, String uids) {
        super("Report");
        this.uid = uids;
        //date 
        Date d;
        d = new Date();
        cal1 = Calendar.getInstance();
        cal1.setTime(d);
        String date1 = cal1.get(Calendar.YEAR) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.DAY_OF_MONTH);
        //monday
        cal2 = Calendar.getInstance();
        cal2.setTime(d);
        cal2.set(Calendar.DAY_OF_WEEK, 2);
        String date2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DAY_OF_MONTH);

        String da1 = cal1.get(Calendar.DAY_OF_MONTH) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.YEAR);
        String da2 = cal2.get(Calendar.DAY_OF_MONTH) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.YEAR);;
        append("From " + da2 + " to " + da1);

        bus.busReport rp = new busReport();
        String all = rp.Report(uid, date2, date1);
        String trim = all.trim();
        String[] list = Split(trim, "\n");
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            String[] part = Split(list[i], "|");
            dalist[i][0] = part[0];
            dalist[i][1] = part[1];
            int x = Integer.parseInt(dalist[i][1]);
            sum = sum + x;
        }
        for (int i = 0; i < list.length; i++) {
            String name = Encode(dalist[i][0]);
            int x = Integer.parseInt(dalist[i][1]);
            gauge = new Gauge(name, false, sum, x);
            append(gauge);
        }
        addCommand(cmdDetail);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.display = display;
        isSafeToExit = true;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdDetail) {
            frmDetail dt = new frmDetail(display, uid, cal2, cal1);
            display.setCurrent(dt);
        } else if (c == cmdCancel) {
            frmMain ma = new frmMain(display, uid);
            display.setCurrent(ma);
        }
    }

    public static String[] Split(String splitStr, String delimiter) {
        StringBuffer token = new StringBuffer();
        Vector tokens = new Vector();
        // split
        char[] chars = splitStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (delimiter.indexOf(chars[i]) != -1) {
                // we bumbed into a delimiter
                if (token.length() > 0) {
                    tokens.addElement(token.toString());
                    token.setLength(0);
                }
            } else {
                token.append(chars[i]);
            }
        }
        // don't forget the "tail"...
        if (token.length() > 0) {
            tokens.addElement(token.toString());
        }
        // convert the vector into an array
        String[] splitArray = new String[tokens.size()];
        for (int i = 0; i < splitArray.length; i++) {
            splitArray[i] = (String) tokens.elementAt(i);
        }
        return splitArray;
    }

    public static String replace(String _text, String _searchStr, String _replacementStr) {
        // String buffer to store str
        StringBuffer sb = new StringBuffer();

        // Search for search
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int searchStringLength = _searchStr.length();

        // Iterate to add string
        while (searchStringPos != -1) {
            sb.append(_text.substring(startPos, searchStringPos)).append(_replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
        }

        // Create string
        sb.append(_text.substring(startPos, _text.length()));

        return sb.toString();
    }

    public static String Decode(String decode) {
        String rename1 = replace(decode, "|", "mzo");
        String rename2 = replace(rename1, "+", "ozm");
        String rename3 = replace(rename2, " ", "+");
        return rename3;
    }

    public static String Encode(String encode) {
        String rename5 = replace(encode, "mzo", "|");
        String rename6 = replace(rename5, "ozm", "+");
        return rename6;
    }
}
