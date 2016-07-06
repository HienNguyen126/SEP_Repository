/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import javax.microedition.lcdui.*;

/**
 *
 * @author Khoi Nguyen
 */
public class frmMain extends Form implements CommandListener, ItemCommandListener{
    private static Command cmdExit = new Command("Exit", Command.EXIT, 1);
    private static Command cmdRecord = new Command ("", Command.ITEM, 1);
    private static Command cmdTag = new Command ("", Command.ITEM, 1);
    private static Command cmdReport = new Command ("", Command.ITEM, 1);
    private Display display;
    private StringItem item;

    public frmMain(Display display) {
        super("Main");
        item = new StringItem ("", "Record", Item.BUTTON);
        item.setDefaultCommand (cmdRecord);
        item.setItemCommandListener (this);
        append (item);
        item = new StringItem ("\n", "Tag", Item.BUTTON);
        item.setDefaultCommand (cmdTag);
        item.setItemCommandListener (this);
        append (item);
        item = new StringItem ("\n", "Report", Item.BUTTON);
        item.setDefaultCommand (cmdReport);
        item.setItemCommandListener (this);
        append (item);
        addCommand(cmdExit);
        setCommandListener(this);
        this.display = display;
    }

    public void commandAction(Command c, Item item) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(c == cmdTag){
            frmTag Tag = new frmTag(display);
            display.setCurrent(Tag);
        }else if (c == cmdRecord) {
            frmRecord Record = new frmRecord(display);
            display.setCurrent(Record);
        }else{
            frmReport Report = new frmReport(display);
            display.setCurrent(Report);
        }
    }
    
    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(c == cmdExit){
            frmLogin Login = new frmLogin(display);
            display.setCurrent(Login);
        }
    }
    
}
