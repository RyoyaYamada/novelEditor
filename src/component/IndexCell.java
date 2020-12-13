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
}
