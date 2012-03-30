package karpachevski.gui.editwindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import karpachevski.model.SuperEntity;

public abstract class EditWindow extends JDialog {
	
	private boolean canceled;
	
	public EditWindow(JFrame parent) {
		super(parent, true);
		setTitle("Редактирование");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				actionCancel();
			}
		});
		setLocationRelativeTo(parent);
		addButtons(getContentPane());
		canceled = false;
	}
	
	public abstract SuperEntity getObject();
	protected abstract void actionOk();
	
	private void addButtons(Container pane) {
		JPanel buttonPanel = new JPanel();
		JButton okBtn = new JButton("Ok");
		okBtn.setMnemonic(KeyEvent.VK_ENTER);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOk(); 
			}
		});
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setMnemonic(KeyEvent.VK_ESCAPE);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancel();
			}
		});
		
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void actionCancel() {
		canceled = true;
		dispose();
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
}
