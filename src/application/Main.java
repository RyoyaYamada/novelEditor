package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// TODO
// キャレットの保存
// 選択中のテキストが分かるように
// フォントとサイズの初期値
// 保存、カウント等のプログレス -> DONE
// 保存後のダイアログ　-> DONE
// フォルダに対してカウントした時に全体の文字数を返すように -> DONE
// 目次でデリートキー押下で削除可能に
// 削除時ダイアログ表示


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("NovelEditor.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
