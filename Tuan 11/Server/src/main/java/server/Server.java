package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.ProductDao;
import entity.Product;

public class Server {
	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(7878);) {
			System.out.println("Ready...");

			while (true) {
				Socket socket = server.accept();
				Server temp = new Server();
				Handler task = temp.new Handler(socket);
				Thread thread = new Thread(task);
				thread.start();
			}
		}
	}

	private class Handler implements Runnable {

		private Socket socket;
		private ProductDao dao;

		public Handler(Socket socket) {
			this.socket = socket;
			dao = new ProductDao();
		}

		@Override
		public void run() {
			try (DataInputStream in = new DataInputStream(socket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
				while (true) {
					String name = in.readUTF();
					System.out.println(name);
					List<Product> products = dao.listOfProductsByCategory(name);

					out.writeObject(products);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
