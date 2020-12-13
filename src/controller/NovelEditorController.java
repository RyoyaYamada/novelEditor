package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import component.EditArea;
import component.NovelIndex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import manager.NovelFileManager;
import model.IndexItem;
import model.Part;
import model.Title;

public class NovelEditorController implements Initializable{

	@FXML
	private EditArea editArea;

	@FXML
	private NovelIndex novelIndex;
	
	private File saveFile = null;
	
	private NovelFileManager manager = new NovelFileManager();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		novelIndex.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> leftItem,
					TreeItem<String> selectedItem) {
				if (leftItem instanceof Part) {
					editArea.saveContext((Part)leftItem);
				}

				if (selectedItem instanceof Part) {
					editArea.setText(((Part)selectedItem).getContext());
				}
			}
		});
	}
	
	@FXML
	public void newTitle() {
		novelIndex.init();
		saveFile = null;
	}
	
	@FXML
	public void load() {
		Title root = manager.load();
		if (root == null) {
		} else {
			novelIndex.setRoot(root);
		}
	}
	
	@FXML
	public void save() {
		 IndexItem selectedPart = (IndexItem) novelIndex.getSelectionModel().getSelectedItem();
		 if (selectedPart instanceof Part) {
			 editArea.saveContext((Part) selectedPart);
		 }
		saveFile = manager.save(saveFile, (Title) novelIndex.getRoot());
	}
	
	@FXML
	public void saveAs() {
		IndexItem selectedPart = (IndexItem) novelIndex.getSelectionModel().getSelectedItem();
		 if (selectedPart instanceof Part) {
			 editArea.saveContext((Part) selectedPart);
		 }
		manager.save(null, (Title) novelIndex.getRoot());
	}

}
