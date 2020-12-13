package component;

import controller.IndexCellFactory;
import javafx.scene.control.TreeView;
import model.IndexItem;
import model.Part;
import model.Section;
import model.Title;

public class NovelIndex extends TreeView<String> {

	public NovelIndex() {
		super();
		TreeViewMenu.createInstance(this);
		this.setShowRoot(true);

		init();

		this.setEditable(true);
		this.setCellFactory(new IndexCellFactory());
	}



	public void expandNovelIndex(IndexItem selectedNode) {
		super.getSelectionModel().select(selectedNode);
	}

	public void init() {
		Part part1 = new Part("part1");
		Title root = new Title("New Title", new Section("Section1",  part1));

		this.setRoot(root);
		expandNovelIndex(part1);
	}
}
