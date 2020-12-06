package model;

import java.util.Objects;

import javafx.scene.control.TreeItem;

public class IndexItem extends TreeItem<String> {
	public IndexItem() {
		super();
	}

	/**
	 * コンストラクタ
	 *
	 * @param title　タイトル(value)
	 */
	public IndexItem(String title) {
		super(title);
	}

	public String getTitle() {
		return this.getValue();
	}

	public void setTitle(String title) {
		this.setValue(title);
	}

	public IndexItem getParentIndex() {
		return (IndexItem) super.getParent();
	}
	
	public boolean isChildOf(IndexItem draggedNode) {
		if (Objects.equals(this, draggedNode)) {
			return true;
		}
		
		for (TreeItem<String> treeItem : draggedNode.getChildren()) {
			if (isChildOf((IndexItem)treeItem)) {
				return true;
			}
		}
		return false;
	}

}
