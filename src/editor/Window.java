package editor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Shakespearean Insult Generator");
		setBounds(100, 100, 500, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 25, 100, 20);
		contentPane.add(comboBox);

		JLabel lblStarterWord = new JLabel("Starter word");
		lblStarterWord.setBounds(10, 11, 100, 14);
		contentPane.add(lblStarterWord);

		JLabel lblSecondWord = new JLabel("Second word");
		lblSecondWord.setBounds(120, 11, 100, 14);
		contentPane.add(lblSecondWord);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(120, 25, 100, 20);
		contentPane.add(comboBox_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(230, 25, 100, 20);
		contentPane.add(comboBox_2);

		JLabel lblThirdWord = new JLabel("Third word");
		lblThirdWord.setBounds(230, 11, 100, 14);
		contentPane.add(lblThirdWord);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(340, 7, 134, 38);
		contentPane.add(btnGenerate);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 56, 464, 95);
		contentPane.add(textArea);

		ComboBoxController cmbC = new ComboBoxController();
		cmbC.addComboBox(comboBox);
		cmbC.addComboBox(comboBox_1);
		cmbC.addComboBox(comboBox_2);
		
		List<String> wordList0 = getWordList("CSVPhrases1.txt");
		List<String> wordList1 = getWordList("CSVPhrases2.txt");
		List<String> wordList2 = getWordList("CSVPhrases3.txt");
		
		cmbC.fillComboBox(0, wordList0);
		cmbC.fillComboBox(1, wordList1);
		cmbC.fillComboBox(2, wordList2);
		
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
		
		btnGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String word1 = String.valueOf(comboBox.getSelectedItem());
				String word2 = String.valueOf(comboBox_1.getSelectedItem());
				String word3 = String.valueOf(comboBox_2.getSelectedItem());
				String insult = word1 + " " + word2 + " " + word3;
				textArea.append(insult + "\n");
			}
		});

	}

	private List<String> getWordList(String fileName) {
		List<String> wordList = Arrays.asList(wordsString(fileName).split(","));
		return wordList;
	}

	private String wordsString(String fileName){
		String text = null;
		try {
			File file = new File(fileName);
			Scanner sc = new Scanner(file);
			text = (String) sc.nextLine();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		text = text.replaceAll(" ", "");
		text = text.replaceAll("\"", "");
		return text;
	}
}
