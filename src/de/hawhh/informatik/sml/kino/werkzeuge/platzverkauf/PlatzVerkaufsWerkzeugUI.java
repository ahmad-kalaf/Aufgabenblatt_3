package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import de.hawhh.informatik.sml.kino.fachwerte.Platz;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Die UI des {@link PlatzVerkaufsWerkzeug}.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
class PlatzVerkaufsWerkzeugUI {
	// Die Widgets, aus denen das UI sich zusammensetzt
	private Pane _hauptPanel;
	private Label _preisLabel;
	private Button _verkaufenButton;
	private Button _stornierenButton;
	private Platzplan _platzplan;

	/**
	 * Initialisiert die UI.
	 */
	public PlatzVerkaufsWerkzeugUI() {
		_hauptPanel = erstellePanel();
	}

	/**
	 * Erzeugt das Panel, in dem der Kinosaal mit den Sitzplätzen dargestellt wird.
	 */
	private Pane erstellePanel() {
		BorderPane hauptPane = new BorderPane();
		_platzplan = new Platzplan();
		final ScrollPane scrPane = new ScrollPane();
		scrPane.setContent(_platzplan);
		scrPane.viewportBoundsProperty().addListener((arg0, arg1, arg2) -> {
			Node content = scrPane.getContent();
			scrPane.setFitToWidth(content.prefWidth(-1) < arg2.getWidth());
			scrPane.setFitToHeight(content.prefHeight(-1) < arg2.getHeight());
		});
		hauptPane.setCenter(scrPane);
		BorderPane southPane = new BorderPane();
		FlowPane preisPane = new FlowPane();
		_preisLabel = new Label();
		preisPane.getChildren().add(_preisLabel);
		preisPane.setAlignment(Pos.CENTER_LEFT);
		preisPane.setPadding(new Insets(10));

		FlowPane buttonPane = new FlowPane();
		buttonPane.setAlignment(Pos.CENTER_RIGHT);
		_verkaufenButton = new Button("Verkaufen");
		_stornierenButton = new Button("Stornieren");
		buttonPane.setHgap(10);
		buttonPane.setPadding(new Insets(10));
		buttonPane.getChildren().addAll(_verkaufenButton, _stornierenButton);

		southPane.setLeft(preisPane);
		southPane.setRight(buttonPane);

		hauptPane.setBottom(southPane);

		return hauptPane;
	}

	/**
	 * Gibt den Platzplan zurück.
	 */
	public Platzplan getPlatzplan() {
		return _platzplan;
	}

	/**
	 * Gibt das Label für die Preisanzeige zurück.
	 */
	public Label getPreisLabel() {
		return _preisLabel;
	}

	/**
	 * Liefert den Stornieren-Button.
	 */
	public Button getStornierenButton() {
		return _stornierenButton;
	}

	/**
	 * Liefert den Verkaufen-Button.
	 */
	public Button getVerkaufenButton() {
		return _verkaufenButton;
	}

	/**
	 * Liefert das Panel, in dem die Widgets angeordnet sind.
	 */
	public Pane getUIPane() {
		return _hauptPanel;
	}

}
