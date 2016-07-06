/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Khoi Nguyen
 */
public class frmLogin extends Form implements CommandListener{
    private static TextField txtEmail = new TextField("Email:", null, 100, TextField.EMAILADDR);
    private static TextField txtPassword = new TextField("Password", null, 100, TextField.PASSWORD);
    private static Command cmdExit = new Command("Exit", Command.EXIT, 1);
    private static Command cmdLogin = new Command("Login", Command.OK, 1);
    private static Command cmdRegister = new Command("Register", Command.OK, 1);
    private Display display;
    public frmLogin(Display display) {
        super("Login");
        append(txtEmail);
        append(txtPassword);
        addCommand(cmdExit);
        addCommand(cmdLogin);
        addCommand(cmdRegister);
        setCommandListener(this);
        this.display = display;
    }


    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(c == cmdLogin){
            String email = txtEmail.getString();
            String password = txtPassword.getString();
            if(email.equals("") && password.equals("")){
                frmMain Main = new frmMain(display);
                display.setCurrent(Main);
            }else{
                Alert altLogin = new Alert("Login Fail!", "ID or password is incorret!", null, AlertType.WARNING);
                display.setCurrent(altLogin, this);
            }
        }else if (c == cmdRegister){
            
        }else{
            
        }
    }
}
