package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.util.EventListener;

/**
 * Interface eines Listeners, der bei Änderungen der Platzauswahl benachrichtigt
 * wird.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
interface PlatzSelectionListener extends EventListener
{
    /**
     * Wird aufgerufen, wenn sich die Auswahl geändert hat.
     * 
     * @param event das Event, das die Änderung beschreibt.
     */
    void auswahlGeaendert(PlatzSelectionEvent event);
}
