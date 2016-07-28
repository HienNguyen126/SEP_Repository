/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tag;

import bus.busTag;
import gui.frmMain;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 *
 * @author Thuong
 */
public class frmTag extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 1);
    Command cmdAddTag = new Command("Add", Command.OK, 1);
    Command cmdEditTag = new Command("Edit", Command.OK, 1);
    Command cmdDeleteTag = new Command("Delete", Command.OK, 1);
    private ChoiceGroup radioButtons = new ChoiceGroup(null, Choice.EXCLUSIVE);
    public String uid;
    String[][] dalist = new String[100][3];

    public frmTag(Display display, String uids) {
        super("Tag");
        this.uid = uids;
        bus.busTag tag = new busTag();
        String all = tag.ListTag(uid);
        String trim = all.trim();
        String[] list = Split(trim, "\n");
        for (int i = 0; i < list.length; i++) {
            String[] part = Split(list[i], "|");
            dalist[i][0] = part[0];
            dalist[i][1] = part[1];
            dalist[i][2] = part[2];
            String name = Encode(dalist[i][1]);
            String desc = Encode(dalist[i][2]);
            radioButtons.append("" + name + "\n" + desc, null);
        }
        append(radioButtons);
        addCommand(cmdBack);
        addCommand(cmdAddTag);
        addCommand(cmdEditTag);
        addCommand(cmdDeleteTag);
        setCommandListener(this);
        this.display = display;
    }
    Display display;

    public void commandAction(Command c, Displayable d) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c == cmdAddTag) {
            frmAddTag at = new frmAddTag(display, uid);
            display.setCurrent(at);
        } else if (c == cmdEditTag) {
            int index = radioButtons.getSelectedIndex();
            String tagid = dalist[index][0];
            frmEditTag et = new frmEditTag(display, uid, tagid);
            display.setCurrent(et);
        } else if (c == cmdDeleteTag) {
            int index = radioButtons.getSelectedIndex();
            String tagid = dalist[index][0];
            frmDeleteTag dt = new frmDeleteTag(display, uid, tagid);
            display.setCurrent(dt);
        } else {
            frmMain wc = new frmMain(display, uid);
            display.setCurrent(wc);
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

    public static String replace(String _text, String _searchStr, String _replacementStr) {
        // String buffer to store str
        StringBuffer sb = new StringBuffer();

        // Search for search
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int searchStringLength = _searchStr.length();

        // Iterate to add string
        while (searchStringPos != -1) {
            sb.append(_text.substring(startPos, searchStringPos)).append(_replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
        }

        // Create string
        sb.append(_text.substring(startPos, _text.length()));

        return sb.toString();
    }

    public static String Decode(String decode) {
        String rename1 = replace(decode, "|", "mzo");
        String rename2 = replace(rename1, "+", "ozm");
        String rename3 = replace(rename2, " ", "+");
        return rename3;
    }

    public static String Encode(String encode) {
        String rename5 = replace(encode, "mzo", "|");
        String rename6 = replace(rename5, "ozm", "+");
        return rename6;
    }

}
