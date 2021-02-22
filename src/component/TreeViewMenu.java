package component;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import model.IndexItem;
import model.Part;
import model.Section;

public class TreeViewMenu extends ContextMenu {

	private IndexItem popupedItem;

	private static TreeViewMenu treeViewmenu;

	private MenuItem itemRename = new MenuItem("Rename");
	private MenuItem itemNewSecton = new MenuItem("Add Section");
	private MenuItem itemNewPart = new MenuItem("Add Part");
	private MenuItem itemDelete = new MenuItem("Delete");


	public static void createInstance(NovelIndex novelIndex) {
		treeViewmenu = new TreeViewMenu(novelIndex);
	}

	public static TreeViewMenu getInstance() {
		return treeViewmenu;
	}

	private TreeViewMenu(NovelIndex novelIndex) {
		itemRename.setOnAction(even -> {
			novelIndex.edit(popupedItem);
		});

		itemNewSecton.setOnAction(event -> {
			Section selectedSection = (Section) popupedItem;

			if (selectedSection == null) {
				selectedSection = (Section) novelIndex.getRoot();
			}

			Section newSection = addSection(selectedSection);
			novelIndex.expandNovelIndex(newSection);
		});

		itemNewPart.setOnAction(event -> {
			if (!(popupedItem instanceof Section)) {
				return;
			}

			Section selectedSection = (Section) popupedItem;

			if (selectedSection == null) {
				selectedSection = (Section) novelIndex.getRoot();
			}

			Part newPart = addPart(selectedSection);
			novelIndex.expandNovelIndex(newPart);
		});

		itemDelete.setOnAction(event -> {
			IndexItem parent = popupedItem.getParentIndex();
			parent.getChildren().remove(popupedItem);
			if (parent.getChildren().size() == 0) {
				parent.getParent().getChildren().remove(parent);
			}
		});

		this.getItems().add(itemRename);
		this.getItems().add(itemNewSecton);
		this.getItems().add(itemNewPart);
		this.getItems().add(itemDelete);

	}

	public TreeViewMenu(MenuItem... items) {
		super(items);
	}

	public void setPopupedNode(IndexItem popupedItem) {
		this.popupedItem = popupedItem;
	}

	private Section addSection(Section selectedSection) {
		TextInputDialog newSectionDialog = new TextInputDialog("Please Input Section Name");
		String sectionTitle = newSectionDialog.showAndWait().orElse("");

		if (!(sectionTitle == null || sectionTitle.length() == 0)) {
			Section newSection = new Section(sectionTitle, new Part(sectionTitle + "-1"));
			selectedSection.addSection(newSection);
			return newSection;
		}
		return null;
	}

	private Part addPart(Section selectedSection) {
		TextInputDialog newPartDialog = new TextInputDialog("Please Input Part Name");
		String partTitle = newPartDialog.showAndWait().orElse("");

		if (!(partTitle == null || partTitle.length() == 0)) {
			Part newPart = new Part(partTitle);
			selectedSection.addPart(newPart);
			return newPart;
		}

		return null;
	}

	public void showWithSection() {
		itemRename.setDisable(false);
		itemNewSecton.setDisable(false);
		itemNewPart.setDisable(false);
		itemDelete.setDisable(false);
	}

	public void showWithPart() {
		itemRename.setDisable(false);
		itemNewSecton.setDisable(true);
		itemNewPart.setDisable(true);
		itemDelete.setDisable(false);
	}

	public void showWithNonSelected() {
		itemRename.setDisable(true);
		itemNewSecton.setDisable(false);
		itemNewPart.setDisable(false);
		itemDelete.setDisable(true);
	}

}
