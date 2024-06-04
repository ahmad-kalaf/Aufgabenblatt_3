package de.hawhh.informatik.sml.kino.startup;

import javafx.application.Application;
import javafx.stage.Stage;
import de.hawhh.informatik.sml.kino.fachwerte.Datum;
import de.hawhh.informatik.sml.kino.fachwerte.FSK;
import de.hawhh.informatik.sml.kino.fachwerte.Uhrzeit;
import de.hawhh.informatik.sml.kino.materialien.Film;
import de.hawhh.informatik.sml.kino.materialien.Kino;
import de.hawhh.informatik.sml.kino.materialien.Kinosaal;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import de.hawhh.informatik.sml.kino.werkzeuge.kasse.KassenWerkzeug;

/**
 * Startet die Anwendung.
 * 
 * @author SE2-Team Uni HH, PM2-Team
 * @version SoSe 2024
 */
public class StartupKinoticketverkauf_VorlageBlatt03 extends Application
{
    /**
     * Die Main-Methode.
     * 
     * @param args die Aufrufparameter.
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final Kino kino = erzeugeKinoMitBeispieldaten();
        new KassenWerkzeug(kino);
    }

    /**
     * Erzeugt ein Kino mit einigen Vorstellungen.
     */
    private static Kino erzeugeKinoMitBeispieldaten()
    {
        final Kinosaal[] saele = { new Kinosaal("Saal 1", 20, 25),
                new Kinosaal("Saal 2", 16, 20), new Kinosaal("Saal 3", 10, 16) };

        // Filme: Some all-time favorites.
        Film[] filme = {
                new Film("Match Point", 124, FSK.FSK6, true),
                new Film("Die zwölf Geschworenen", 96, FSK.FSK12, false),
                new Film("Die Reifeprüfung", 106, FSK.FSK16, false),
                new Film("Down by Law", 107, FSK.FSK12, false),
                new Film("American Beauty", 122, FSK.FSK12, true) };

        Uhrzeit nachmittag = Uhrzeit.get(17, 30);
        Uhrzeit abend = Uhrzeit.get(20, 0);
        Uhrzeit spaet = Uhrzeit.get(22, 30);
        Uhrzeit nacht = Uhrzeit.get(1, 0);

        Datum d1 = Datum.heute();
        Datum d2 = d1.naechsterTag();
        Datum d3 = d2.naechsterTag();

        final Vorstellung[] vorstellungen = {
                // Heute
                new Vorstellung(saele[0], filme[2], nachmittag, abend, d1, 500),
                new Vorstellung(saele[0], filme[0], abend, spaet, d1, 700),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d1, 700),

                new Vorstellung(saele[1], filme[3], nachmittag, abend, d1, 900),
                new Vorstellung(saele[1], filme[1], spaet, nacht, d1, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d1, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d1, 900),

                // Morgen
                new Vorstellung(saele[0], filme[0], abend, spaet, d2, 500),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d2, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d2, 900),
                new Vorstellung(saele[1], filme[4], abend, nacht, d2, 800),

                new Vorstellung(saele[2], filme[3], nachmittag, abend, d2, 1000),
                new Vorstellung(saele[2], filme[1], spaet, nacht, d2, 900),

                // Übermorgen
                new Vorstellung(saele[0], filme[1], abend, spaet, d3, 500),
                new Vorstellung(saele[0], filme[1], spaet, nacht, d3, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d3, 900),
                new Vorstellung(saele[1], filme[0], abend, nacht, d3, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d3, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d3, 900) };

        return new Kino(saele, vorstellungen);
    }

}
