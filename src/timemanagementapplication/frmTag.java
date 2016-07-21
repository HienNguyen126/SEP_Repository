/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import javax.microedition.lcdui.*;

/**
 *
 * @author Thuong
 */
public class frmTag extends Form implements CommandListener {
    
    Command cmdBack = new Command("Back", Command.BACK, 1);
    Command cmdAddTag = new Command("Add", Command.OK, 1);
    Command cmdEditTag = new Command("Edit", Command.OK, 1);
    Command cmdDeleteTag = new Command("Delete", Command.OK, 1);
    public String uid;
    
    public frmTag(Display display, String uids) {
        super("Tag");
        this.uid = uids;
        addCommand(cmdBack);
        addCommand(cmdAddTag);
        addCommand(cmdEditTag);
        addCommand(cmdDeleteTag);
        setCommandListener(this);
        this.display = display;
    }
    Display display;
    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(c == cmdAddTag){
            frmAddTag at = new frmAddTag(display , uid);
            display.setCurrent(at);
        }else if(c == cmdEditTag){
            frmEditTag et = new frmEditTag(display , uid);
            display.setCurrent(et);
        }else if (c == cmdDeleteTag){
            frmDeleteTag dt = new frmDeleteTag(display , uid);
            display.setCurrent(dt);
        }else{
            
            frmMain wc = new frmMain(display, uid);
            display.setCurrent(wc);
        }
    }
    
}
