package model;

import javafx.scene.control.TreeItem;

public class IndexNode extends TreeItem<String> {
	public IndexNode() {
		super();
	}


	public IndexNode(String title) {
		super(title);
	}

	public String getTitle() {
		return this.getValue();
	}

	public void setTitle(String title) {
		this.setValue(title);
	}

}
