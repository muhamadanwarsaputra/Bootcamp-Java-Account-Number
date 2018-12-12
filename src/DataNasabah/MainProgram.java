package DataNasabah;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MainProgram {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/bank";
	static final String USER = "root";
	static final String PASS = "";

	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(inputStreamReader);

	/**
	 * @param args the command line arguments
	 */
	public void MainProgram() {
		System.out.println("Login Success");
		try {
			// register driver
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			while (!conn.isClosed()) {
				showMenu();
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void showMenu() {
		System.out.println("\n========= MENU UTAMA =========");
		System.out.println("1. Insert Data");
		System.out.println("2. Show Data");
		System.out.println("3. Edit Data");
		System.out.println("4. Delete Data");
		System.out.println("0. Keluar");
		System.out.println("");
		System.out.print("PILIHAN> ");

		try {
			int pilihan = Integer.parseInt(input.readLine());

			switch (pilihan) {
			case 0:
				System.exit(0);
				break;
			case 1:
				insertData();
				break;
			case 2:
				showData();
				break;
			case 3:
				updateData();
				break;
			case 4:
				deleteData();
				break;
			default:
				System.out.println("Pilihan salah!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void showData() {
		String sql = "SELECT * FROM DataNasabah";

		try {
			rs = stmt.executeQuery(sql);

			System.out.println("+--------------------------------+");
			System.out.println("|          DATA NASABAH          |");
			System.out.println("+--------------------------------+");
			System.out.println("\n=No.Account--Nama----Alamat-----");

			while (rs.next()) {
				int id_DataNasabah = rs.getInt("id_DataNasabah");
				String AccountNumber = rs.getString("AccountNumber");
				String Nama = rs.getString("Nama");
				String Alamat = rs.getString("Alamat");

				System.out.println(String.format("%d. %s %s -- (%s)", id_DataNasabah, AccountNumber, Nama, Alamat));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void insertData() {
		try {
			// ambil input dari user
//        	System.out.print("id_DataNasabah: ");
//            String id_DataNasabah = input.readLine().trim();
			System.out.print("AccountNumber: ");
			String AccountNumber = input.readLine().trim();
			System.out.print("Nama: ");
			String Nama = input.readLine().trim();
			System.out.print("Alamat: ");
			String Alamat = input.readLine().trim();

			// query simpan
			String sql = "INSERT INTO DataNasabah (AccountNumber, Nama, Alamat) VALUE( '%s', '%s', '%s')";
			sql = String.format(sql, AccountNumber, Nama, Alamat);

			// simpan Data
			stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void updateData() {
		try {

			// ambil input dari user
			System.out.print("ID yang mau diedit: ");
			int id_DataNasabah = Integer.parseInt(input.readLine());
			System.out.print("AccountNumber: ");
			String AccountNumber = input.readLine().trim();
			System.out.print("Nama: ");
			String Nama = input.readLine().trim();
			System.out.print("Alamat: ");
			String Alamat = input.readLine().trim();

			// query update
			String sql = "UPDATE DataNasabah SET AccountNumber='%s', Nama='%s', Alamat='%s' WHERE id_DataNasabah=%d";
			sql = String.format(sql, AccountNumber, Nama, Alamat, id_DataNasabah);

			// update data buku
			stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void deleteData() {
		try {

			// ambil input dari user
			System.out.print("ID yang mau dihapus: ");
			int id_DataNasabah = Integer.parseInt(input.readLine());

			// buat query hapus
			String sql = String.format("DELETE FROM DataNasabah WHERE id_DataNasabah=%d", id_DataNasabah);

			// hapus data
			stmt.execute(sql);

			System.out.println("Data telah terhapus...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}