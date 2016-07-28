/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tag;

import javax.microedition.lcdui.*;
import bus.busTag;

/**
 *
 * @author Thuong
 */
public class frmDeleteTag extends Form implements CommandListener {

    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOK = new Command("OK", Command.OK, 1);
    public String uid;
    String tagid;

    public frmDeleteTag(Display display, String uids, String tagid) {
        super("Delete a tag");
        this.uid = uids;
        this.tagid = tagid;
        append("Do you want to delete tag?");
        addCommand(cmdCancel);
        addCommand(cmdOK);
        setCommandListener(this);
        this.display = display;
    }
    Display display;

    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdOK) {
            busTag bustag = new busTag();
            String re = bustag.DeleteTag(tagid);
            if (re.startsWith("Ok")) {
                frmTag tag = new frmTag(display, uid);
                display.setCurrent(tag);
            } else {
                Alert addTag = new Alert("Delete Tag Fail!", re, null, AlertType.WARNING);
                display.setCurrent(addTag, this);
            }
        } else {
            frmTag tag = new frmTag(display, uid);
            display.setCurrent(tag);
        }
    }

}
