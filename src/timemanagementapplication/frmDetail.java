/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.Command;
/**
 *
 * @author Thang Nguyen
 */
public class frmDetail extends Form implements CommandListener{
   private static TextField txtDetail = new TextField("Detail:", null, 100, TextField.INITIAL_CAPS_WORD);
    private Ticker hi = new Ticker("Mon 6/6 - Sat 11/6");
    Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
    Command cmdOk = new Command("Ok", Command.OK, 1);
    
    Display display;
    TextBox box;
    public frmDetail(Display display)
    {
        super("Detail");
        append(txtDetail);
        addCommand(cmdOk);
        addCommand(cmdCancel);
        setCommandListener(this);
        this.setTicker(hi);
       
    }
    public void commandAction(Command c, Displayable d)
    {
        if(c==cmdOk)
        {
            frmReport rp = new frmReport(display);
            display.setCurrent(rp);
        }
        else if(c==cmdCancel)
        {
           frmReport rp = new frmReport(display);
            display.setCurrent(rp);
        }
      
    }
    
}
