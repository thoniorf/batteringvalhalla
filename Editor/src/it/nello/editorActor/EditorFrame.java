package it.nello.editorActor;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class EditorFrame extends JFrame{
	public static void main(String[] args) {
		EditorFrame editorFrame = new EditorFrame();
		editorFrame.setVisible(true);	
	}
	
	private static EditorPanel editorPanel;

	public EditorFrame() {
		editorPanel = new EditorPanel();
	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				editorPanel.showExitConfirm();
			}
		});

		

		setContentPane(editorPanel);
		pack();
		setLocationRelativeTo(null);
		
	}
	

	/*public boolean Chiudere(){
		return editorPanel.Chiudere();
	}*/

	

	
}
