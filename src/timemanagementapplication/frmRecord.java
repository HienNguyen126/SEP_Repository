/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;
import java.util.Date;
import javax.microedition.lcdui.*;
/**
 *
 * @author THACH
 */
public class frmRecord extends Form implements CommandListener{
    DateField dtfRecordDate = new DateField("Today", DateField.DATE);
    Date d;
    Command cmdAddRecord = new Command("Add new", Command.OK, 1);
    Command cmdEditRecord = new Command("Edit", Command.OK, 1);
    Command cmdDeleteRecord = new Command("Delete", Command.OK, 1);
    Command cmdBack = new Command("Back", Command.EXIT, 1);
    Display display;
    public frmRecord(Display display)
    {
        super("Record");
        d = new Date();
        dtfRecordDate.setDate(d);
        append(dtfRecordDate);
        addCommand(cmdAddRecord);
        addCommand(cmdEditRecord);
        addCommand(cmdDeleteRecord);
        addCommand(cmdBack);
        setCommandListener(this);
        this.display = display;
    }
    public void commandAction(Command c, Displayable d)
    {
        if(c== cmdAddRecord)
        {
            frmAddRecord ar = new frmAddRecord(display);
            display.setCurrent(ar);
        }
        else if(c==cmdEditRecord)
        {
            frmEditRecord er = new frmEditRecord(display);
            display.setCurrent(er);
        }
    }
}
