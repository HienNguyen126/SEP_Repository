/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bus.busUser;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.apdu.APDUConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Khoi Nguyen
 */
public class frmLogin extends Form implements CommandListener {

    private static TextField txtEmail = new TextField("Email:", "", 50, TextField.EMAILADDR);
    private static TextField txtPassword = new TextField("Password", "", 50, TextField.PASSWORD);
    private static Command cmdExit = new Command("Exit", Command.EXIT, 1);
    private static Command cmdLogin = new Command("Login", Command.OK, 1);
    private static Command cmdRegister = new Command("Register", Command.OK, 1);
    private Display display;
    public String uid;

    public frmLogin(Display display) {

        super("Login");
        append(txtEmail);
        append(txtPassword);
        addCommand(cmdLogin);
        addCommand(cmdRegister);
        setCommandListener(this);
        this.display = display;
    }

    bus.busUser bususer = new busUser();

    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdLogin) {
            String Email = txtEmail.getString();
            String Password = txtPassword.getString();
            Email.valueOf(txtEmail.getString());
            Password.valueOf(txtPassword.getString());
            String result = bususer.Login(Email, Password);
            String[] parts = Split(result, "|");
            String part1 = parts[0];
            if (part1.equals("Ok")) {
                String part2 = parts[1];
                int uidj = Integer.parseInt(part2.trim());
                uid = "" + uidj;
                frmMain main = new frmMain(display, uid);
                display.setCurrent(main);
            } else {
                Alert altLogin = new Alert("Login Failed!", result, null, AlertType.WARNING);
                display.setCurrent(altLogin, this);
            }
        } else if (c == cmdRegister) {
            String Email = txtEmail.getString();
            Email.valueOf(txtEmail.getString());
            String[] testemail = Split(Email, "@");
            String Test = testemail[1];
            if (Test.equals("vanlanguni.vn") || Test.equals("vanlanguni.edu.vn")) {
                Random rand = new Random();
                int answer = rand.nextInt((999999 - 100000) + 1) + 100000;
                String Password = "" + answer;
                String result = bususer.Register(Email, Password);
                if (result.startsWith("Ok")) {
                    String result2 = bususer.SendEmail(Email, Password);
                    if (result2.startsWith("Ok")) {
                        Alert altLogin = new Alert("Registration Success!", "Please check your email", null, AlertType.WARNING);
                        display.setCurrent(altLogin, this);
                    } else {
                        Alert altLogin = new Alert("Registration Failed!", result2, null, AlertType.WARNING);
                        display.setCurrent(altLogin, this);
                    }
                } else {
                    Alert altLogin = new Alert("Registration Failed!", result, null, AlertType.WARNING);
                    display.setCurrent(altLogin, this);
                }
            } else {
                Alert altLogin = new Alert("Registration Failed!", "Wrong format email!", null, AlertType.WARNING);
                display.setCurrent(altLogin, this);
            }
        }
    }

    public static String[] Split(String splitStr, String delimiter) {
        StringBuffer token = new StringBuffer();
        Vector tokens = new Vector();
        // split
        char[] chars = splitStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (delimiter.indexOf(chars[i]) != -1) {
                // we bumbed into a delimiter
                if (token.length() > 0) {
                    tokens.addElement(token.toString());
                    token.setLength(0);
                }
            } else {
                token.append(chars[i]);
            }
        }
        // don't forget the "tail"...
        if (token.length() > 0) {
            tokens.addElement(token.toString());
        }
        // convert the vector into an array
        String[] splitArray = new String[tokens.size()];
        for (int i = 0; i < splitArray.length; i++) {
            splitArray[i] = (String) tokens.elementAt(i);
        }
        return splitArray;
    }
}
