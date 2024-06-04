package de.hawhh.informatik.sml.kino.fachwerte;

/**
 * Eine Sitzplatzangabe in einem Kinosaal. Der Platz setzt sich zusammen aus der
 * Reihennummer und der Sitznummer in dieser Reihe.
 * 
 * @author SE2-Team Uni HH, PM2-Team
 * @version SoSe 2024
 */
public final class Platz
{
    private final int _reihe;
    private final int _sitz;

    /**
     * Wählt einen Platz aus.
     * 
     * @param reihe die Sitzreihe.
     * @param sitz die Nummer des Sitzes in seiner Sitzreihe.
     * 
     * @require reihe >=0
     * @require sitz >= 0
     */
    public static Platz get(int reihe, int sitz)
    {
        assert reihe >= 0 : "Vorbedingung verletzt: reihe <=0";
        assert sitz >= 0 : "Vorbedingung verletzt: sitz <=0";
        return new Platz(reihe, sitz);
    }

    private Platz(int reihe, int sitz)
    {
        _reihe = reihe;
        _sitz = sitz;
    }

    /**
     * Gibt die Sitzreihe zurueck, in der sich dieser Platz befindet.
     */
    public int getReihenNr()
    {
        return _reihe;
    }

    /**
     * Gibt die Nummer dieses Sitzes in seiner Sitzreihe zurueck.
     */
    public int getSitzNr()
    {
        return _sitz;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean ergebnis = false;
        if (o instanceof Platz)
        {
            Platz platz = (Platz) o;
            ergebnis = ((platz.getReihenNr() == this.getReihenNr())
                    && (platz.getSitzNr() == this.getSitzNr()));
        }
        return ergebnis;
    }

    @Override
    public int hashCode()
    {
        return 1000 * getReihenNr() + getSitzNr();
    }

    @Override
    public String toString()
    {
        return _reihe + "-" + _sitz;
    }
}
