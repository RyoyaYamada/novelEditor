package controller;

import component.IndexCell;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class IndexCellFactory implements Callback<TreeView<String>, TreeCell<String>>{

	public IndexCellFactory() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public IndexCell call(TreeView<String> param) {
		IndexCell cell = new IndexCell();
		return cell;
	}

}
