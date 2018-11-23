package editor;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnector dbConnector = new DBConnector();
//					dbConnector.addWordListsToDB(getWordList("CSVPhrases1.txt"), 
//							getWordList("CSVPhrases2.txt"), 
//							getWordList("CSVPhrases3.txt"));
					
					
					Window frame = new Window(dbConnector);
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
	public Window(DBConnector dbConnector) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Shakespearean Insult Generator");
		setBounds(100, 100, 1000, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		panel.setLayout(null);

		//Create ComboBoxes
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 40, 200, 40);
		comboBox.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(220, 40, 200, 40);
		comboBox_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(comboBox_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(440, 40, 200, 40);
		comboBox_2.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(comboBox_2);

		//Create labels
		JLabel lblStarterWord = new JLabel("Starter word");
		lblStarterWord.setBounds(10, 11, 200, 28);
		lblStarterWord.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblStarterWord);

		JLabel lblSecondWord = new JLabel("Second word");
		lblSecondWord.setBounds(220, 11, 200, 28);
		lblSecondWord.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblSecondWord);

		JLabel lblThirdWord = new JLabel("Third word");
		lblThirdWord.setBounds(440, 11, 200, 28);
		lblThirdWord.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblThirdWord);

		//Create Button
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(660, 7, 268, 76);
		btnGenerate.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(btnGenerate);

		//Create Text Area
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 100, 928, 190);
		textArea.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(textArea);
		
		//Creating new ComboBox Controller
		ComboBoxController cmbC = new ComboBoxController();
		cmbC.addComboBox(comboBox);
		cmbC.addComboBox(comboBox_1);
		cmbC.addComboBox(comboBox_2);
		
		//Importing word list files
		List<String> wordList0 = dbConnector.getWordList(1);
		List<String> wordList1 = dbConnector.getWordList(2);
		List<String> wordList2 = dbConnector.getWordList(3);
		
		cmbC.fillComboBox(0, wordList0);
		cmbC.fillComboBox(1, wordList1);
		cmbC.fillComboBox(2, wordList2);
		
		//ComboBox listener to filter following ComboBoxes by first letter selection made in first box
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//perform comboBoxRepopulate with starting letter
				String selectedValue = String.valueOf(comboBox.getSelectedItem());
				String selectedLetter = selectedValue.substring(0, 1);
				cmbC.fillComboBox(1, wordList1, selectedLetter);
				cmbC.fillComboBox(2, wordList2, selectedLetter);
			}
		});
		
		//button to generate insult
		btnGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String word1 = String.valueOf(comboBox.getSelectedItem());
				String word2 = String.valueOf(comboBox_1.getSelectedItem());
				String word3 = String.valueOf(comboBox_2.getSelectedItem());
				String insult = word1 + " " + word2 + " " + word3;
				textArea.append(insult + "\n");
				dbConnector.saveInsult(word1, word2, word3, insult);
			}
		});

	}

//	private static List<String> getWordList(String fileName) {
//		List<String> wordList = Arrays.asList(wordsString(fileName).split(","));
//		return wordList;
//	}
//	
//	private static String wordsString(String fileName){
//		String text = null;
//		try {
//			File file = new File(fileName);
//			Scanner sc = new Scanner(file);
//			text = (String) sc.nextLine();
//			sc.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		text = text.replaceAll(" ", "");
//		text = text.replaceAll("\"", "");
//		return text;
//	}
}
