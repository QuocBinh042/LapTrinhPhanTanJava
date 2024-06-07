package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Student;

public class Client {
	public static void main(String[] args) {

		try (Socket client = new Socket("DESKTOP-K2I7FKM", 1801);
				Scanner sc = new Scanner(System.in);) {

			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());

			System.out.println("Connected to server");

			int choose = 0;

			while (true) {

				System.out.println(
						"Enter your choice: \n1.Search students enrollment in course\n2.Search students enrollment in year");
				choose = sc.nextInt();
				out.writeInt(choose);

				switch (choose) {
				case 1:
					sc.nextLine();
					System.out.println("Enter course title: ");
					String title = sc.nextLine();
					out.writeUTF(title);
					out.flush();

//					Nhan ds Student

					List<Student> students = (List<Student>) in.readObject();
					students.forEach(System.out::println);

					break;

				case 2:

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
