package model;

import javafx.scene.control.TreeItem;

public class IndexNode extends TreeItem<String> {
	public IndexNode() {
		super();
	}

	/**
	 * コンストラクタ
	 *
	 * @param title　タイトル(value)
	 */
	public IndexNode(String title) {
		super(title);
	}

	public String getTitle() {
		return this.getValue();
	}

	public void setTitle(String title) {
		this.setValue(title);
	}

	public IndexNode getParentNode() {
		return (IndexNode) super.getParent();
	}

}
