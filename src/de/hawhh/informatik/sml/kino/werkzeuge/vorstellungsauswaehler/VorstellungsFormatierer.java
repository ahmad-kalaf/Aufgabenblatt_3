package de.hawhh.informatik.sml.kino.werkzeuge.vorstellungsauswaehler;

import de.hawhh.informatik.sml.kino.materialien.Vorstellung;

/**
 * Formatierer für eine {@link Vorstellung}.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2024
 */
class VorstellungsFormatierer
{
    private Vorstellung _vorstellung;

    /**
     * Initialisiert einen Formatierer für die angegebene Vorstellung.
     * 
     * @param vorstellung die Vorstellung, die von diesem Formatierer
     *            dargestellt wird.
     */
    public VorstellungsFormatierer(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
    }

    /**
     * Gibt die Vorstellung zurück, die von diesem Formatierer dargestellt wird.
     */
    Vorstellung getVorstellung()
    {
        return _vorstellung;
    }

    @Override
    public String toString()
    {
        return _vorstellung.getAnfangszeit().getFormatiertenString() + " - "
                + _vorstellung.getFilm().getFormatiertenString() + ", "
                + _vorstellung.getKinosaal().getName();
    }
}
