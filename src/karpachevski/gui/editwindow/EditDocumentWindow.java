package karpachevski.gui.editwindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import karpachevski.component.DateField;
import karpachevski.component.PersonField;
import karpachevski.model.Document;

public class EditDocumentWindow extends EditWindow {

	private Document document;
	
	private JTextField titleFld;
	private JTextField templateOfDocFld;
	private DateField dateOfSupplyFld;
	private PersonField listOfSignFld;
	
	
	public EditDocumentWindow(JFrame parent, Document document) {
		super(parent);
		this.document = document;
		addComponentsToPane(getContentPane());
		fillFields();
		pack();
		setMinimumSize(getSize());
	}

	private void addComponentsToPane(Container pane) {
		JPanel lblAndFldPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		
		String[] properties = {"Название: ", "Шаблон: ", "Дата получения: ", "ФИО визировавших: "};

		for(int i = 0; i < properties.length; i++) {

			JLabel lbl = new JLabel(properties[i]);
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 0;
			lblAndFldPanel.add(lbl,c);
		}
		
		titleFld = new JTextField(10);
		templateOfDocFld =  new JTextField(10);
		dateOfSupplyFld = new DateField();
		listOfSignFld = new PersonField();
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weightx = 1;
		lblAndFldPanel.add(titleFld,c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		lblAndFldPanel.add(templateOfDocFld,c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weightx = 1;
		lblAndFldPanel.add(dateOfSupplyFld,c);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weightx = 1;
		lblAndFldPanel.add(listOfSignFld,c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		c.weightx = 1;
		lblAndFldPanel.add(new JSeparator(SwingConstants.HORIZONTAL),c);
		
		pane.add(lblAndFldPanel, BorderLayout.CENTER);
	}
	
	private void fillFields() {
		titleFld.setText(document.getTitle());
		templateOfDocFld.setText(document.getTemplateOfDoc());
		dateOfSupplyFld.setDate(document.getDateOfSupply());
//		listOfSignFld.setPerson(document.getListOfSign()); 
	}
	
	@Override
	public Document getObject() {
		document.setTitle(titleFld.getText());
		document.setTemplateOfDoc(templateOfDocFld.getText());
		document.setDateOfSupply(dateOfSupplyFld.getDate());
//		document.setListOfSsign(listOfSignFld.getPerson()); 
		
		return document;
	}

	@Override
	protected void actionOk() {
		if (titleFld.getText().trim().length() < 1) {
			JOptionPane.showMessageDialog(this, "Введите название документа", "Заполните поля!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
	}
}
