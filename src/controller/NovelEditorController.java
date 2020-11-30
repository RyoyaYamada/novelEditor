package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;

public class NovelEditorController implements Initializable{

	@FXML
	private TreeView<String> novelIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		TreeItem<String> rootItem = new TreeItem<String>("Root");
		
//		rootItem.getChildren().addAll(
//				new TreeItem<String>("Red"), new TreeItem<String>("Green"), new TreeItem<String>("Yello"), new TreeItem<String>("Blue")
//				);
//		
//		novelIndex.setRoot(rootItem);
	}
	
}
