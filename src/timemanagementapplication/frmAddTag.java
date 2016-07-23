/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import bus.busTag;
import javax.microedition.lcdui.*;

/**
 *
 * @author Thuong
 */
public class frmAddTag extends Form implements CommandListener{

    TextField txtNameTag = new TextField("Name: ", "", 50, TextField.ANY);
    TextField txtDescriptionTag = new TextField("Description: ", "", 200, TextField.ANY);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOK = new Command("OK", Command.OK, 1);
    public String uid;
    
    public frmAddTag(Display display , String uids) {
        super("Add a new tag");
        this.uid = uids;
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
        if(c == cmdOK){
            String name = txtNameTag.getString();
            name.valueOf(txtNameTag.getString());
            String rename = frmTag.Decode(name);
            String decs =txtDescriptionTag.getString();
            decs.valueOf(txtDescriptionTag.getString());
            String redecs = frmTag.Decode(decs);
            busTag bustag = new busTag();
            String re = bustag.AddTag(uid, rename, redecs);
            if(re.startsWith("Ok")){
                frmTag tag = new frmTag(display, uid);
                display.setCurrent(tag);
            }else{
                Alert addTag = new Alert ("Add Tag Fail!", re , null , AlertType.WARNING);
                display.setCurrent(addTag, this);
            }
        }else{
            frmTag tag = new frmTag(display , uid);
            display.setCurrent(tag);
        }
    }
    
}
