package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import javafx.stage.Stage;

/**
 * 
 */
public class BarzahlungsSubwerkzeug {
	private BarzahlungsSubwerkzeugUI _ui;
	private boolean _bezahlungErfolgreich;
	private Vorstellung _vorstellung;
	private Platzplan _plan;

	public BarzahlungsSubwerkzeug(Vorstellung v, Platzplan plan) {
		_bezahlungErfolgreich = false;
		_ui = new BarzahlungsSubwerkzeugUI(v);
		_plan = plan;
		_vorstellung = v;
		registriereUIAktionen();
	}

	public void zeigeBarzahlungsFenster(Stage owner) {
		_ui.starteStage(owner);
	}

	/**
	 * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
	 */
	private void registriereUIAktionen() {
		_ui.getAbbrechenButton().setOnAction((action) -> _ui.getUIStage().close());
		_ui.getTextField().textProperty().addListener((a, b, c) -> {
			pruefeBezahlvorgang();
			if (pruefeEingabe()) {
				int zuZahlen = berechnePreisFuerAusgewaehlte();
				int rueckgeld = berechneRueckgeld() >= 0 ? berechneRueckgeld() : 0;
				int gezahlt = _ui.getTextField().getText().isBlank() ? 0
						: Integer.parseInt(_ui.getTextField().getText());
				_ui.getLabelText().setText("Zu Zahlen: " + zuZahlen + " Eurocent" + "\nGezahlt: "
						+ gezahlt + " Eurocent" + "\nRückgeld: " + rueckgeld + " Eurocent");
			} else {
				_ui.getLabelText().setText("Fehler in Eingabe");
			}
		});

	}

	private void pruefeBezahlvorgang() {
		if (pruefeEingabe()) {
			_bezahlungErfolgreich = berechneRueckgeld() >= 0;
		}
	}

	private int berechneRueckgeld() {
		if (_ui.getTextField().getText().isBlank()) {
			return 0;
		}
		return Integer.parseInt(_ui.getTextField().getText()) - berechnePreisFuerAusgewaehlte();
	}

	private int berechnePreisFuerAusgewaehlte() {
		return _plan.getAusgewaehltePlaetze().size() * _vorstellung.getPreis();
	}

	private boolean pruefeEingabe() {
		String value = _ui.getTextField().getText();
		return value != null && value.chars().allMatch(i -> i >= 48 && i <= 57);
	}

	public void setZuZahlenderBetragAmAnfang() {

		_ui.getLabelText().setText("Zu Zahlen: " + berechnePreisFuerAusgewaehlte() + " Eurocent" + "\nGezahlt: " + "0"
				+ " Eurocent" + "\nRückgeld: " + "0" + " Eurocent");
	}
}
