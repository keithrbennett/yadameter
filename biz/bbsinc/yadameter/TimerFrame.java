package biz.bbsinc.yadameter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class TimerFrame extends JFrame {
    
    private TimerPanel timerPanel;
    private TimerController controller;
    private Context context;
    private TimerModel timerModel;
    
    public TimerFrame() {
        super("Yadameter - http://sourceforge.net/projects/yadameter/");
        init();
    }

    private void init() {
        context = new Context(this);
        timerModel = new TimerModel(context);
        controller = new TimerController(context);
        getContentPane().add(getTimerPanel(), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupInitialFocusListener();
        pack();
    }
    
    public TimerPanel getTimerPanel() {
        if (timerPanel == null) {
        	    timerPanel = new TimerPanel(context);
        }
        return timerPanel;
    }
    
    
    public TimerController getTimerController() {
    	    return controller;
    }
    
    
	/**
	 * @return Returns the timerModel.
	 */
	public TimerModel getTimerModel() {
		return timerModel;
	}

	
    private void setupInitialFocusListener() {
    	    addWindowListener(new WindowAdapter() {
    	    	    public void windowOpened(WindowEvent e) {
                getTimerPanel().getSignalPanel().setTimeDisplayed(0L);

    	    	    	    getTimerPanel().getControlPanel()
                            .getStartStopButton().requestFocus();
            }
        });
    }

    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int newHeight = screenSize.height * 7 / 8;
        int newWidth  = screenSize.width  * 7 / 8;

        return new Dimension(newWidth, newHeight);
    }
}
