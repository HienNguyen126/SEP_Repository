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
public class frmAddRecord extends Form implements CommandListener{
    TextField txtTag = new TextField("Tag", null, 20, TextField.ANY);
    TextField txtDescription = new TextField("Description", "", 200, TextField.ANY);
    DateField dtfDate= new DateField("Date", DateField.DATE);
    DateField dtfStart = new DateField("Start", DateField.TIME);
    DateField dtfEnd = new DateField("End", DateField.TIME);
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    Display display;
    public frmAddRecord(Display display)
    {
        super("Add new record");
        append(txtTag);
        append(dtfDate);
        append(dtfStart);
        append(dtfEnd);
        append(txtDescription);
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
        else if(c==cmdCancel)
        {
            frmRecord record= new frmRecord(display);
            display.setCurrent(record);
        }
    }
}
