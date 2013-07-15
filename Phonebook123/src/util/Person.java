package util;

public class Person {

	private int id;
	private String name;
	private String surname;
	private String phonenumber;

	public Person() {
	}

	public String getname() {
		return name;
	}

	public void setname(String adi) {
		this.name = adi;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String soyadi) {
		this.surname = soyadi;
	}

	public String getphonenumber() {
		return phonenumber;
	}

	public void setphonenumber(String telefonu) {
		this.phonenumber = telefonu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String toString() {
		return id + " " + name + " " + surname + " " + phonenumber;
	}
}
