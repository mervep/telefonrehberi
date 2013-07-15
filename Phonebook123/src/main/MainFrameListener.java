package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import ui.SearchDialog;
import ui.UpdateDialog;
import util.Person;

import manger.DBManager;

public class MainFrameListener implements ActionListener {

	private UpdateDialog dialog;
	private SearchDialog searchDialog;
	private MainFrame mainFrame;
	private DefaultListModel<Person> sampleModel;

	public MainFrameListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainFrame.getBtnSave()) {
			if (mainFrame.getTxtName().getText().length() > 0
					&& mainFrame.getTxtSurName().getText().length() > 0
					&& mainFrame.getTxtPhoneNumber().getText().length() > 0) {
				Person newPerson = new Person();
				newPerson.setname(mainFrame.getTxtName().getText());
				newPerson.setsurname(mainFrame.getTxtSurName().getText());
				newPerson.setphonenumber(mainFrame.getTxtPhoneNumber().getText());
				DBManager.savePerson(newPerson);
				mainFrame.getTxtName().setText("");
				mainFrame.getTxtSurName().setText("");
				mainFrame.getTxtPhoneNumber().setText("");
				((JList) mainFrame.getList()).setListData(DBManager
						.listPerson().toArray());
			} else
				DBManager.information("BOS ALANLARI DOLDURUNUZ");
		}

		else if (e.getSource() == mainFrame.getBtnDelete()) {
			if(mainFrame.getList().getSelectedValue()==null){
				DBManager.information("BİR KİŞİ SEÇİNİZ");
			}
			else{
			int n = JOptionPane.showOptionDialog(null,
					"Silmek istediginizden emin misiniz?", "Sil",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (n == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Silindi!", "Bilgi",
						JOptionPane.WARNING_MESSAGE);
	
			Person deletePerson = new Person();
			deletePerson = (Person) mainFrame.getList().getSelectedValue();
			DBManager.deletePerson(deletePerson);
			mainFrame.getList().setListData(DBManager.listPerson().toArray());
			// DBManager.information("Silindi:" + silKisi.getname());
		}
			else if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Silinmedi!", "Bilgi",
						JOptionPane.WARNING_MESSAGE);
			} 
	}
		}
	
		else if (e.getSource() == mainFrame.getBtnUpdate()) {
			if (mainFrame.getList().getSelectedValue() == null) {
				DBManager
						.information("HİÇ BİR SEÇİM YAPMADINIZ!LÜTFEN BİRİNİ SEÇİNİZ");
			}
			else{
			dialog = new UpdateDialog(mainFrame);
			dialog.setBounds(250, 200, 370, 200);
			dialog.setVisible(true);
		}
		}
		 else if (e.getSource() == mainFrame.getBtnSearch()) {
				searchDialog = new SearchDialog(mainFrame.getSampleModel(), mainFrame);
				searchDialog.setBounds(260, 260, 470, 100);
				searchDialog.setResizable(false);
				searchDialog.setVisible(true);
			}

		else if (e.getSource() == mainFrame.getBtnList()) {
			mainFrame.getList().setListData(DBManager.listPerson().toArray());
		}
	}

	public DefaultListModel<Person> getSampleModel() {
		return sampleModel;
	}

	public void setSampleModel(DefaultListModel<Person> sampleModel) {
		this.sampleModel = sampleModel;
	}

	public SearchDialog getSearchDialog() {
		return searchDialog;
	}

	public void setSearchDialog(SearchDialog searchDialog) {
		this.searchDialog = searchDialog;
	}
}
