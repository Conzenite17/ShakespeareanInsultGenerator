package editor;

import java.util.List;
import javax.swing.JComboBox;

public class ComboboxPopulator {
	private List<JComboBox<String>> comboBoxes;
	private List<List<String>> wordLists;

	public ComboboxPopulator(List<JComboBox<String>> comboBoxes, List<List<String>> wordLists) {
		this.comboBoxes = comboBoxes;
		this.wordLists = wordLists;
	}
	
	public void populate(){
		for (int i=0 ; i < wordLists.get(0).size(); i++) {
			comboBoxes.get(0).addItem(wordLists.get(0).get(i));
		}
		for (int i=0 ; i < wordLists.get(1).size(); i++) {
			comboBoxes.get(1).addItem(wordLists.get(1).get(i));
		}
		for (int i=0 ; i < wordLists.get(2).size(); i++) {
			comboBoxes.get(2).addItem(wordLists.get(2).get(i));
		}
	}

}
