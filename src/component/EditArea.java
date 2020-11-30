package component;

import javafx.scene.control.TextArea;
import model.Part;

public class EditArea extends TextArea {
	
	public EditArea() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EditArea(String text) {
		super(text);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public  void saveContext(Part part) {
		part.setContext(this.getText());
	}

}
