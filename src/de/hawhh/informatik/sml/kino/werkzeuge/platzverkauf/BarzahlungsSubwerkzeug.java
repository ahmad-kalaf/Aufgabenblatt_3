package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import javafx.scene.input.KeyCode;
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
		_ui = new BarzahlungsSubwerkzeugUI();
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
		_ui.getOkButton().setDisable(true);
		_ui.getAbbrechenButton()
				.setOnAction((action) -> _ui.getUIStage().close());
		_ui.getTextField().setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER)
			{
				pruefeBezahlvorgang();
				if(pruefeEingabe() && !_ui.getTextField().getText().isBlank() && rechnePreisInEuro() > 0)
				{
					double zuZahlen = berechnePreisFuerAusgewaehlte();
					double rueckgeld = berechneRueckgeld() >= 0 ? berechneRueckgeld()
							: 0;
					double gezahlt = _ui.getTextField().getText().isBlank() ? 0
							: rechnePreisInEuro();
					_ui.getLabelText()
							.setText("ZEITSTEMPEL: " + _zeitstempel
									+ _vorstellungsinfo + "RECHNUNGSDETAILS"
									+ "\nZu Zahlen: " + zuZahlen + " Eurocent"
									+ "\nGezahlt: " + gezahlt + " Eurocent"
									+ "\nRückgeld: " + rueckgeld + " Eurocent");
					_ui.getOkButton().setDisable(false);
				} else
				{
					_ui.getLabelText().setText("Fehler in Eingabe");
					_ui.getOkButton().setDisable(true);
				}
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

	private double berechneRueckgeld()
	{
		if(_ui.getTextField().getText().isBlank())
		{
			return 0;
		}
		return rechnePreisInEuro()
				- berechnePreisFuerAusgewaehlte();
	}

	private double berechnePreisFuerAusgewaehlte()
	{
//		return _plan.getAusgewaehltePlaetze().size() * _vorstellung.getPreis();
		return (double)_vorstellung.getPreisFuerPlaetze(_plan.getAusgewaehltePlaetze()) / 100.0;
	}

	private boolean pruefeEingabe()
	{
		String value = _ui.getTextField().getText();
		
		return value != null && value.matches("-?\\d+(\\.\\d+)?") || value.matches("[1-9]") || value.isBlank();
	}
	
	private double rechnePreisInEuro()
	{
		if(pruefeEingabe())
		{
			return Double.valueOf(_ui.getTextField().getText());
		}
		return 0;
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
