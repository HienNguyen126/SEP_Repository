/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import bus.busReport;
import java.util.Date;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.Command;
import java.util.Calendar;

/**
 *
 * @author Thang Nguyen
 */
public class frmDetail extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 1);
    public String uid;
    String[][] dalist = new String[200][5];
    Display display;

    public frmDetail(Display display, String uids, Calendar cal2, Calendar cal1) {
        super("Detail");
        this.display = display;
        this.uid = uids;

        String date1 = cal1.get(Calendar.YEAR) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.DAY_OF_MONTH);
        String date2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DAY_OF_MONTH);

        String da1 = cal1.get(Calendar.DAY_OF_MONTH) + "-" + (cal1.get(Calendar.MONTH) + 1) + "-" + cal1.get(Calendar.YEAR);
        String da2 = cal2.get(Calendar.DAY_OF_MONTH) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.YEAR);
        append("     REPORT\nFrom " + da2 + " to " + da1);

        bus.busReport rp = new busReport();
        String all = rp.DetailReport(uid, date2, date1);
        String trim = all.trim();
        String[] list = frmReport.Split(trim, "\n");
        for (int i = 0; i < list.length; i++) {
            String[] part = frmReport.Split(list[i], "|");
            dalist[i][0] = part[0];
            dalist[i][1] = part[1];
            dalist[i][2] = part[2];
            dalist[i][3] = part[3];
            dalist[i][4] = part[4];
            //append("   " + dalist[i][1] + "\n  From " + dalist[i][3] + " to " + dalist[i][4]);
        }
        long second2 = cal2.getTime().getTime();
        long second1 = cal1.getTime().getTime();

        while (second2 <= second1) {

            String year = "" + cal2.get(Calendar.YEAR);
            String month = "" + (cal2.get(Calendar.MONTH) + 1);
            String day = "" + cal2.get(Calendar.DAY_OF_MONTH);
            if (Integer.parseInt(day) < 10) {
                day = "0" + day;
            }
            if (Integer.parseInt(month) < 10) {
                month = "0" + month;
            }
            String date = year + "-" + month + "-" + day;
            boolean chk = false;
            for (int i = 0; i < list.length; i++) {
                if (dalist[i][2].equals(date)) {
                    append("  " + dalist[i][1] + "\n   From " + dalist[i][3] + " to " + dalist[i][4]);
                    chk = true;
                }
            }
            if (chk) {
                date = "" + cal2.get(Calendar.DAY_OF_MONTH) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.YEAR);
                append(date+"\n");
                append("+++\n");
            }
            cal2.setTime(new Date(cal2.getTime().getTime() + 86400000));
            second2 = cal2.getTime().getTime();
        }
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            frmReport rp = new frmReport(display, uid);
            display.setCurrent(rp);
        }

    }

}
