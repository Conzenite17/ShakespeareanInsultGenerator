package editor;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
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

		List<JComboBox<String>> comboBoxes = new ArrayList<JComboBox<String>>();
		comboBoxes.add(comboBox);
		comboBoxes.add(comboBox_1);
		comboBoxes.add(comboBox_2);
		
		String words1 = copyTxt("CSVPhrases1.txt");
		String words2 = copyTxt("CSVPhrases2.txt");
		String words3 = copyTxt("CSVPhrases3.txt");
		
		List<String> lst1 = Arrays.asList(words1.split(","));
		List<String> lst2 = Arrays.asList(words2.split(","));
		List<String> lst3 = Arrays.asList(words3.split(","));

		List<List<String>> wordLists = new ArrayList<>();
		wordLists.add(lst1);
		wordLists.add(lst2);
		wordLists.add(lst3);
		
		ComboboxPopulator cmbP = new ComboboxPopulator(comboBoxes, wordLists);
		cmbP.populate();

	}

	private String copyTxt(String fileName){
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
