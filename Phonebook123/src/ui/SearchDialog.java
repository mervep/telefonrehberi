package ui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

import main.MainFrame;
import manger.DBManager;
import util.Person;
import util.QueryParamType;

@SuppressWarnings("serial")
public class SearchDialog extends JDialog {
	private DefaultListModel<Person> sampleModel;
	private MainFrame mainFrame;

	/**
	 * Bu sınıf ilk oluşturulduğunda ilk olarak bu metot çağrilir.
	 */
	public SearchDialog(DefaultListModel<Person> sampleModel, MainFrame mainFrame) {
		this.sampleModel = sampleModel;
		this.mainFrame = mainFrame;
		createContents();
	}

	public void createContents() {
		this.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		final JRadioButton nameRadioButton = new JRadioButton("isime göre ara yapmak icin");
		this.getContentPane().add(nameRadioButton);
		final TextField txtIsim = new TextField();
		this.getContentPane().add(txtIsim);
		final JRadioButton surnameRadioButton = new JRadioButton("Soyisime göre ara yapmak icin");
		Button btnBul = new Button("GOSTER");
		ButtonGroup group=new ButtonGroup();
		group.add(surnameRadioButton);
		group.add(nameRadioButton);
		this.getContentPane().add(surnameRadioButton);
		this.getContentPane().add(btnBul);
		btnBul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Person> persons;
				if (nameRadioButton.isSelected()) {
					persons = DBManager.searchPerson(txtIsim.getText(),
							QueryParamType.NAME);
					for (int i = 0; i < persons.size(); i++) {
						sampleModel.setElementAt(new Person(), i);
					}
					mainFrame.getList().setListData(persons.toArray());
				}
				if (surnameRadioButton.isSelected()) {
					persons = DBManager.searchPerson(txtIsim.getText(),
							QueryParamType.SURNAME);
					for (int i = 0; i < persons.size(); i++) {
						sampleModel.setElementAt(new Person(), i);
					}
					mainFrame.getList().setListData(persons.toArray());
				}
			}
		});
	}
}
