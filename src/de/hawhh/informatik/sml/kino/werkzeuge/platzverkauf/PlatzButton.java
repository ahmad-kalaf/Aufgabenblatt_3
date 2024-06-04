package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import de.hawhh.informatik.sml.kino.fachwerte.Platz;

/**
 * Ein Button, mit dem ein Sitzplatz in der Benutzeroberfläche dargestellt wird.
 * Der Sitzplatz kann ausgewählt und als frei oder als verkauft gekennzeichnet
 * werden.
 * 
 * @author SE2-Team Uni HH, PM2-Team
 * @version SoSe 2024
 */
class PlatzButton extends Button
{
    private static final String FARBE_FREI = "lightgreen; ";
    private static final String FARBE_VERKAUFT = "coral; ";
    private static final String FARBE_AUSGEWAEHLT = "yellow; ";

    private Platz _platz;
    private boolean _verkauft;
    private boolean _ausgewaehlt;

    /**
     * Initialisiert einen neuen Button, der mit der Nummer des Sitzes in seiner
     * Sitzreihe beschriftet wird. Ein neuer Button ist weder verkauft noch
     * ausgewählt.
     * 
     * @param platz der Platz, auf den sich dieser Button bezieht.
     * 
     * @require platz != null
     */
    public PlatzButton(Platz platz)
    {
        super("" + platz.getSitzNr());
        _platz = platz;
        _verkauft = false;
        _ausgewaehlt = false;
        setPrefWidth(28);
        setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        setStyle("-fx-background-color: darkgrey, " + FARBE_FREI +
                "-fx-background-radius: 6, 5;" +
                " -fx-background-insets: 0, 0 0 2 2;");
    }

    /**
     * Gibt die Farbe zurück, in der der Button-Hintergrund gezeichnet werden
     * soll, basierend auf dem aktuellen Zustand dieses Buttons.
     */
    private void setzeFarbe()
    {
        if (_ausgewaehlt)
        {
            setStyle("-fx-background-color: darkgrey, " + FARBE_AUSGEWAEHLT +
                    "-fx-background-radius: 6, 5;" +
                    " -fx-background-insets: 0, 2 2 0 0;");
        }
        else if (_verkauft)
        {
            setStyle("-fx-background-color: darkgrey, " + FARBE_VERKAUFT +
                    "-fx-background-radius: 6, 5;" +
                    " -fx-background-insets: 0, 0 0 2 2;");
        }
        else
        {
            setStyle("-fx-background-color: darkgrey, " + FARBE_FREI +
                    "-fx-background-radius: 6, 5;" +
                    " -fx-background-insets: 0, 0 0 2 2;");
        }
    }

    /**
     * Liefert den Platz, auf den sich dieser Button bezieht.
     */
    public Platz getPlatz()
    {
        return _platz;
    }

    /**
     * Kennzeichnet den Sitzplatz, den dieser Button anzeigt, als verkauft oder
     * frei. Nach Aufruf dieser Methode wird der Button nicht mehr als
     * ausgewählt angezeigt.
     * 
     * @param verkauft <code>true</code>, wenn der Sitzplatz als verkauft
     *            gekennzeichnet werden soll, <code>false</code>, wenn er als
     *            frei angezeigt werden soll.
     */
    public void setVerkauft(boolean verkauft)
    {
        _verkauft = verkauft;
        setzeFarbe();
    }

    /**
     * Zeigt diesen Button als ausgewählt oder nicht ausgewählt an.
     * 
     * @param ausgewaehlt <code>true</code>, wenn der Button als ausgewählt
     *            angezeigt werden soll, <code>false</code> sonst.
     */
    public void setAusgewaehlt(boolean ausgewaehlt)
    {
        _ausgewaehlt = ausgewaehlt;
        setzeFarbe();
    }
}
