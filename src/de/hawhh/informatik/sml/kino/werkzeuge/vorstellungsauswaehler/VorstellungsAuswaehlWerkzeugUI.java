package de.hawhh.informatik.sml.kino.werkzeuge.vorstellungsauswaehler;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Die UI des {@link VorstellungsAuswaehlWerkzeug}.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
class VorstellungsAuswaehlWerkzeugUI
{
    // Die Widgets, aus denen das UI sich zusammensetzt
    private BorderPane _hauptPanel;
    private ListView<VorstellungsFormatierer> _vorstellungAuswahlList;

    /**
     * Initialisiert die UI.
     */
    public VorstellungsAuswaehlWerkzeugUI()
    {
        _hauptPanel = new BorderPane();
        _hauptPanel.setTop(new Label("Vorstellung:"));
        _vorstellungAuswahlList = new ListView<VorstellungsFormatierer>();
        _hauptPanel.setCenter(_vorstellungAuswahlList);
    }

    /**
     * Liefert den UI-Container, in dem die Widgets angeordnet sind.
     */
    public Pane getUIPane()
    {
        return _hauptPanel;
    }

    /**
     * Liefert das Listen-Widget, in dem die Vorstellungen angezeigt werden.
     */
    public ListView<VorstellungsFormatierer> getVorstellungAuswahlList()
    {
        return _vorstellungAuswahlList;
    }
}
