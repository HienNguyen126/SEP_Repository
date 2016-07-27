/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import bus.busRecord;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 *
 * @author THACH
 */
public class frmRecord extends Form implements CommandListener, ItemStateListener {

    DateField dtfRecordDate = new DateField("Date", DateField.DATE);
    Date d;
    Command cmdAddRecord = new Command("Add new", Command.OK, 1);
    Command cmdEditRecord = new Command("Edit", Command.OK, 1);
    Command cmdDeleteRecord = new Command("Delete", Command.OK, 1);
    Command cmdBack = new Command("Back", Command.EXIT, 1);
    ChoiceGroup radioButtons = new ChoiceGroup(null, Choice.EXCLUSIVE);
    public String uid;
    Display display;
    Boolean check;
    bus.busRecord br = new busRecord();

    public frmRecord(Display display, String uids) {
        super("Record");
        this.uid = uids;
        d = new Date();
        dtfRecordDate.setDate(d);
        append(dtfRecordDate);
        loadItem(dtfRecordDate);
        append(radioButtons);
        addCommand(cmdAddRecord);
        addCommand(cmdEditRecord);
        addCommand(cmdDeleteRecord);
        addCommand(cmdBack);
        setCommandListener(this);
        setItemStateListener(this);
        this.display = display;
    }

    String[][] dalist = new String[100][5];

    public void loadItem(DateField df) {
        radioButtons.deleteAll();
        String s = ParseDate(df);
        s.valueOf(ParseDate(df));
        String re = br.ListRecord(uid, s, s);
        String trim = re.trim();
        String[] list = Split(trim, "\n");
        for (int i = 0; i < list.length; i++) {
            String[] part = Split(list[i], "|");
            dalist[i][0] = part[0];
            dalist[i][1] = part[1];
            dalist[i][2] = part[2];
            dalist[i][3] = part[3];
            dalist[i][4] = part[4];
            String name = Encode(dalist[i][1]);
            String desc = Encode(dalist[i][4]);
            String[] time1 = Split(dalist[i][2], ":");
            String[] time2 = Split(dalist[i][3], ":");
            //hour
            int iTime1 = Integer.parseInt(time1[0]);
            int iTime2 = Integer.parseInt(time2[0]);
            //min
            int iTime3 = Integer.parseInt(time1[1]);
            int iTime4 = Integer.parseInt(time2[1]);
            //
            int iTmp1 = (iTime1 * 60) + iTime3;
            int iTmp2 = (iTime2 * 60) + iTime4;
            //
            int min = iTmp2 - iTmp1;
            //
            int sumhour = min / 60;
            int summin = min % 60;
            String hour = "";
            String minute = "";
            //
            if (sumhour < 10) {
                hour = "0" + sumhour;
            } else {
                hour = "" + sumhour;
            }
            if (summin < 10) {
                minute = "0" + summin;
            } else {
                minute = "" + summin;
            }
            //append(hour + ":"+minute);
            String time = hour + ":" + minute;
            radioButtons.append("" + name + "\t\n" + time + "\n" + desc, null);
        }
    }

    public static String ParseDate(DateField d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d.getDate());
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public void itemStateChanged(Item item) {
        if (item == dtfRecordDate) {
            loadItem(dtfRecordDate);
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

    public void commandAction(Command c, Displayable d) {
        if (c == cmdAddRecord) {
            frmAddRecord ar = new frmAddRecord(display, uid);
            display.setCurrent(ar);
        } else if (c == cmdEditRecord) {
            int index = radioButtons.getSelectedIndex();
            String rid = dalist[index][0];
            String tagid = dalist[index][1];
            frmEditRecord er = new frmEditRecord(display, uid, rid, dtfRecordDate, tagid);
            display.setCurrent(er);
//            frmEditRecord er = new frmEditRecord(display, uid);
//            display.setCurrent(er);
        } else if (c == cmdDeleteRecord) {
            int index = radioButtons.getSelectedIndex();
            String rid = dalist[index][0];
            frmDeleteRecord dr = new frmDeleteRecord(display, uid, rid);
            display.setCurrent(dr);
        } else {
            frmMain wc = new frmMain(display, uid);
            display.setCurrent(wc);
        }
    }
}
