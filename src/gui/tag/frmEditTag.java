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
public class frmEditTag extends Form implements CommandListener {

    TextField txtNameTag = new TextField("Name: ", "", 20, TextField.ANY);
    TextField txtDescriptionTag = new TextField("Description: ", "", 50, TextField.ANY);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOK = new Command("OK", Command.OK, 1);
    public String uid;
    public String tagid;

    public frmEditTag(Display display, String uids, String tagid) {
        super("Edit a tag");
        this.uid = uids;
        this.tagid = tagid;
        busTag bustag = new busTag();
        String re = bustag.ListOne(tagid);
        String re1 = re.trim();
        String[] part = frmTag.Split(re1, "|");
        String name = frmTag.Encode(part[1]);
        String desc = frmTag.Encode(part[2]);
        txtNameTag.setString(name);
        txtDescriptionTag.setString(desc);
        append(txtNameTag);
        append(txtDescriptionTag);
        addCommand(cmdCancel);
        addCommand(cmdOK);
        setCommandListener(this);
        this.display = display;
    }
    Display display;

    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdOK) {
            String name = txtNameTag.getString();
            name.valueOf(txtNameTag.getString());
            String rename = frmTag.Decode(name);
            if (rename.equals("")) {
                Alert addTag = new Alert("Add Tag Fail!", "Please input name!", null, AlertType.WARNING);
                display.setCurrent(addTag, this);
            } else {
                String decs = txtDescriptionTag.getString();
                decs.valueOf(txtDescriptionTag.getString());
                String redecs = frmTag.Decode(decs);
                if (redecs.equals("")) {
                    redecs = "...";
                }
                busTag bustag = new busTag();
                String re = bustag.EditTag(tagid, rename, redecs);
                if (re.startsWith("Ok")) {
                    frmTag tag = new frmTag(display, uid);
                    display.setCurrent(tag);
                } else {
                    Alert addTag = new Alert("Edit Tag Fail!", re, null, AlertType.WARNING);
                    display.setCurrent(addTag, this);
                }
            }
        } else {
            frmTag tag = new frmTag(display, uid);
            display.setCurrent(tag);
        }
    }

}
