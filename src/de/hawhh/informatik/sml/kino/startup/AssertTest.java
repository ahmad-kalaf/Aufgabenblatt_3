package de.hawhh.informatik.sml.kino.startup;

import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Diese Klasse testet, ob in Eclipse die Assertions aktiviert sind.
 * 
 * @author SE2-Team Uni HH, PM2-Team
 * @version SoSe 2024
 */
public class AssertTest
{
    @Test
    public void assertionEnabledTest()
    {
        boolean assertsEnabled = false;
        assert assertsEnabled = true;

        if (!assertsEnabled)
        {
            fail("Asserts muessen aktiviert sein -ea");
            /*
             * Anleitung:
             * 
             * Window > Preferences > Java > Installed JREs -> ausgewaehlte JRE
             * markieren und auf "Edit" klicken > im erscheinenden Dialog bei
             * "Default VM Arguments" -ea eingeben
             */
        }
    }
}
