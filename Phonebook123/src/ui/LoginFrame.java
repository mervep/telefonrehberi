package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import main.MainFrame;
import manger.DBManager;
import util.User;
import util.WriteLog;

import com.mysql.jdbc.Connection;

public class LoginFrame {

	private JFrame loginFrame;
	private JPasswordField txtUserPass;
	private JTextField txtUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		WriteLog.write();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setBounds(450, 230, 270, 140);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnlLogin = new JPanel();
		loginFrame.getContentPane().add(pnlLogin, BorderLayout.CENTER);
		pnlLogin.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel pnlUserInformation = new JPanel();
		pnlLogin.add(pnlUserInformation);
		pnlUserInformation.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblUserName = new JLabel("Kullanıcı Adı:");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUserInformation.add(lblUserName);

		txtUserName = new JTextField();
		pnlUserInformation.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblUserPass = new JLabel("Kullanıcı Sifresi");
		lblUserPass.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUserInformation.add(lblUserPass);

		txtUserPass = new JPasswordField();
		pnlUserInformation.add(txtUserPass);
		txtUserPass.setColumns(10);

		JPanel pnlButton = new JPanel();
		pnlLogin.add(pnlButton);

		JButton btnLogin = new JButton("GIRIS");
		btnLogin.registerKeyboardAction(btnLogin.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBManager.connectDB();
				@SuppressWarnings("deprecation")
				List<User> list = DBManager.logIn(txtUserName.getText(),
						txtUserPass.getText(), con);
				if (list.size() > 0) {
					loginFrame.dispose();
					MainFrame frame = new MainFrame();
					frame.setBounds(450, 200, 500, 450);
                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE );
                    frame.setResizable(false);
					frame.setVisible(true);
					
				}

				else {
					DBManager
							.information("KULLANICI ADI VEYA SIFRE HATALI TEKRAR DENEYINIZ");

				}
			}

		});
		pnlButton.add(btnLogin);
	}

}
