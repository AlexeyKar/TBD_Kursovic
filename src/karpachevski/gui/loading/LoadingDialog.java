package karpachevski.gui.loading;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class LoadingDialog extends JDialog {

	public LoadingDialog(JFrame parent) {
		super(parent, true);
		setTitle("Загрузка данных из БД");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(new JLabel("Происходит загрузка списков из базы данных..."));
		setContentPane(contentPanel);
		pack();
		setLocationRelativeTo(parent);
	}
	
}
