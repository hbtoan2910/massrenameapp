package ryanhuynh.learn.java;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//Import log4j classes, configuration file on the classpath can be in JSON, XML, YAML, or properties format
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Color;

import java.nio.file.StandardCopyOption;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(MainUI.class);
	private JPanel contentPane;
	private File selectedFolder = null;
	private JTextField userInput;
	String user_input = "";
	String selected_item = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			// Set Windows Look and Feel, at the moment only 5 below available
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//this
			// can not set BG color for button hmm
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {

		// Load your custom icon
		ImageIcon winAppIcon = new ImageIcon(getClass().getResource("images/tomato.png")); //ImageIcon, MUST use getClass().getResource(String name)
		// Set the custom icon for the frame
		setIconImage(winAppIcon.getImage());

		setTitle("RyanHuynhApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Load the original icon
		ImageIcon icon = new ImageIcon(getClass().getResource("images/dog.jpg")); // better us relative path

		// Get the original image from the icon
		Image originalImage = icon.getImage();

		// Define the new dimensions for the resized icon
		int width = 300;  // New width
		int height = 300; // New height

		// Resize the original image to the new dimensions
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		// Create a new ImageIcon from the resized image
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

		JLabel imgLabel = new JLabel(resizedIcon);
		imgLabel.setBounds(10, 11, 240, 245);
		contentPane.add(imgLabel);

		JButton fBrowseBtn = new JButton("FROM folder");
		fBrowseBtn.setBounds(277, 11, 99, 23);
		contentPane.add(fBrowseBtn);

		// Add textarea to display path
		JTextArea txtArea_1 = new JTextArea();
		txtArea_1.setBounds(278, 35, 311, 40);
		txtArea_1.setFont(getFont());
		txtArea_1.setBackground(new java.awt.Color(211, 211, 211));
		txtArea_1.setLineWrap(true);
		contentPane.add(txtArea_1);

		fBrowseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select a folder");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					// Selected folder
					selectedFolder = fileChooser.getSelectedFile();

					// Remove previous components
					for (Component component : contentPane.getComponents()) {
						if (component.getName() == "txtArea_1") {
							contentPane.remove(component);
						}
					}
					txtArea_1.setText(selectedFolder.getAbsolutePath());
				}
			}
		});

		JButton toBrowseBtn = new JButton("TO folder");
		toBrowseBtn.setBounds(277, 80, 99, 23);
		contentPane.add(toBrowseBtn);

		// Add textarea to display path
		JTextArea txtArea_2 = new JTextArea();
		txtArea_2.setBounds(278, 104, 311, 40);
		txtArea_2.setFont(getFont());
		txtArea_2.setBackground(new java.awt.Color(211, 211, 211));
		txtArea_2.setLineWrap(true);
		contentPane.add(txtArea_2);

		toBrowseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select a folder");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					// Selected folder
					selectedFolder = fileChooser.getSelectedFile();

					for (Component component : contentPane.getComponents()) {
						if (component.getName() == "txtArea_2") {
							contentPane.remove(component);
						}
					}
					txtArea_2.setText(selectedFolder.getAbsolutePath());
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Naming convention:");
		lblNewLabel_1.setBounds(278, 149, 109, 14);
		contentPane.add(lblNewLabel_1);

		userInput = new JTextField();
		//		userInput.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				user_input = userInput.getText();
		//			}
		//		});
		userInput.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				user_input = userInput.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				user_input = userInput.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				user_input = userInput.getText();
			}

		});
		userInput.setBounds(278, 165, 311, 20);
		userInput.setColumns(10);
		contentPane.add(userInput);

		JLabel lblNewLabel_2 = new JLabel("Choose file extension:");
		lblNewLabel_2.setBounds(278, 193, 109, 14);
		contentPane.add(lblNewLabel_2);

		String[] items = { "", "jpg", "jpeg", "png" };
		JComboBox<String> comboBox = new JComboBox<String>(items);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_item = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(542, 188, 47, 22);
		contentPane.add(comboBox);

		JButton renameBtn = new JButton("Mass-renaming");
		renameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				logger.debug("From: " + txtArea_1.getText() + " - To: " + txtArea_2.getText() + " - Name: " + user_input
						+ " - Type: " + selected_item);
				String result = rename(txtArea_1.getText(), txtArea_2.getText(), user_input, selected_item);

				// Create and display the pop-up window
				JFrame popupFrame = new JFrame("Notification");
				popupFrame.getContentPane().setLayout(new BorderLayout());

				JLabel messageLabel = new JLabel(result);
				messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
				popupFrame.getContentPane().add(messageLabel, BorderLayout.CENTER);

				popupFrame.setSize(500, 100);
				popupFrame.setLocationRelativeTo(null); // Center the pop-up window
				popupFrame.setVisible(true);
				ImageIcon popupIcon = new ImageIcon(getClass().getResource("images/tomato.png"));
				popupFrame.setIconImage(popupIcon.getImage());
			}
		});
		renameBtn.setBounds(278, 216, 109, 23);
		renameBtn.setBackground(new Color(46, 139, 87));
		renameBtn.setForeground(Color.WHITE);
		renameBtn.setOpaque(true);
		contentPane.add(renameBtn);

		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index != 0) {
					comboBox.setSelectedIndex(0);
				}
				txtArea_1.setText("");
				txtArea_2.setText("");
				userInput.setText("");
			}
		});
		refreshBtn.setOpaque(true);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setBackground(Color.BLUE);
		refreshBtn.setBounds(397, 216, 109, 23);
		contentPane.add(refreshBtn);
	}

	public static String rename(String input, String output, String name, String type) {
		File[] files = {};
		File renameFromFolder = new File(input);
		File renameToFolder = new File(output);
		try {
			if (renameFromFolder.isDirectory() && renameToFolder.isDirectory()) {
				if (renameToFolder.listFiles() != null && renameToFolder.listFiles().length == 0) {

					if (name.isEmpty()) {
						logger.error("Please input name convention.");
						return "Please input name convention.";
					}
					if (type.isEmpty()) {
						logger.error("Please select file extension.");
						return "Please select file extension.";
					}

					File archiveFolder = new File("C:\\Users\\RYANHUYNHPC\\Downloads\\Archive");
					if (!archiveFolder.exists()) {
						boolean success = archiveFolder.mkdir();
						if (!success) {
							logger.error("Failed to create directory: " + archiveFolder.getPath());
							throw new IOException("Failed to create directory: " + archiveFolder.getPath());
						}

						files = renameFromFolder.listFiles();

						if (files != null && files.length > 0) {
							for (int i = 0; i < files.length; i++) {
								File file = files[i];
								if (file.isFile()) {

									/*** ARCHIVE FIRST SECTION ***/
									// As default, if rename-transaction is successful, old files will be renamed
									// and also moved to new folder
									// So we do this step to create a copy of origin files to a new folder before
									// execute renameTo()
									File archiveFile = new File(
											archiveFolder.getAbsolutePath() + "\\" + file.getName());
									Files.copy(file.toPath(), archiveFile.toPath(),
											StandardCopyOption.REPLACE_EXISTING);
									logger.debug("All files have been archived properly.");

									/*** RENAME SECTION ***/
									File newFile = new File(renameToFolder, name + "_" + (i + 1) + "." + type);
									if (file.renameTo(newFile)) {
										logger.debug("File: " + file.getName() + " was renamed properly into "
												+ renameToFolder.getAbsolutePath() + " folder with new name: "
												+ newFile.getName());
									} else {
										logger.error("Renaming process failed.");
										return "Renaming process failed.";
									}

								} else {
									logger.error("Not a proper file.");
									return "Not a proper file.";
								}
							}
						}
					} else {
						logger.error("This archive folder already existed. Please remove it to proceed.");
						return "This archive folder already existed. Please remove it to proceed.";
					}
				} else {
					logger.error("Output folder must be empty before proceeding.");
					return "Output folder must be empty before proceeding.";
				}

			} else {
				logger.error("Either Input or Output is not a Folder.");
				return "Either Input/Output src is not a valid folder...";
			}

		} catch (Exception e) {
			logger.error("Error occurred: " + e.getMessage());
			e.printStackTrace();
		}

		return files.length + " files had been rename properly.";
	}
}
