package component;

import controller.IndexCellFactory;
import javafx.scene.control.TreeView;
import model.IndexItem;
import model.Novel;
import model.Part;
import model.Section;

public class NovelIndex extends TreeView<String> {

	public NovelIndex() {
		super();

		this.setShowRoot(true);
		Part part1 = new Part("part1");
		Novel root = new Novel("root", new Section("Section1",  part1));

		Part part2 = new Part("part2");
		Part part3 = new Part("part3");

		Section sec2 = new Section("sec2");
		sec2.addPart(part2);
		sec2.addPart(part3);

		root.addSection(sec2);

		this.setRoot(root);
		expandNovelIndex(part1);
		TreeViewMenu popupMenu = new TreeViewMenu(this);
		this.setContextMenu(popupMenu);
		
		this.setOnContextMenuRequested(event -> {
			IndexItem selectedItem = (IndexItem) this.getSelectionModel().getSelectedItem();
			popupMenu.setPopupedNode(selectedItem);
			if (selectedItem instanceof Novel) {
				popupMenu.showWithNonSelected();
			} else if (selectedItem instanceof Section) {
				popupMenu.showWithSection();
			} else if (selectedItem instanceof Part) {
				popupMenu.showWithPart();
			} else {
				popupMenu.showNoAll();
			}
		});
		
		
		this.setEditable(true);
		this.setCellFactory(new IndexCellFactory());
	}

	public void expandNovelIndex(IndexItem selectedNode) {
		super.getSelectionModel().select(selectedNode);
	}
}
