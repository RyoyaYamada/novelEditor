package component;

import javafx.scene.control.Alert;

public class EditorDialog extends Alert{
	public EditorDialog(AlertType alertType, String title, String headerText, String contextText) {
		super(alertType);
		setTitle(title);
		setHeaderText(headerText);
		setContentText(contextText);
	}

}
