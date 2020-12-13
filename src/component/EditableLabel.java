package component;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;

public class EditableLabel extends Label {
	private TextField editField = new TextField();
	
	private String backup = "";
	
	private TreeItem<String> root;

	public EditableLabel() {
		this("");
	}

	public EditableLabel(String text) {
		super(text);
		this.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				editField.setText(backup);
				this.setGraphic(editField);
				this.setText("");
				editField.requestFocus();
			}
		});
		
		editField.focusedProperty().addListener((observal, oldValue, newValue) -> {
			if (!newValue) {
				toLabel();
			}
		});
		
		editField.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				toLabel();
			} else if (e.getCode().equals(KeyCode.ESCAPE)) {
				editField.setText(backup);
				toLabel();
			}
		});
		
	}

	public EditableLabel(String text, Node graphic) {
		super(text, graphic);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	void toLabel() {
		this.setGraphic(null);
		this.setText(editField.getText());
		if (root != null) {
			root.setValue(editField.getText());
		}
	}

	public void setRootItem(TreeItem<String> root) {
		this.root = root;		
	}

}
