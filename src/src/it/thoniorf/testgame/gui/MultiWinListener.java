package it.thoniorf.testgame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MultiWinListener {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				showUI();

			}

		});

	}

	private static void showUI() {

		Two fr1 = new Two();

		fr1.setVisible(true);

		Two fr2 = new Two();

		fr2.setVisible(true);

		ActionListener[] broFrames = { fr1, fr2 };

		One f1 = new One(broFrames);

		f1.setVisible(true);

	}
}

class One extends JFrame {

	JButton button = new JButton("Press Here!");

	public One(ActionListener[] frs) {

		getContentPane().add(button);

		for (int c = 0; c < frs.length; c++) {

			button.addActionListener(frs[c]);

		}
	}
}

class Two extends JFrame implements ActionListener {

	protected JLabel label = new JLabel("");

	public Two() {

		getContentPane().add(label);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		label.setText("OK");
	}
}
