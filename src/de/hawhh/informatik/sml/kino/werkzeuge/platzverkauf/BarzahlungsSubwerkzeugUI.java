package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BarzahlungsSubwerkzeugUI {

	private int _zuZahlen;
	private Button _okButton;
	private Button _abbrechenButton;
	private Label _label;
	private TextField _textField;
	private Stage dialog;
	private boolean _ownerGesetzt;

	public BarzahlungsSubwerkzeugUI() {

		Font textstyle = Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 13);
		dialog = new Stage();
		dialog.initModality(Modality.WINDOW_MODAL); // Modalität setzen
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Barzahlung");
		dialog.setMinWidth(300);
		dialog.setMinHeight(350);

		_label = new Label();
		_label.setFont(textstyle);
		_label.setTextFill(Color.BLUE);
		_okButton = new Button("OK");
		_abbrechenButton = new Button("Abbrechen");
		_textField = new TextField();
		_textField.setPromptText("Betrag eingeben");

		GridPane dialogLayout = new GridPane();
		dialogLayout.setAlignment(Pos.CENTER);
		dialogLayout.setPadding(new Insets(10));
		dialogLayout.setHgap(10);
		dialogLayout.setVgap(10);

		// Text und Textfeld in der Mitte
		dialogLayout.add(_label, 0, 1);
		dialogLayout.add(_textField, 0, 3);
		GridPane.setHalignment(_label, HPos.LEFT);
		GridPane.setHalignment(_textField, HPos.LEFT);

		// Buttons
		HBox buttonsBox = new HBox(10, _okButton, _abbrechenButton);
		buttonsBox.setAlignment(Pos.CENTER);
		dialogLayout.add(buttonsBox, 0, 4);
		GridPane.setHalignment(buttonsBox, HPos.CENTER);

		Scene dialogScene = new Scene(dialogLayout, 420, 420);
		dialog.setScene(dialogScene);
	}

	public void starteStage(Stage owner) {
		if (!_ownerGesetzt) {
			dialog.initOwner(owner);
			_ownerGesetzt = true;
		}
		dialog.showAndWait();
	}

	public Label getLabelText() {
		return _label;
	}

	public Button getOkButton() {
		return _okButton;
	}

	public Button getAbbrechenButton() {
		return _abbrechenButton;
	}

	public int getZuzahlen() {
		return _zuZahlen;
	}

	public TextField getTextField() {
		return _textField;
	}

	public Stage getUIStage() {
		return dialog;
	}
	

}
