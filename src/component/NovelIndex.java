package component;

import controller.IndexCellFactory;
import javafx.scene.control.TreeView;
import model.IndexItem;
import model.Part;
import model.Section;

public class NovelIndex extends TreeView<String> {

	public NovelIndex() {
		super();

		this.setShowRoot(false);
		Part part1 = new Part("part1");
		Section root = new Section("root", new Section("Section1",  part1));

		Part part2 = new Part("part2");
		Part part3 = new Part("part3");

		Section sec2 = new Section("sec2");
		sec2.addPart(part2);
		sec2.addPart(part3);

		root.addSection(sec2);

		this.setRoot(root);
		expandNovelIndex(part1);

		TreeViewMenu.createInstance(this);


		this.setEditable(true);
		this.setCellFactory(new IndexCellFactory());
	}

	public void expandNovelIndex(IndexItem selectedNode) {
		super.getSelectionModel().select(selectedNode);
	}
}
