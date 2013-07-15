package manger;

import java.awt.TextField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.Person;
import util.QueryParamType;
import util.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBManager {
	/**
	 * 
	 */

	static Connection connection = null;

	/**
	 * bu metot veri tabani baglantisini saglar.
	 * 
	 * @return connection
	 */
	public static Connection connectDB() {
		try {
			String connectionString = "jdbc:mysql:///vt_ogrenci";
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(
					connectionString, "root", "1234");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * bu metot ayni numarali kisi yoksa yeni kayit yapilmasini saglar.
	 * 
	 * @param newPerson
	 *            kayit edicelek kisidir.
	 */
	public static void savePerson(Person newPerson) {
		Connection baglanti = connectDB();
		try {
			Statement sorgu = (Statement) connection.createStatement();
			ResultSet rs = sorgu
					.executeQuery("SELECT * FROM pbook where Number= "
							+ newPerson.getphonenumber());
			if (rs.next() == false) {
				sorgu = (Statement) connection.createStatement();
				sorgu.executeUpdate("INSERT INTO pbook(Name, Surname, Number) VALUES('"
						+ newPerson.getname()
						+ "', '"
						+ newPerson.getsurname()
						+ "', '" + newPerson.getphonenumber() + "')");
				sorgu.close();
				baglanti.close();
				DBManager.information("KAYDEDİLDİ:" + newPerson.getname()
						+ newPerson.getsurname() + newPerson.getphonenumber());
			} else {
				DBManager.information("Bu numarali kisi var,kayit yapilamaz!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * bu metot veri tabanindaki kisileri arayüzde listeler.
	 * 
	 * @return veritabanindaki kisiler listesidir.
	 */
	public static List<Person> listPerson() {
		List<Person> personList = new ArrayList<Person>();
		Connection baglanti = connectDB();
		try {
			Statement sorgu = (Statement) connection.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM pbook ");
			while (rs.next()) {
				Person yenikisi = new Person();
				yenikisi.setId(rs.getInt("id"));
				yenikisi.setname(rs.getString("Name"));
				yenikisi.setsurname(rs.getString("Surname"));
				yenikisi.setphonenumber(rs.getString("Number"));
				personList.add(yenikisi);
			}
			sorgu.close();
			baglanti.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personList;
	}

	/**
	 * bu metot veri tabanindan bir kisinin silinmesini saglar.
	 * 
	 * @param deletePerson
	 *            secilen silinecek kisidir.
	 */
	public static void deletePerson(Person deletePerson) {
		Connection baglanti = connectDB();
		try {
			Statement sorgu = (Statement) baglanti.createStatement();
			sorgu.executeUpdate("DELETE FROM pbook WHERE id='"
					+ deletePerson.getId() + "'");
			sorgu.close();
			baglanti.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Person> searchPerson(String strQueryParameter,
			QueryParamType paramType) {
		List<Person> persons = new ArrayList<>();
		Connection baglanti = connectDB();
		try {
			String query = null;
			if (paramType == QueryParamType.NAME) {
				query = "SELECT * FROM pbook WHERE Name = '"
						+ strQueryParameter + "'";
			} else if (paramType == QueryParamType.SURNAME) {
				query = "SELECT * FROM pbook WHERE Surname = '"
						+ strQueryParameter + "'";
			} else {
			}
			Statement sorgu = (Statement) connection.createStatement();
			ResultSet rs = sorgu.executeQuery(query);

			while (rs.next()) {
				Person searchPerson = new Person();
				searchPerson.setId(rs.getInt("id"));
				searchPerson.setname(rs.getString("name"));
				searchPerson.setsurname(rs.getString("surname"));
				searchPerson.setphonenumber(rs.getString("number"));
				persons.add(searchPerson);
			}
			if (persons.size() == 0) {
				information("BOYLE BIR KAYIT YOK!");
			}
			sorgu.close();
			baglanti.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}

	/**
	 * bu metot secilen kisinin dzüenlenmesini saglar.
	 * 
	 * @param updatePerson
	 *            güllencek kisidir.
	 * @param txt_Tlf
	 *            güncel numara.
	 */
	public static void update(TextField txtUpdateNumber,Person updatePerson)  {
		Connection baglanti = connectDB();
		try {
			Statement sorgu = (Statement) baglanti.createStatement();
			sorgu.executeUpdate("UPDATE pbook SET number=" + txtUpdateNumber.getText()
					+ "  WHERE id=" +updatePerson.getId() + " ");
			
			sorgu.close();
			baglanti.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * bu metot veritabanindan kullanici adini ve sifresini kontrol eder ve
	 * uyanları geriye döndürür.
	 * 
	 * @param userName
	 *            kullanicinin girdigi kullanici adi.
	 * @param password
	 *            kullanicinin girdigi kullanici sifresi.
	 * @param connection
	 *            mysql veri tabani baglantisi.
	 * @return sql sorgusuna uyan kisiler listesi döndürür geriye.
	 */
	public static List<User> logIn(String userName, String password,
			Connection connection) {
		connectDB();
		List<User> userList = new ArrayList<User>();
		try {

			String sqlQuery = "SELECT * FROM usertable WHERE userName='"
					+ userName + "' and Password='" + password + "'";
			Statement durum = (Statement) connection.createStatement();
			ResultSet rs = (ResultSet) durum.executeQuery(sqlQuery);
			while (rs.next()) {
				String name = rs.getString("userName");
				String pass = rs.getString("Password");

				User user = new User(name, pass);
				userList.add(user);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * bu metot kullaniciya bilgi veren dialoglar acar.
	 * 
	 * @param string
	 *            bilgi stringidir.
	 */
	public static void information(String string) {
		JOptionPane.showMessageDialog(null, string, "BILGI", 1);
	}
}
