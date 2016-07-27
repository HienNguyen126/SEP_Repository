/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import bus.busTag;
import bus.busRecord;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.lcdui.*;

/**
 *
 * @author THACH
 */
public class frmAddRecord extends Form implements CommandListener {

    ChoiceGroup radioButtons = new ChoiceGroup("Tag", ChoiceGroup.POPUP);
    TextField txtDescription = new TextField("Description", "", 200, TextField.ANY);
    DateField dtfDate = new DateField("Date", DateField.DATE);
    DateField dtfStart = new DateField("Start", DateField.TIME);
    DateField dtfEnd = new DateField("End", DateField.TIME);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    public String uid;
    String[][] dalist = new String[100][3];
    Date d = new Date();
    bus.busRecord br = new busRecord();
    Display display;

    public frmAddRecord(Display display, String uids) {
        super("Add new record");
        this.uid = uids;
        dtfDate.setDate(d);
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
        }
        append(radioButtons);
        append(dtfDate);
        dtfStart.setDate(new Date(0));
        append(dtfStart);
        dtfEnd.setDate(new Date(0));
        append(dtfEnd);
        append(txtDescription);
        addCommand(cmdOk);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.display = display;
    }

    public static String ParseTime(DateField d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d.getDate());
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "0";
        return time;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdOk) {
            String sDate = frmRecord.ParseDate(dtfDate);
            sDate.valueOf(frmRecord.ParseDate(dtfDate));
            //
            String sStart = ParseTime(dtfStart);
            sStart.valueOf(ParseTime(dtfStart));
            String sEnd = ParseTime(dtfEnd);
            sEnd.valueOf(ParseTime(dtfEnd));
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
                String sAdd = br.AddRecord(tagid, sDate, sStart, sEnd, redecs);
                if (sAdd.startsWith("Ok")) {
                    frmRecord re = new frmRecord(display, uid);
                    display.setCurrent(re);
                } else {
                    Alert addRecord = new Alert("Add Record Fail!", sAdd, null, AlertType.WARNING);
                    display.setCurrent(addRecord, this);
                }
            } else {
                Alert checkTime = new Alert("Time check", "start time not sufficient", null, AlertType.WARNING);
                display.setCurrent(checkTime,this);}

        } else if (c == cmdCancel) {
            frmRecord record = new frmRecord(display, uid);
            display.setCurrent(record);
        }
    }
}
