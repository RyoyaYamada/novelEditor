package controller;

import java.net.URL;
import java.util.ResourceBundle;

import component.EditArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Part;

public class NovelEditorController implements Initializable{

	private Part selectedPart;
	
	@FXML
	private EditArea editArea;
	
	@FXML
	private TreeView<String> novelIndex;
	
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
	
}
