/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapplication;


import javax.microedition.lcdui.*;


/**
 *
 * @author Thang Nguyen
 */
public class frmReport extends Form implements CommandListener{
        Display display;
        private Gauge gauge;
        private Gauge gauge1;
        private Gauge gauge2;
        private Gauge gauge3;
        private Gauge gauge4;
        private boolean isSafeToExit;
        private Ticker hi = new Ticker("Mon 6/6 - Sat 11/6");
        Command cmdDetail = new Command("Detail", Command.OK, 1);
        Command cmdCancel = new Command("Cancel", Command.CANCEL, 1);
        public String uid;
       
    public frmReport(Display display, String uids)   
    {
          super("Report");
          this.uid = uids;
          gauge = new Gauge("Entertainment", false, 100, 50);
          gauge1 = new Gauge("Study", false, 100, 33);
          gauge2 = new Gauge("Housework", false, 100, 21);
          gauge3 = new Gauge("Homework", false, 100, 19);
          gauge4 = new Gauge("Sport", false, 100, 80);
          append(gauge);
          append(gauge1);
          append(gauge2);
          append(gauge3);
          append(gauge4);
          addCommand(cmdDetail);
          addCommand(cmdCancel);
          setCommandListener(this);
          this.display=display;
          isSafeToExit = true;
          this.setTicker(hi);
    }

    public void commandAction(Command c, Displayable d) {
       if(c== cmdDetail)
        {
            frmDetail dt = new frmDetail(display , uid);
            display.setCurrent(dt);
        }
       else if (c == cmdCancel)
               {
                   frmMain ma = new frmMain(display, uid);
                   display.setCurrent(ma);
               }
        }
    class GaugeUpdater implements Runnable {

    public void run() {
      isSafeToExit = false;
      try {
        while (gauge.getValue() < gauge.getMaxValue()) {
          Thread.sleep(1000);
          gauge.setValue(gauge.getValue() + 1);
        }
        isSafeToExit = true;
        gauge.setLabel("Process Completed.");
      } catch (InterruptedException Error) {
        throw new RuntimeException(Error.getMessage());
      }
    }
  }
}
    

    

