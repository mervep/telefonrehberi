package ui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import util.Person;

import main.MainFrame;
import manger.DBManager;


@SuppressWarnings("serial")
public class UpdateDialog extends JDialog {
	private MainFrame mainFrame;

	public UpdateDialog(MainFrame mainframe) {
		this.mainFrame = mainframe;
		createContents();
	}
	private void createContents() {
		this.getContentPane().setLayout(new GridLayout(5, 2, 2, 2));
		
		Person duzenleKisi = new Person();
		duzenleKisi = (Person) mainFrame.getList().getSelectedValue();
		Label lblName = new Label("Adı:");
		this.getContentPane().add(lblName);
		final TextField txtName = new TextField();
		this.getContentPane().add(txtName);
		txtName.setText(duzenleKisi.getname());
		
		Label lblSurname = new Label("Soyadı:");
		this.getContentPane().add(lblSurname);
		final TextField txtSurname = new TextField("");
		this.getContentPane().add(txtSurname);
		txtSurname.setText(duzenleKisi.getsurname());
		
		Label lblnumber = new Label("Numarası:");
		this.getContentPane().add(lblnumber);
		final TextField txtNumber = new TextField();
		this.getContentPane().add(txtNumber);
		txtNumber.setText(duzenleKisi.getphonenumber());
		
		Label lbl_Tlf = new Label();
		this.getContentPane().add(lbl_Tlf);
		lbl_Tlf.setText("Yeni Telefon numarasini Giriniz:  ");
		final TextField txt_Tlf = new TextField();
		this.getContentPane().add(txt_Tlf);
		JButton btn_gnclle = new JButton();
		this.getContentPane().add(btn_gnclle);
		btn_gnclle.setText("DUZENLE");
		btn_gnclle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Person duzenleKisi = new Person();
				duzenleKisi = (Person) mainFrame.getList().getSelectedValue();
				DBManager.update(txt_Tlf, duzenleKisi);
				mainFrame.getList().setListData(DBManager.listPerson().toArray());
			}
		});
	}
}
