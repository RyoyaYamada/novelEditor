package component;

import javafx.scene.control.cell.TextFieldTreeCell;
import model.IndexItem;
import model.Part;
import model.Section;

public class IndexCell extends TextFieldTreeCell<String> {


	public IndexCell() {
		super();

		TreeViewMenu popupMenu = TreeViewMenu.getInstance();
		this.setContextMenu(popupMenu);

		this.setOnContextMenuRequested(event -> {
			IndexItem selectedItem = (IndexItem) this.getTreeItem();
			popupMenu.setPopupedNode(selectedItem);
			if (selectedItem instanceof Section) {
				popupMenu.showWithSection();
			} else if (selectedItem instanceof Part) {
				popupMenu.showWithPart();
			} else {
				popupMenu.showWithNonSelected();
			}
		});

	}


	public IndexItem getIndexItem() {
		return (IndexItem) super.getTreeItem();
	}

	public NovelIndex getNovelIndex() {
		return (NovelIndex) super.getTreeView();
	}


//	@Override
//	public void startEdit() {
//		// TODO 自動生成されたメソッド・スタブ
//		super.startEdit();
//
//		if (titleArea == null) {
//			createTtitleArea();
//		} else {
//			titleArea.setText(getString());
//		}
//		setText(null);
//		setGraphic(titleArea);
//		titleArea.selectAll();
//	}
//
//
//	@Override
//	protected void updateItem(String item, boolean empty) {
//		super.updateItem(item, empty);
//
//		if (empty) {
//			// 何のデータも結びついていなければ、何も表示しない
//			setText(null);
//			setGraphic(null);
//		} else {
//			// 何らかのデータが結びついている
//			if (isEditing()) {
//				// 編集中に再描画された場合は、textFieldを表示
//				if (titleArea != null) {
//					titleArea.setText(getString());
//				}
//				setText(null);
//				setGraphic(titleArea);
//			} else {
//				// 編集中以外はlabelを表示
//				setText(getString());
//				setGraphic(getTreeItem().getGraphic());
//			}
//		}
//	}
//
//	@Override
//	public void cancelEdit() {
//		// TODO 自動生成されたメソッド・スタブ
//		super.cancelEdit();
//		setText(getItem());
//		setGraphic(getTreeItem().getGraphic());
//	}
//
//	private void createTtitleArea() {
//		titleArea = new TextField(getString());
//		titleArea.setOnKeyReleased((KeyEvent t) -> {
//			if (t.getCode() == KeyCode.ENTER) {
//				commitEdit(titleArea.getText());
//			} else if (t.getCode() == KeyCode.ESCAPE) {
//				cancelEdit();
//			}
//		});
//	}
//
//	private String getString() {
//		// getItem: TreeItemのvalueを返す
//        return getItem() == null ? "" : getItem();
//    }
}
