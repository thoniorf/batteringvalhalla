package it.batteringvalhalla.gamegui.menu.customcomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class JCustomComboBox extends JComboBox<String> {
	private static final long serialVersionUID = 1L;

	public JCustomComboBox(String[] items) {
		super(items);
		setEditable(true);
		setUI(CustomArrow.createUI(this));
		setRenderer(new ComboBoxRenderCustom());
		setEditor(new ComboBoxEditorCustom());
		setOpaque(false);
	}

}

// custom render class for map list
class ComboBoxRenderCustom extends JLabel implements ListCellRenderer<String> {
	private static final long serialVersionUID = 1L;

	public ComboBoxRenderCustom() {
		setOpaque(true);
		setBackground(new Color(211, 191, 143));
		setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 54));
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value);
		return this;
	}

}

class ComboBoxEditorCustom extends BasicComboBoxEditor {

	private String selectedItem;
	private JLabel label = new JLabel();
	private JPanel panel = new JPanel();

	public ComboBoxEditorCustom() {
		// label.setOpaque(false);
		label.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 54));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBackground(new Color(211, 191, 143));

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		panel.setBackground(new Color(211, 191, 143));
		panel.add(label);

	}

	@Override
	public Component getEditorComponent() {
		return this.panel;
	}

	@Override
	public Object getItem() {
		return this.selectedItem;
	}

	@Override
	public void setItem(Object anObject) {
		this.selectedItem = (String) anObject;
		label.setText(selectedItem);
	}
}

class CustomArrow extends BasicComboBoxUI {

	public static ComboBoxUI createUI(JComponent c) {
		return new CustomArrow();
	}

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
		super.paintCurrentValueBackground(g, bounds, hasFocus);

	}

	@Override
	protected JButton createArrowButton() {
		return new JButtonRound(ResourcesLoader.images.get("down"), ResourcesLoader.images.get("down_hover"));
	}
}
