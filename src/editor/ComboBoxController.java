package editor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxController {
	private List<JComboBox<String>> comboBoxes;

	public ComboBoxController() {
		comboBoxes = new ArrayList<JComboBox<String>>();	
	}
	
	public void addComboBox(JComboBox<String> comboBox){
		comboBoxes.add(comboBox);
	}
	
	public JComboBox<String> getComboBox(int referenceNumber){
		return comboBoxes.get(referenceNumber);
	}
	
	public void fillComboBox(int referenceNumber, List<String> wordList){
		getComboBox(referenceNumber).removeAllItems();
		for (String word : wordList) {
			getComboBox(referenceNumber).addItem(word);
		}
	}
	
	public void fillComboBox(int referenceNumber, List<String> wordList, String startingLetter){
		getComboBox(referenceNumber).removeAllItems();
		for (String word : wordList) {
			if (word.startsWith(startingLetter)) {
				getComboBox(referenceNumber).addItem(word);
			}
		}
	}

}
