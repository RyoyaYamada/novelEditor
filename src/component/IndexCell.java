package component;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class IndexCell extends TreeCell<String> {
	
	private TextField titleArea;
	
	public IndexCell() {
		super();
	}

	@Override
	public void startEdit() {
		// TODO 自動生成されたメソッド・スタブ
		super.startEdit();
		
		if (titleArea == null) {
			createTtitleArea();
		} else {
			titleArea.setText(getString());
		}
		setText(null);
		setGraphic(titleArea);
		titleArea.selectAll();
	}

	
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			// 何のデータも結びついていなければ、何も表示しない
			setText(null);
			setGraphic(null);
		} else {
			// 何らかのデータが結びついている
			if (isEditing()) {
				// 編集中に再描画された場合は、textFieldを表示
				if (titleArea != null) {
					titleArea.setText(getString());
				}
				setText(null);
				setGraphic(titleArea);
			} else {
				// 編集中以外はlabelを表示
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
			}
		}
	}

	@Override
	public void cancelEdit() {
		// TODO 自動生成されたメソッド・スタブ
		super.cancelEdit();
		setText(getItem());
		setGraphic(getTreeItem().getGraphic());
	}
	
	private void createTtitleArea() {
		titleArea = new TextField(getString());
		titleArea.setOnKeyReleased((KeyEvent t) -> {
			if (t.getCode() == KeyCode.ENTER) {
				commitEdit(titleArea.getText());
			} else if (t.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
		});
	}
	
	private String getString() {
        return getItem() == null ? "" : getItem();
    }
	
}
