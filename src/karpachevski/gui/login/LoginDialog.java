package karpachevski.gui.login;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {

	private JTextField usernameFld;
	private JPasswordField passwordFld;
	private JTextField DBNameFld;
	
	public LoginDialog(JFrame parent) {
		super(parent, true);
		setTitle("Выберите базу даных");
		setLocationRelativeTo(parent);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				actionCancel();
			}
		});
		addComponentsToPane(getContentPane());
		pack();
		
		setMinimumSize(getSize());
	}
	
	private void addComponentsToPane(Container pane) {
		JPanel textFldPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		
		JLabel infoLbl = new JLabel("Введите имя пользователя, пароль и назание базы данных: ");
		JLabel usernameLbl = new JLabel("Имя пользователя (postgres): ");
		usernameFld = new JTextField("postgres");
		JLabel passwordLbl = new JLabel("Пароль: ");
		passwordFld = new JPasswordField("111");
		JLabel DBNameLbl = new JLabel("Название базы данных (DissertationDB): ");
		DBNameFld = new JTextField("DissertationDB");

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		textFldPanel.add(usernameLbl,c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		textFldPanel.add(usernameFld,c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		textFldPanel.add(passwordLbl,c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		textFldPanel.add(passwordFld,c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		textFldPanel.add(DBNameLbl,c);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		textFldPanel.add(DBNameFld,c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weightx = 1;
		textFldPanel.add(new JSeparator(SwingConstants.HORIZONTAL),c);
		
		JPanel buttonPanel = new JPanel();
		JButton okBtn = new JButton("Ok");
		JButton cancelBtn = new JButton("Cancel");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLogin();
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancel();
			}
		});
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);

		pane.add(infoLbl, BorderLayout.NORTH);
		pane.add(textFldPanel, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void actionLogin() {
		if (usernameFld.getText().trim().length() < 1 ||
			DBNameFld.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(this, "Введите имя пользователя и название базы данных", "Заполните поля!", JOptionPane.ERROR_MESSAGE);
				usernameFld.requestFocusInWindow();
				return;
		}
		dispose();
	}
	
	private void actionCancel() {
		System.exit(0);
	}

	public String getUsername() {
		return usernameFld.getText();
	}
	
	public String getPassword() {
		return new String(passwordFld.getPassword());
	}
	
	public String getDBName() {
		return DBNameFld.getText();
	}
	
}
