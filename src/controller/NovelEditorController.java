package controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import component.EditArea;
import component.EditableLabel;
import component.NovelIndex;
import consts.Strings;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import manager.NovelIOManager;
import model.IndexItem;
import model.Part;
import model.Title;

public class NovelEditorController implements Initializable{

	@FXML
	private EditArea editArea;

	@FXML
	private NovelIndex novelIndex;
	
	@FXML
	private EditableLabel titleField;

	@FXML
	private ComboBox<String> fontSizeCombo;
	
	@FXML
	private ComboBox<String> fontCombo;
	
	private File saveFile = null;

	private NovelIOManager manager = new NovelIOManager();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		editArea.setWrapText(false);
		initToolBar();

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
	
	private void initToolBar() {
		titleField.setRootItem(novelIndex.getRoot());
		titleField.setText(Strings.defaultTitle.getString());
		Double fontsize = editArea.getFont().getSize();
		fontSizeCombo.getItems().addAll(new String[]{"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"});
		fontSizeCombo.setEditable(true);
		fontSizeCombo.getSelectionModel().select(String.valueOf(fontsize.intValue()));

		List<String> familyName = Font.getFamilies();
		fontCombo.getItems().addAll(familyName);
		fontCombo.getSelectionModel().select(editArea.getFont().getFamily());
	}

	@FXML
	public void newTitle() {
		novelIndex.init();
		titleField.setText(Strings.defaultTitle.getString());
		saveFile = null;
	}

	@FXML
	public void load() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Title");
		fileChooser.getExtensionFilters().addAll(NovelIOManager.novelFilter, NovelIOManager.allFilter);
		File novelFile = fileChooser.showOpenDialog(null);

		if (novelFile != null) {
			Title root = manager.load(novelFile);
			novelIndex.setRoot(root);
			titleField.setText(root.getTitle());
			saveFile = novelFile;
		}
	}

	@FXML
	public void save() {
		 IndexItem selectedItem = (IndexItem) novelIndex.getSelectionModel().getSelectedItem();
		 if (selectedItem instanceof Part) {
			 editArea.saveContext((Part) selectedItem);
		 }
		saveFile = manager.save(saveFile, (Title) novelIndex.getRoot());
	}

	@FXML
	public void saveAs() {
		IndexItem selectedItem = (IndexItem) novelIndex.getSelectionModel().getSelectedItem();
		 if (selectedItem instanceof Part) {
			 editArea.saveContext((Part) selectedItem);
		 }
		manager.save(null, (Title) novelIndex.getRoot());
	}

	@FXML
	public void export() {
		IndexItem selectedItem = (IndexItem) novelIndex.getSelectionModel().getSelectedItem();
		 if (selectedItem instanceof Part) {
			 editArea.saveContext((Part) selectedItem);
		 }
		 manager.export(selectedItem);
	}
	
	@FXML
	public void exportAll() {
		manager.export((IndexItem) novelIndex.getRoot());
	}
	
	@FXML
	public void close() {
		Platform.exit();
	}
	
	@FXML
	public void toggleWrap() {
		editArea.setWrapText(!editArea.isWrapText());
	}

}
