package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import javafx.stage.Stage;

/**
 * 
 */
public class BarzahlungsSubwerkzeug
{
	private BarzahlungsSubwerkzeugUI _ui;
	private boolean _bezahlungErfolgreich;
	private Vorstellung _vorstellung;
	private Platzplan _plan;
	private boolean _okGedrueckt;
	private String _zeitstempel;
	private String _vorstellungsinfo;

	public BarzahlungsSubwerkzeug(Vorstellung v, Platzplan plan)
	{
		_bezahlungErfolgreich = false;
		_okGedrueckt = false;
		_ui = new BarzahlungsSubwerkzeugUI(v);
		_plan = plan;
		_vorstellung = v;
		registriereUIAktionen();
		// Zeit und Datum beim Verkauf der Tickets
		LocalDateTime jetzt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		_zeitstempel = jetzt.format(formatter) + "\n\n";
		// Vorstellungsinfos
		_vorstellungsinfo = "Vorstellung: "
				+ _vorstellung.getFilm().getFormatiertenString()
				+ "\nKinosaal: " + _vorstellung.getKinosaal().getName()
				+ "\nStartzeit: " + _vorstellung.getAnfangszeit() + "\n\n";
	}

	public void zeigeBarzahlungsFenster(Stage owner)
	{
		_ui.starteStage(owner);
	}

	/**
	 * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
	 */
	private void registriereUIAktionen()
	{
		_ui.getAbbrechenButton()
				.setOnAction((action) -> _ui.getUIStage().close());
		_ui.getTextField().textProperty().addListener((a, b, c) -> {
			pruefeBezahlvorgang();
			if(pruefeEingabe())
			{
				int zuZahlen = berechnePreisFuerAusgewaehlte();
				int rueckgeld = berechneRueckgeld() >= 0 ? berechneRueckgeld()
						: 0;
				int gezahlt = _ui.getTextField().getText().isBlank() ? 0
						: Integer.parseInt(_ui.getTextField().getText());
				_ui.getLabelText()
						.setText("ZEITSTEMPEL: " + _zeitstempel
								+ _vorstellungsinfo + "RECHNUNGSDETAILS"
								+ "\nZu Zahlen: " + zuZahlen + " Eurocent"
								+ "\nGezahlt: " + gezahlt + " Eurocent"
								+ "\nRückgeld: " + rueckgeld + " Eurocent");
			} else
			{
				_ui.getLabelText().setText("Fehler in Eingabe");
			}
		});
		_ui.getOkButton().setOnAction(action -> {
			_okGedrueckt = _bezahlungErfolgreich;
			if(_okGedrueckt)
			{
				_ui.getUIStage().close();
			}
		});
	}

	private void pruefeBezahlvorgang()
	{
		if(pruefeEingabe())
		{
			_bezahlungErfolgreich = berechneRueckgeld() >= 0;
		}
	}

	private int berechneRueckgeld()
	{
		if(_ui.getTextField().getText().isBlank())
		{
			return 0;
		}
		return Integer.parseInt(_ui.getTextField().getText())
				- berechnePreisFuerAusgewaehlte();
	}

	private int berechnePreisFuerAusgewaehlte()
	{
		return _plan.getAusgewaehltePlaetze().size() * _vorstellung.getPreis();
	}

	private boolean pruefeEingabe()
	{
		String value = _ui.getTextField().getText();
		try 
		{
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			_ui.getLabelText().setText("Fehler in Eingabe: Input ist zu groß");
		}
		
		return value != null && value.chars().allMatch(i -> i >= 48 && i <= 57);
	}

	public void setInfotextAmAnfang()
	{
		_ui.getLabelText().setText("ZEITSTEMPEL: " + _zeitstempel
				+ _vorstellungsinfo + "RECHNUNGSDETAILS" + "\nZu Zahlen: "
				+ berechnePreisFuerAusgewaehlte() + " Eurocent" + "\nGezahlt: "
				+ "0" + " Eurocent" + "\nRückgeld: " + "0" + " Eurocent");
	}

	public boolean getOkGedrueckt()
	{
		return _okGedrueckt;
	}
}
