/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Hien 
 */
public class RunApplication extends MIDlet {

    //TextField txtTTTT;
    Display display = Display.getDisplay(this);
    public void startApp() {
        frmRecord record = new frmRecord(display);
        display.setCurrent(record);
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
