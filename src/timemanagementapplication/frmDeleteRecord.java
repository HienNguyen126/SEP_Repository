/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import javax.microedition.lcdui.*;
/**
 *
 * @author THACH
 */
public class frmDeleteRecord extends Form implements CommandListener{
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    public String uid;
    Display display;
    public frmDeleteRecord(Display display , String uids)
    {
        super("Delete record");
        this.uid = uids;
        append("Do you want to delete this record? ");//+record name
        addCommand(cmdOk);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.display= display;
    }
    public void commandAction(Command c, Displayable d)
    {
        if(c==cmdOk)
        {
            
        }
        else if(c== cmdCancel)
        {
            frmRecord record = new frmRecord(display , uid);
            display.setCurrent(record);
        }
    }
}
