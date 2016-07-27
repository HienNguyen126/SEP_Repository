/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import bus.busRecord;
import javax.microedition.lcdui.*;

/**
 *
 * @author THACH
 */
public class frmDeleteRecord extends Form implements CommandListener {

    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    public String uid;
    String rid;
    Display display;

    public frmDeleteRecord(Display display, String uids, String rid) {
        super("Delete record");
        this.uid = uids;
        this.rid = rid;
        append("Do you want to delete this record? ");//+record name  
        addCommand(cmdOk);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.display = display;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdOk) {
            busRecord br = new busRecord();
            String re = br.DeleteRecord(rid);
            if (re.startsWith("Ok")) {
                frmRecord rec = new frmRecord(display, uid);
                display.setCurrent(rec);
            } else {
                Alert addTag = new Alert("Delete Record Fail!", re, null, AlertType.WARNING);
                display.setCurrent(addTag, this);
            }
        } else if (c == cmdCancel) {
            frmRecord record = new frmRecord(display, uid);
            display.setCurrent(record);
        }
    }
}
