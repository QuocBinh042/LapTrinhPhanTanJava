package iuh.fit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyNotepad extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	private JMenuItem newMenu;
	private JMenuItem openMenu;
	private JMenuItem saveMenu;
	private JMenuItem printMenu;
	private JMenuItem exitMenu;
	private JTextArea ta;
	private JScrollPane sc;
	private Thread thread;

	public MyNotepad() {

		setTitle("Notepad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(500, 300);
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600, 400));

		JMenuBar menuBar;
		setJMenuBar(menuBar = new JMenuBar());
		JMenu fileMenu, editMenu, helpMenu;
		menuBar.add(fileMenu = new JMenu("File"));
		menuBar.add(editMenu = new JMenu("Edit"));
		menuBar.add(helpMenu = new JMenu("Help"));
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		helpMenu.setMnemonic('H');

		fileMenu.add(newMenu = new JMenuItem("New", 'N'));
		fileMenu.add(new JSeparator());
		newMenu.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(openMenu = new JMenuItem("Open", 'O'));
		openMenu.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(saveMenu = new JMenuItem("Save", 'S'));
		saveMenu.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(new JSeparator());
		fileMenu.add(printMenu = new JMenuItem("Print", 'P'));
		printMenu.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(new JSeparator());
		fileMenu.add(exitMenu = new JMenuItem("Exit", 'E'));
		exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

		Container container = getContentPane();

		container.add(sc = new JScrollPane(ta = new JTextArea()));
		ta.setLineWrap(true);
		sc.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel pnl;
		container.add(pnl = new JPanel(), BorderLayout.SOUTH);
		pnl.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pnl.setLayout(new BorderLayout());
//		pnl.add(new JLabel("Ready..."));
		pnl.add(progressBar = new JProgressBar());
		progressBar.setStringPainted(true);

		progressBar.addPropertyChangeListener(ev -> {
			if ("progress".equals(ev.getPropertyName())) {
				progressBar.setValue((Integer) ev.getNewValue());
			}
		});

		newMenu.addActionListener(this);
		openMenu.addActionListener(this);
		saveMenu.addActionListener(this);
		printMenu.addActionListener(this);
		exitMenu.addActionListener(this);
	}

	public static void main(String[] args) {
		new MyNotepad().setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(exitMenu))
			System.exit(0);
		else if(o.equals(openMenu)) {

			JFileChooser fileChooser = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir"));
			fileChooser.setCurrentDirectory(workingDirectory);
			fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt","text"));
			//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
			int value = fileChooser.showOpenDialog(this);

			if(value == JFileChooser.APPROVE_OPTION) {
				File fileName = fileChooser.getSelectedFile();
				//				String choosing = JOptionPane.showInputDialog(this, new String[] {"Without thread", "With thread"},  "With thread");
				String choosing = (String) JOptionPane.showInputDialog(this, "Select your choice", "Without or with thread", JOptionPane.PLAIN_MESSAGE, null,new String[] {"Without thread", "With thread"},  "With thread");
				if(choosing != null) {
					newMenu.doClick();
					if(choosing.equals("Without thread"))
						withoutThread(fileName); //small data
					else { //big data
//						LoadingWithThread task = new LoadingWithThread(fileName.getAbsolutePath(), ta);
//						Thread thread = new Thread(task);
//						thread.start();
						LoadingWorker task = new LoadingWorker(fileName, ta);
						task.execute();
					}
				}
			}
		}
		else if(o.equals(newMenu)) {
			if(thread != null) {
				thread.interrupt();
				ta.setText("");
				ta.requestFocus();
			}
		}
	}

	private void withoutThread(File fileName) {
		Scanner sc = null;

		try {
			sc = new Scanner(new FileReader(fileName));
			String line = "";
			int i = 0;
			while(sc.hasNextLine()) {

				line = sc.nextLine();
				i++;
				ta.append(i + ":" +line);
				ta.append("\n");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (Exception e2) {
			e2.printStackTrace();
		}
		finally {
			sc.close();
		}
	}

	class LoadingWorker extends SwingWorker<Long, Integer>{
		private File filename;
		private JTextArea ta;

		public LoadingWorker(File filename, JTextArea ta) {
			this.filename = filename;
			this.ta = ta;
		}

		@Override
		protected Long doInBackground() throws Exception {
			long lines = Files.lines(filename.toPath()).count();
			try(
					BufferedReader br = new BufferedReader(new FileReader(filename));
			){

				int i = 0;
				while (br.ready()) {
					String line = br.readLine();
					i++;
					ta.append(i + ":" +line);
					ta.append("\n");

					int percent = (int) (i * 100 / lines);
					progressBar.setValue(percent);

					publish(i);
				}

			}catch (IOException e) {
				e.printStackTrace();
			}
			return lines;
		}

		@Override
		protected void process(List<Integer> chunks) {
			System.out.println(chunks.get(chunks.size() - 1));
		}

		@Override
		protected void done() {
            long lines = 0l;
            try {
                lines = get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null, "Finihed! Lines:" + lines, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
	}

}



class LoadingWithThread implements Runnable{
	private String file; // File txt
	private JTextArea ta;

	public LoadingWithThread(String file, JTextArea ta) {
		this.file = file;
		this.ta = ta;
	}

	@Override
	public void run() {
		try(
				BufferedReader br = new BufferedReader(new FileReader(file));
				){

			int i = 0;
			while (br.ready()) {
				String line = br.readLine();
				i++;
				ta.append(i + ":" +line);
				ta.append("\n");
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}



// IO
// Stardard IO: System.in, System.out, System.err
// Byte Stream: InputStream, OutputStream --> BufferedInputStream, BufferedOutputStream
// Character Stream: Reader, Writer --> BufferedReader, BufferedWriter
// Primitive Stream: DataInputStream, DataOutputStream
	// Primitive Type: byte, short, int, long, float, double, boolean, char
	// String
// Object Stream: ObjectInputStream, ObjectOutputStream
