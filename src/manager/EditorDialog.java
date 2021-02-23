package manager;

import javafx.scene.control.Alert;

public class EditorDialog extends Alert{

//	protected EditorDialog(AlertType alertType, String contentText, ButtonType[] buttons) {
//		super(alertType, contentText, buttons);
//	}

	public EditorDialog(AlertType alertType, String title, String headerText, String contextText) {
		super(alertType);
		setTitle(title);
		setHeaderText(headerText);
		setContentText(contextText);
	}

}
