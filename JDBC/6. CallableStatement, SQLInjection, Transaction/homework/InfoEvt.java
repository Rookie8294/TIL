package work0821;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoEvt extends WindowAdapter {
	
	private InfoView iView;
	
	public InfoEvt(InfoView iView) {
		this.iView =  iView;
	}//InfoEvt

	@Override
	public void windowClosing(WindowEvent e) {
		iView.dispose();
	}//windowClosing
	
}//class
