/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * @author Khoi Nguyen
 */
public class RunApplication extends MIDlet {

    private Display display;

    public RunApplication() {
        display = Display.getDisplay(this);
    }

    public void startApp() {

        frmLogin Login = new frmLogin(display);
        display.setCurrent(Login);

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
