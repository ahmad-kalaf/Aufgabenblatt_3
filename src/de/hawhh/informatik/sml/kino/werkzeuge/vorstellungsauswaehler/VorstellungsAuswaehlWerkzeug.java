package de.hawhh.informatik.sml.kino.werkzeuge.vorstellungsauswaehler;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import de.hawhh.informatik.sml.kino.materialien.Tagesplan;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;

/**
 * Mit diesem Werkzeug kann der Benutzer oder die Benutzerin eine Vorstellung
 * aus einem Tagesplan auswählen.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt seine
 * Beobachter, wenn sich die ausgewählte Vorstellung geändert hat.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
public class VorstellungsAuswaehlWerkzeug extends ObservableSubwerkzeug
{
    private VorstellungsAuswaehlWerkzeugUI _ui;

    // Das Material dieses Werkzeugs
    private Tagesplan _tagesplan;

    /**
     * Initialisiert das Werkzeug.
     */
    public VorstellungsAuswaehlWerkzeug()
    {
        _ui = new VorstellungsAuswaehlWerkzeugUI();
        registriereUIAktionen();
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Vorstellung ausgewaehlt wurde.
     */
    private void vorstellungWurdeAusgewaehlt()
    {
        informiereUeberAenderung();
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     * 
     * @ensure result != null
     */
    public Pane getUIPane()
    {
        return _ui.getUIPane();
    }

    /**
     * Gibt die derzeit ausgewählte Vorstellung zurück. Wenn keine Vorstellung
     * ausgewählt ist, wird <code>null</code> zurückgegeben.
     */
    public Vorstellung getAusgewaehlteVorstellung()
    {
        Vorstellung result = null;
        VorstellungsFormatierer adapter = (VorstellungsFormatierer) _ui
                .getVorstellungAuswahlList().getSelectionModel().getSelectedItem();
        if (adapter != null)
        {
            result = adapter.getVorstellung();
        }

        return result;
    }

    /**
     * Setzt den Tagesplan, dessen Vorstellungen zur Auswahl angeboten werden.
     * 
     * @require tagesplan != null
     */
    public void setTagesplan(Tagesplan tagesplan)
    {
        assert tagesplan != null : "Vorbedingung verletzt: tagesplan != null";

        _tagesplan = tagesplan;
        List<Vorstellung> vorstellungen = _tagesplan.getVorstellungen();
        aktualisiereAngezeigteVorstellungsliste(vorstellungen);
    }

    /**
     * Aktualisiert die Liste der Vorstellungen.
     */
    private void aktualisiereAngezeigteVorstellungsliste(
            List<Vorstellung> vorstellungen)
    {
        _ui.getVorstellungAuswahlList().getItems().clear();
        if (vorstellungen.size() > 0)
        {
            VorstellungsFormatierer[] varray = new VorstellungsFormatierer[vorstellungen
                    .size()];
            for (int i = 0; i < vorstellungen.size(); i++)
            {
                varray[i] = new VorstellungsFormatierer(vorstellungen.get(i));
            }

            _ui.getVorstellungAuswahlList().getItems().addAll(varray);
            _ui.getVorstellungAuswahlList().getSelectionModel().clearAndSelect(0);
        }
    }

    /**
     * 
     * Verbindet die fachlichen Aktionen mit den Interaktionselementen der
     * graphischen Benutzungsoberfläche.
     */
    private void registriereUIAktionen()
    {
//        _ui.getVorstellungAuswahlList().getSelectionModel()
//                .selectedItemProperty()
//                .addListener(new ChangeListener<VorstellungsFormatierer>()
//                {
//                    @Override
//                    public void changed(
//                            ObservableValue<? extends VorstellungsFormatierer> arg0,
//                            VorstellungsFormatierer arg1,
//                            VorstellungsFormatierer arg2)
//                    {
//                        vorstellungWurdeAusgewaehlt();
//                    }
//
//                });
    	_ui.getVorstellungAuswahlList().getSelectionModel()
      .selectedItemProperty()
      .addListener((arg0, arg1, arg2) -> vorstellungWurdeAusgewaehlt());
    }
}
