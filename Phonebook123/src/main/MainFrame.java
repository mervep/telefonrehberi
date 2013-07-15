package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import manger.DBManager;
import ui.SearchDialog;
import util.Person;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JTextField txtName;
	private JTextField txtSurName;
	private JTextField txtPhoneNumber;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnList;
	private JButton btnUpdate;
	private JButton btnSave;
	DBManager db = new DBManager();
	private DefaultListModel<Person> sampleModel;
	private JPanel pnlList;
	private JList<Object> list;
	private JPanel pnlProject;
	@SuppressWarnings("unused")
	private SearchDialog searchDialog;
	private MainFrameListener buttonListener;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainFrame() {
		super("TELEFON REHBERİ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/java/swing/plaf/motif/icons/Question.gif")));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				int result = JOptionPane.showInternalConfirmDialog(
						getContentPane(),
						"Uygulamadan çıkmak istediğinize emin misiniz?",
						"Kapatma Penceresi", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
					// ((Window) getContentPane()).dispose();
				} else if (result == JOptionPane.NO_OPTION) {
					getContentPane().setVisible(true);
				}
			}

		});
		buttonListener = new MainFrameListener(this);
		pnlProject = new JPanel();
		getContentPane().add(pnlProject);
		pnlProject.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel pnlSaveDelete = new JPanel();
		pnlProject.add(pnlSaveDelete);
		pnlSaveDelete.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel name_label = new JLabel("Adı:");
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSaveDelete.add(name_label);

		txtName = new JTextField();
		pnlSaveDelete.add(txtName);
		txtName.setColumns(10);

		JLabel surname_lbl = new JLabel("Soyadı:");
		surname_lbl.setIcon(null);
		surname_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSaveDelete.add(surname_lbl);

		txtSurName = new JTextField();
		pnlSaveDelete.add(txtSurName);
		txtSurName.setColumns(10);

		JLabel telefon_lbl = new JLabel("Telefon no:");
		telefon_lbl.setIcon(null);
		telefon_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSaveDelete.add(telefon_lbl);

		txtPhoneNumber = new JTextField();
		pnlSaveDelete.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);

		btnSave = new JButton("KAYDET");
		// UNUTMA
		btnSave.addActionListener(buttonListener);
		pnlSaveDelete.add(btnSave);
		btnSave.setForeground(Color.BLACK);

		btnDelete = new JButton("SİL");
		btnDelete.addActionListener(buttonListener);
		pnlSaveDelete.add(btnDelete);
		btnDelete.setVerticalAlignment(SwingConstants.TOP);

		pnlList = new JPanel();
		pnlProject.add(pnlList);
		pnlList.setLayout(new GridLayout(1, 0, 0, 0));

		final List<Person> kisiler = DBManager.listPerson();

		sampleModel = new DefaultListModel<Person>();
		for (int i = 0; i < kisiler.size(); i++) {
			sampleModel.addElement((Person) kisiler.get(i));
		}
		list = new JList(sampleModel);
		pnlList.add(list);
		list.addListSelectionListener(null);

		JScrollPane listPane = new JScrollPane(list);
		pnlList.add(listPane);

		JPanel pnlUpdateSerach = new JPanel();
		pnlProject.add(pnlUpdateSerach);
		pnlUpdateSerach.setLayout(new GridLayout(3, 2, 0, 0));

		btnUpdate = new JButton("GÜNCELLE");
		btnUpdate.addActionListener(buttonListener);

		btnSearch = new JButton("ARA");
		searchDialog = new SearchDialog(sampleModel, this);
		btnSearch.addActionListener(buttonListener);
		pnlUpdateSerach.add(btnSearch);
		btnList = new JButton("LİSTELE");
		// btnList.addMouseListener(null);
		btnList.addActionListener(buttonListener);

		pnlUpdateSerach.add(btnList);
		pnlUpdateSerach.add(btnUpdate);
	}

	public JList<Object> getList() {
		return list;
	}

	public void setList(JList<Object> list) {
		this.list = list;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnList() {
		return btnList;
	}

	public void setBtnList(JButton btnList) {
		this.btnList = btnList;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtSurName() {
		return txtSurName;
	}

	public void setTxtSurName(JTextField txtSurName) {
		this.txtSurName = txtSurName;
	}

	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}

	public void setTxtPhoneNumber(JTextField txtPhoneNumber) {
		this.txtPhoneNumber = txtPhoneNumber;
	}

	public DefaultListModel<Person> getSampleModel() {
		return sampleModel;
	}

	public void setSampleModel(DefaultListModel<Person> sampleModel) {
		this.sampleModel = sampleModel;
	}
}
