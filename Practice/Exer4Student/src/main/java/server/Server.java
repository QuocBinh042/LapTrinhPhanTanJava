package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.StudentDAO;
import dao.impl.StudentImpl;
import entity.Student;

public class Server {

	public static void main(String[] args) {
		
		try(ServerSocket server = new ServerSocket(1234)){
			System.out.println("Server is running on port 1234");
			
			while(true) {
				
				Socket client = server.accept();
				System.out.println("Client connected");
				
				System.out.println("Client IP: " + client.getInetAddress().getHostName());
				
				// Create a new thread to handle the client
				
				Server temp = new Server();
				Thread thread = new Thread(temp.new ClientHandler(client));
				thread.start();
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class ClientHandler implements Runnable{
		
		private StudentDAO studentDAO ;
		
		private Socket socket;
		

		public ClientHandler(Socket socket) {
			super();
			this.socket = socket;
			this.studentDAO = new StudentImpl();
		}


		@Override
		public void run() {
			
			try {
				
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				
				int choose = in.readInt();
				switch (choose) {
				case 1:
					String title = in.readUTF();
					List<Student> students = studentDAO.findStudentsEnrolledInCourse(title);
					
					out.writeObject(students);
					break;
				case 2:
					break;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
