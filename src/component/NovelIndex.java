package component;

import controller.IndexCellFactory;
import javafx.scene.control.TreeView;
import model.Part;
import model.Section;

public class NovelIndex extends TreeView<String> {

	public NovelIndex() {
		super();

		this.setShowRoot(false);
		Part part1 = new Part("part1");
		Section root = new Section("root", new Section("Section1",  part1));
		this.setRoot(root);
		this.getSelectionModel().select(part1);
		this.setEditable(true);
		this.setCellFactory(new IndexCellFactory());
	}
}
