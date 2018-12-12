package DataNasabah;

import java.util.Scanner;

public class Login {
	MainProgram Prog = new MainProgram();

	public void masuk() {

		// membuat variable dan scanner
		boolean running = true;
		String userName, userPass;
		Scanner ketik = new Scanner(System.in);

		while (running) {
			// Enter Username
			System.out.println("Enter username");
			userName = ketik.nextLine();

			// Enter password
			System.out.println("Enter password");
			userPass = ketik.nextLine();

			// cek jawabannya, kalau ya maka berhenti mengulang
			if (userName.equals("anwar"))
				if (userPass.equals("***")) {
					Prog.MainProgram();
				}

		}
	}
}
