package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import de.hawhh.informatik.sml.kino.fachwerte.Platz;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;

/**
 * Widget, das die Plätze in einem Kinosaal grafisch darstellt und es
 * ermöglicht, Plätze für den Verkauf auszuwählen.
 * 
 * Hierfür wurde ein eigenes Widget entwickelt, damit das Werkzeug nur einen
 * Listener für ein spezielles Auswahl-Event registrieren muss, statt an jedem
 * einzelnen Button einen EventHandler registrieren zu müssen. Dieses Widget
 * kapselt also die Abbildung der Events der einzelnen Buttons auf ein
 * Auswahl-Event.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
class Platzplan extends GridPane
{
	public static double PLATZBUTTON_GROESSE = 20;

	private PlatzButton[][] _buttons;
	private EventHandler<ActionEvent> _buttonListener;
	private Set<Platz> _ausgewaehltePlaetze;
	private List<PlatzSelectionListener> _selectionListener;
	private Vorstellung _vorstellung;

	/**
	 * Initialisiert einen neuen, leeren Platzplan.
	 */
	public Platzplan()
	{
		setPadding(new Insets(3, 3, 3, 3));
		setGridLinesVisible(true);
		setHgap(5);
		setVgap(5);
		erzeugePlatzAuswahlListener();
		_ausgewaehltePlaetze = new HashSet<Platz>();
		_selectionListener = new ArrayList<PlatzSelectionListener>();
	}

	/**
	 * Erzeugt und registriert den Listener, der darauf reagiert, wenn durch
	 * Drücken eines der Buttons ein Platz ausgewählt wird.
	 */
	private void erzeugePlatzAuswahlListener()
	{
		_buttonListener = (ActionEvent action) -> {
			// Es gibt nur einen Listener für alle Buttons. Welcher der
			// Buttons gedrückt wurde, muss deshalb aus dem Event ausgelesen
			// werden.
			PlatzButton button = (PlatzButton) action.getSource();

			// Je nachdem, ob der Platz bereits ausgewählt war, wird er aus
			// der Menge der ausgewählten Plätze entfernt oder dieser
			// hinzugefügt.
			Platz platz = button.getPlatz();
			if(_ausgewaehltePlaetze.contains(platz))
			{
				_ausgewaehltePlaetze.remove(platz);
				button.setAusgewaehlt(false);
				if(_vorstellung != null)
				{
					_vorstellung.deselektiere(platz);
				}
				informiereSelectionListener(_ausgewaehltePlaetze);
			} else
			{
				_ausgewaehltePlaetze.add(platz);
				button.setAusgewaehlt(true);
				if(_vorstellung != null)
				{
					_vorstellung.selektierePlatz(platz);
				}
				informiereSelectionListener(_ausgewaehltePlaetze);
			}
		};
	}

	/**
	 * Fügt einen Listener hinzu, der bei Änderungen der Auswahl benachrichtigt
	 * wird.
	 * 
	 * @param listener der Listener.
	 */
	public void addPlatzSelectionListener(PlatzSelectionListener listener)
	{
		_selectionListener.add(listener);
	}

	/**
	 * Entfernt einen Listener.
	 * 
	 * @param listener der Listener.
	 */
	public void removePlatzSelectionListener(PlatzSelectionListener listener)
	{
		_selectionListener.remove(listener);
	}

	/**
	 * Benachrichtigt die SelectionListener, dass sich die Auswahl geändert hat.
	 * 
	 * @param ausgewaehltePlaetze die neue Auswahl.
	 */
	private void informiereSelectionListener(Set<Platz> ausgewaehltePlaetze)
	{
		PlatzSelectionEvent event = new PlatzSelectionEvent(this,
				ausgewaehltePlaetze);
		for(PlatzSelectionListener listener : _selectionListener)
		{
			listener.auswahlGeaendert(event);
		}
	}

	/**
	 * Setzt die Anzahl der Plätze, die in diesem Platzplan zur Auswahl
	 * angeboten werden. Achtung, nach dem Aufruf dieser Methode werden zunächst
	 * alle Plätze als frei angezeigt!
	 * 
	 * @param anzahlReihen        die Anzahl der Reihen
	 * @param anzahlSitzeProReihe die Anzahl der Plätze pro Reihe
	 * 
	 * @require anzahlReihen >= 0
	 * @require anzahlSitzeProReihe >= 0
	 */
	public void setAnzahlPlaetze(int anzahlReihen, int anzahlSitzeProReihe,
			Vorstellung vorstellung)
	{
		assert anzahlReihen >= 0 : "Vorbedingung verletzt: anzahlReihen >= 0";
		assert anzahlSitzeProReihe >= 0
				: "Vorbedingung verletzt: anzahlSitzeProReihe >= 0";

		_vorstellung = vorstellung;
		// Alle vorhandenen Buttons etc. entfernen
		getChildren().clear();
		getColumnConstraints().clear();
		getRowConstraints().clear();

		for(int sitz = 0; sitz < anzahlSitzeProReihe + 1; sitz++)
		{
			addGrowableColumnConstraint();
		}

		// Neue Buttons für Plätze erstellen
		_buttons = new PlatzButton[anzahlReihen][anzahlSitzeProReihe];
		for(int reihe = 0; reihe < anzahlReihen; reihe++)
		{
			Label label = new Label("Reihe " + (reihe + 1) + ":");
			add(label, 0, reihe);
			addGrowableRowConstraint();
			for(int sitz = 0; sitz < anzahlSitzeProReihe; sitz++)
			{
				PlatzButton button = new PlatzButton(Platz.get(reihe, sitz));
				button.setMinWidth(PLATZBUTTON_GROESSE);
				button.setMinHeight(PLATZBUTTON_GROESSE);

				add(button, sitz + 1, reihe);
				// GridPane.setHgrow(button, Priority.ALWAYS);

				button.setOnAction(_buttonListener);
				_buttons[reihe][sitz] = button;
			}
		}
		// ColumnConstraints column1 = new
		// ColumnConstraints(100,100,Double.MAX_VALUE);
		// column1.setHgrow(Priority.ALWAYS);
		// getColumnConstraints().add(column1);

		// Nach der Änderung ist kein Platz ausgewählt
		_ausgewaehltePlaetze.clear();
		informiereSelectionListener(_ausgewaehltePlaetze);
	}

	private void addGrowableRowConstraint()
	{
		RowConstraints rowC = new RowConstraints();
		rowC.setVgrow(Priority.ALWAYS);
		rowC.setFillHeight(true);
		getRowConstraints().add(rowC);
	}

	private void addGrowableColumnConstraint()
	{
		ColumnConstraints constr = new ColumnConstraints();
		constr.setHgrow(Priority.ALWAYS);
		constr.setFillWidth(true);
		getColumnConstraints().add(constr);
	}

	/**
	 * Gibt die Menge der ausgewählten Plätze zurück.
	 * 
	 * @ensure result != null
	 */
	public Set<Platz> getAusgewaehltePlaetze()
	{
		return new HashSet<Platz>(_ausgewaehltePlaetze);
	}

	/**
	 * Entfernt die Auswahl, sodass keine Plätze mehr ausgewählt sind.
	 */
	public void entferneAuswahl()
	{
		for(Platz platz : _ausgewaehltePlaetze)
		{
			_buttons[platz.getReihenNr()][platz.getSitzNr()]
					.setAusgewaehlt(false);
			_ausgewaehltePlaetze.remove(platz);
		}
		_ausgewaehltePlaetze.clear();
		// repaint();
	}

	/**
	 * Markiert den angegebenen Platz als verkauft.
	 * 
	 * @param platz der Platz.
	 * 
	 * @require platz != null
	 */
	public void markierePlatzAlsVerkauft(Platz platz)
	{
		assert platz != null : "Vorbedingung verletzt: platz != null";
		_buttons[platz.getReihenNr()][platz.getSitzNr()].setVerkauft(true);
		_ausgewaehltePlaetze.remove(platz);
		// repaint();
	}

	/**
	 * Markiert den angegebenen Platz als frei.
	 * 
	 * @param platz der Platz.
	 * 
	 * @require platz != null
	 */
	public void markierePlatzAlsFrei(Platz platz)
	{
		assert platz != null : "Vorbedingung verletzt: platz != null";
		_buttons[platz.getReihenNr()][platz.getSitzNr()].setVerkauft(false);
		_ausgewaehltePlaetze.remove(platz);
		// repaint();
	}

	public void markierePlatzAlsSelektiert(Platz platz)
	{
		assert platz != null : "Vorbedingung verletzt: platz != null";
		_buttons[platz.getReihenNr()][platz.getSitzNr()].setAusgewaehlt(true);
		_ausgewaehltePlaetze.add(platz);
		// repaint();
	}
	
	public void auswahlHinzufuegen(Platz platz) {
		assert platz != null : "Vorbedingung verletzt: platz != null";
		_buttons[platz.getReihenNr()][platz.getSitzNr()].setAusgewaehlt(true);
		_ausgewaehltePlaetze.add(platz);
	}

}
