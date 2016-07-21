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

public class frmRecord extends Form implements CommandListener, ItemStateListener{
    DateField dtfRecordDate = new DateField("Today", DateField.DATE);
    Date d;
    Command cmdAddRecord = new Command("Add new", Command.OK, 1);
    Command cmdEditRecord = new Command("Edit", Command.OK, 1);
    Command cmdDeleteRecord = new Command("Delete", Command.OK, 1);
    Command cmdBack = new Command("Back", Command.EXIT, 1);
    public String uid;
    Display display;
    Boolean check;
    bus.busRecord br = new busRecord();
    
    public frmRecord(Display display, String uids)
    {
        super("Record");
        this.uid = uids;
        d = new Date();
        dtfRecordDate.setDate(d);
        append(dtfRecordDate);
        
        addCommand(cmdAddRecord);
        addCommand(cmdEditRecord);
        addCommand(cmdDeleteRecord);
        addCommand(cmdBack);
        setCommandListener(this);
        setItemStateListener(this);
        this.display = display;
    }
//    public static String getCurrentTimeStamp() {
//    DateFormat sdfDate = new DateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
//    Date now = new Date();
//    String strDate = sdfDate.format(now);
//    return strDate;
//}
    public String ParseDate(DateField d)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d.getDate());
        String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
        return date;
    }
        public void itemStateChanged(Item item)              
  {
      String [] tmp = createList(dtfRecordDate);
    if (item == dtfRecordDate)
    {
        for(int i=0; i<tmp.length; i++)
        {
            append(tmp[i+1].toString()+"\t\t" + "      ");
            String [] time1 = Split(tmp[i+2].toString(), ":");
            String [] time2 = Split(tmp[i+3].toString(), ":");
            //hour
            int iTime1 = Integer.parseInt(time1[0].toString());
            int itime2 = Integer.parseInt(time2[0].toString());
            //min
            int iTime3 = Integer.parseInt(time1[1].toString());
            int itime4 = Integer.parseInt(time2[1].toString());
            //
            int sum1= iTime1+ itime2;
            int sum2= iTime3 + itime4;
            //
            
            //
            append(iTime1+":"+ iTime3);
            append(itime2+":"+ itime4);
            i=i+3;
        
        }
        for(int j =0;j<tmp.length;j++)
        {
             
        }
    }
  }
        public String[] createList(DateField df)
        {
            String s= ParseDate(df);
            s.valueOf(ParseDate(df));
            uid.valueOf("1");
            s=br.ListRecord(uid, s, s);
            String[] tmp = Split(new String(s), "|");
            return tmp;
        }
    public static String[] Split(String splitStr, String delimiter) {
     StringBuffer token = new StringBuffer();
     Vector tokens = new Vector();
     // split
     char[] chars = splitStr.toCharArray();
     for (int i=0; i < chars.length; i++) {
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
     for (int i=0; i < splitArray.length; i++) {
         splitArray[i] = (String)tokens.elementAt(i);
     }
     return splitArray;
 }
    public void commandAction(Command c, Displayable d)
    {
        if(c== cmdAddRecord)
        {
            frmAddRecord ar = new frmAddRecord(display , uid);
            display.setCurrent(ar);
        }
        else if(c==cmdEditRecord)
        {
            frmEditRecord er = new frmEditRecord(display , uid);
            display.setCurrent(er);
        }
        else if (c == cmdDeleteRecord){
            frmDeleteRecord dr = new frmDeleteRecord(display , uid);
            display.setCurrent(dr);
        }else{
            frmMain wc = new frmMain(display, uid);
            display.setCurrent(wc);
        }
    }
}

