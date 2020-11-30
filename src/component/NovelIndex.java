package component;

import javafx.scene.control.TreeView;

public class NovelIndex extends TreeView<String> {

	public NovelIndex() {
		super();

		this.setShowRoot(false);
		Part part1 = new Part("part1");
		Section root = new Section("root", new Section("Section1",  part1));
		this.setRoot(root);
		this.getSelectionModel().select(part1);;
		
	}
}
