package controller;

import java.util.Objects;

import component.IndexCell;
import component.NovelIndex;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import model.IndexNode;
import model.Section;

public class IndexCellFactory implements Callback<TreeView<String>, TreeCell<String>>{

	private IndexNode draggedNode;
	
	private IndexNode parent;
	
	private IndexCell dropZone = new IndexCell();
	
	/**
	 *
	 */
	private static final DataFormat JAVA_FORMAT = new DataFormat("application/x-java-serialized-object");
	/**
	 *
	 */
//	private static final String DROP_HINT_STYLE = "-fx-border-color: #eea82f; -fx-border-width: 0 0 2 0; -fx-padding: 3 3 1 3";
	private static final String DROP_HINT_STYLE = "-fx-background: powderblue";
	private static final String DEFAULT_CELL_STYLE = "-fx-background: white";
	
	
	public IndexCellFactory() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public IndexCell call(TreeView<String> param) {
		IndexCell cell = new IndexCell();
		
		cell.setOnDragDetected((MouseEvent event) -> {
			dragDetect(event, cell, cell.getNovelIndex());
		});
		
		cell.setOnDragOver(event -> {
			dragOver(event, cell, cell.getNovelIndex());
		});
		
		cell.setOnDragExited(event -> {
			cell.setStyle(DEFAULT_CELL_STYLE);
			event.consume();
		});
		
		cell.setOnDragDropped(event -> {
			dragDrop(event, cell, cell.getNovelIndex());
		});
		
		cell.setOnDragDone(event -> {
			clearDropLocation();
			if (parent.getChildren().size() == 0) {
				parent.getParent().getChildren().remove(parent);
			}
		});
			
		return cell;
	}
	
	private void dragDetect(MouseEvent event, IndexCell treeCell, NovelIndex novelIndex) {
		
		draggedNode = treeCell.getIndexNode();
		
		if (draggedNode == null) {
			return;
		}
		
		parent = draggedNode.getParentNode();
		
		if (parent == null) {
			return;
		}
		
		Dragboard dragboard = treeCell.startDragAndDrop(TransferMode.MOVE);
		
		ClipboardContent content = new ClipboardContent();
		content.put(JAVA_FORMAT, draggedNode.getValue());
		dragboard.setContent(content);
		dragboard.setDragView(treeCell.snapshot(null, null));
		event.consume();
	}
	
	private void dragOver(DragEvent event, IndexCell treeCell, NovelIndex novelIndex) {
		if (!event.getDragboard().hasContent(JAVA_FORMAT)) {
			return;
		}
		
		IndexNode dragOverNode = treeCell.getIndexNode();
		
		if (draggedNode == null || dragOverNode == null || dragOverNode == draggedNode) {
			clearDropLocation();
			return;
		}
		
		event.acceptTransferModes(TransferMode.MOVE);
		if (!Objects.equals(dropZone, treeCell)) {
			clearDropLocation();
			this.dropZone = treeCell;
			dropZone.setStyle(DROP_HINT_STYLE);
		}
		
	}

	private void dragDrop(DragEvent event, IndexCell treeCell,  NovelIndex novelIndex) {
		Dragboard dragboard = event.getDragboard();
		boolean success = false;
		
		if (!dragboard.hasContent(JAVA_FORMAT)) {
			return;
		}
		
		IndexNode dropTo = treeCell.getIndexNode();
		IndexNode droppedItemParent = draggedNode.getParentNode();
		
		droppedItemParent.getChildren().remove(draggedNode);
		
		if (dropTo instanceof Section) {
			dropTo.getChildren().add(0, draggedNode);
			novelIndex.expandNovelIndex(draggedNode);
			success = true;
		} else {
			int indexInParent = dropTo.getParent().getChildren().indexOf(dropTo);
			dropTo.getParent().getChildren().add(indexInParent + 1, draggedNode);
			success = true;
		}
		novelIndex.expandNovelIndex(draggedNode);
		event.setDropCompleted(success);
	}
	
	private void clearDropLocation() {
		if (dropZone != null) {
			dropZone.setStyle("");
		}
	}

}
