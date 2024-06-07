package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import entity.Product;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		try (Socket socket = new Socket("DESKTOP-G8E3BC9", 7878);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
			while (true) {
				System.out.println("Enter category name: ");
				String name = sc.nextLine();
				out.writeUTF(name);
				out.flush();

				List<Product> list = (List<Product>) in.readObject();

				list.forEach(System.out::println);
			}
		}

	}
}
