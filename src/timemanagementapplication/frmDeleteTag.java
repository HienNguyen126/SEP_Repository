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
public class frmDeleteTag extends Form implements CommandListener{
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOK = new Command("OK", Command.OK, 1);
    
    public frmDeleteTag(Display display) {
        super("Delete a tag");
        append("Do you want to delete tag?");
        addCommand(cmdCancel);
        addCommand(cmdOK);
        setCommandListener(this);
        this.display = display;
    }
    Display display;
    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdOK){
            
        }else{
            frmTag tag = new frmTag(display);
            display.setCurrent(tag);
        }
    }
    
}