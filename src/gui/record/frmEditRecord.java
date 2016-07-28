/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.record;

import bus.busRecord;
import bus.busTag;
import gui.tag.frmTag;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.lcdui.*;

/**
 *
 * @author THACH
 */
public class frmEditRecord extends Form implements CommandListener {

    ChoiceGroup radioButtons = new ChoiceGroup(null, Choice.POPUP);
    TextField txtDescription = new TextField("Description", "", 50, TextField.ANY);
    DateField dtfDate = new DateField("Date", DateField.DATE);
    DateField dtfStart = new DateField("Start", DateField.TIME);
    DateField dtfEnd = new DateField("End", DateField.TIME);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    public String uid;
    String rid;
    String[][] dalist = new String[100][3];
    bus.busRecord br = new busRecord();
    bus.busTag bt = new busTag();
    Display display;

    public frmEditRecord(Display display, String uids, String rid, DateField dtf, String tagid) {
        super("Edit record");
        this.uid = uids;
        this.rid = rid;
        dtfDate.setDate(dtf.getDate());
        String tmp = br.ListOne(rid);
        String re = tmp.trim();
        String[] aRecord = frmRecord.Split(re, "|");
        bus.busTag tag = new busTag();
        String all = tag.ListTag(uid);
        String trim = all.trim();
        String[] list = frmRecord.Split(trim, "\n");
        for (int i = 0; i < list.length; i++) {
            String[] part = frmRecord.Split(list[i], "|");
            dalist[i][0] = part[0];
            dalist[i][1] = part[1];
            dalist[i][2] = part[2];
            String name = frmRecord.Encode(dalist[i][1]);
            radioButtons.append("" + name, null);
            if (dalist[i][1].equals(tagid)) {
                radioButtons.setSelectedIndex(i, true);
            }
        }

        append(radioButtons);
        append(dtfDate);
        dtfStart.setDate(ConvertTime(aRecord[2]));
        append(dtfStart);
        dtfEnd.setDate(ConvertTime(aRecord[3]));
        append(dtfEnd);
        txtDescription.setString(aRecord[4]);
        append(txtDescription);
        addCommand(cmdOk);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.display = display;
    }

    public Date ConvertTime(String time) {
        String[] tmp = frmRecord.Split(time, ":");
        int hr = Integer.parseInt(tmp[0]);
        int min = Integer.parseInt(tmp[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.MILLISECOND, 0);
        // Get Calendar for the epoch date and time 
        Calendar baseCal = Calendar.getInstance();
        Date baseDate = new Date(0);
        baseCal.setTime(baseDate);
        // Get Calendar for now and use the epoch 
        // values to reset the date to the epoch. 
        Calendar cal1 = Calendar.getInstance();
        Date now = cal.getTime();
        cal1.setTime(now);
        // Set the year, month and day in month from the epoch 
        cal1.set(Calendar.YEAR, baseCal.get(Calendar.YEAR));
        cal1.set(Calendar.MONTH, baseCal.get(Calendar.MONTH));
        cal1.set(Calendar.DATE, baseCal.get(Calendar.DATE));

        Date date = cal1.getTime();
        return date;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdOk) {
            String sDate = frmRecord.ParseDate(dtfDate);
            sDate.valueOf(frmRecord.ParseDate(dtfDate));
            //
            String sStart = frmAddRecord.ParseTime(dtfStart);
            sStart.valueOf(frmAddRecord.ParseTime(dtfStart));
            String sEnd = frmAddRecord.ParseTime(dtfEnd);
            sEnd.valueOf(frmAddRecord.ParseTime(dtfEnd));
            //
            String[] aStart = frmRecord.Split(sStart, ":");
            String[] aEnd = frmRecord.Split(sEnd, ":");
            int hourst = Integer.parseInt(aStart[0]);
            int hourend = Integer.parseInt(aEnd[0]);
            int minst = Integer.parseInt(aStart[1]);
            int minend = Integer.parseInt(aEnd[1]);
            //
            int summinst = hourst * 60 + minst;
            int summinend = hourend * 60 + minend;
            //get tag id
            int index = radioButtons.getSelectedIndex();
            String tagid = dalist[index][0];
            //
            String redecs = frmTag.Decode(txtDescription.getString());
            //
            if (summinst < summinend) {
                if (redecs.equals("")) {
                    redecs = "...";
                }
                String sAdd = br.EditRecord(rid, tagid, sDate, sStart, sEnd, redecs);
                if (sAdd.startsWith("Ok")) {
                    frmRecord re = new frmRecord(display, uid);
                    display.setCurrent(re);
                } else {
                    Alert editRecord = new Alert("Edit Record Fail!", sAdd, null, AlertType.WARNING);
                    display.setCurrent(editRecord, this);
                }
            } else {
                Alert checkTime = new Alert("Time check", "Start time not sufficient", null, AlertType.WARNING);
                display.setCurrent(checkTime, this);
            }
        } else if (c == cmdCancel) {
            frmRecord re = new frmRecord(display, uid);
            display.setCurrent(re);
        }
    }
}
