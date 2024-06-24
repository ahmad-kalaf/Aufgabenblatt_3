package de.hawhh.informatik.sml.kino.fachwerte;

public final class Geldbetrag {
	private final int _euroAnteil;
	private final int _centAnteil;

	/**
	 * Wählt einen Geldbetrag aus.
	 * 
	 * @param eurocent Der Betrag in ganzen Euro-Cent als integer
	 * 
	 * @require eurocent >= 0;
	 */
	public static Geldbetrag getGeldbetrag(int eurocent) {
		assert eurocent >= 0 : "Vorbedingung verletzt: eurocent >= 0";
		return new Geldbetrag(eurocent);
	}

	/**
	 * Wählt einen Geldbetrag aus.
	 * Es können sowohl Kommazahlen als auch Ganzzahlen
	 * in form von Strings eingegeben werden.
	 * Beispiele: 	getGeldbetrag("10")
	 * 				getGeldbetrag("10.")
	 * 				getGeldbetrag("10.01")
	 * 				getGeldbetrag("10.1")
	 * 				getGeldbetrag("10.10")
	 * 
	 * @param eurocent Der Betrag in ganzen Euro-Cent als String
	 * 
	 * @require eurocent != null;
	 */
	public static Geldbetrag getGeldbetrag(String eurocent) {
		assert eurocent != null : "Vorbedingung verletzt: eurocent != null";
		if (eurocent.matches("\\d+")) {
            return new Geldbetrag(Integer.parseInt(eurocent) * 100);
        } else if (eurocent.matches("\\d+\\.")) {
            int euroAnteil = Integer.parseInt(eurocent.substring(0, eurocent.length() - 1));
            return new Geldbetrag(euroAnteil * 100);
        } else if (eurocent.matches("\\d+\\.\\d{1,2}")) {
            String[] euroUndCent = eurocent.split("\\.", 2);
            int euroAnteil = Integer.parseInt(euroUndCent[0]);
            int centAnteil = (euroUndCent.length > 1) ? Integer.parseInt(euroUndCent[1]) : 0;

            // Korrigiere den Centanteil, wenn nur eine Ziffer vorhanden ist
            if (euroUndCent[1].length() == 1) {
                centAnteil *= 10; // z.B. 1.5 -> 150
            }
            return new Geldbetrag((euroAnteil * 100) + centAnteil);
        } else {
            return new Geldbetrag(0);
        }
	}

	private Geldbetrag(int eurocent) {
		_euroAnteil = eurocent / 100;
		_centAnteil = eurocent % 100;
	}
	
	/**
	 * Addiert dieses Geldbetrag mit betrag und gibt die Summe als Geldbetrag zurück.
	 * 
	 * @param betrag1
	 * @param betrag2
	 * 
	 * @return result Summe von diesem Geldbetrag und betrag
	 * 
	 * @require betrag1 != null
	 * 
	 * @ensure result == this + betrag
	 */
	public Geldbetrag addiere(Geldbetrag betrag) {
		assert betrag != null : "Vorbedingung verletzt: betrag1 != null";
		int summand1 = gibWertInEurocent(this);
		int summand2 = gibWertInEurocent(betrag);
		int summe = summand1 + summand2;
		return new Geldbetrag(summe);
	}
	
	/**
	 * Subtrahiert betrag von diesem Geldbetrag und gibt die Differenz als Geldbetrag zurück.
	 * 
	 * @param betrag Subtrahend als Geldbetrag
	 * 
	 * @return result Differenz als Geldbetrag
	 * 
	 * @require betrag != null
	 * 
	 * @ensure result == this - betrag2
	 */
	public Geldbetrag subtrahiere(Geldbetrag betrag) {
		assert betrag != null : "Vorbedingung verletzt: betrag2 != null";
		int minuend = gibWertInEurocent(this);
		int subtrahent = gibWertInEurocent(betrag);
		int differenz = minuend - subtrahent;
		return new Geldbetrag(differenz);
	}
	
	/**
	 * Multiplitziert dieses Geldbetrag mit betrag und gibt das Produkt als Geldbetrag zurück.
	 * 
	 * @param betrag Faktor als positive Ganzzahl (int)
	 * @return result Produkt der Multiplikation als Geldbetrag
	 * 
	 * @require faktor >= 0
	 * 
	 * @ensure result == this * faktor
	 */
	public Geldbetrag multipliziereMit(int faktor) {
		assert faktor >= 0 : "Vorbedingung verletzt: faktor >= 0";
		int faktor1 = gibWertInEurocent(this);
		int produkt = faktor1 * faktor;
		return new Geldbetrag(produkt);
	}
	
	private int gibWertInEurocent(Geldbetrag betrag) {
		return (betrag.getEuroAnteil() * 100) + betrag.getCentAnteil();
	}

	/**
	 * Gibt den Eurobetrag ohne Cent zurück.
	 * 
	 * @return Den Eurobetrag ohne Cent.
	 */
	public int getEuroAnteil() {
		return _euroAnteil;
	}

	/**
	 * Gibt den Centbetrag ohne Eurobetrag zurück.
	 */
	public int getCentAnteil() {
		return _centAnteil;
	}

	/**
	 * Liefert einen formatierten String des Geldbetrags in der Form "10,23" zurück.
	 * 
	 * @return eine String-Repräsentation.
	 */
	public String getFormatiertenString() {
		return _euroAnteil + "," + getFormatiertenCentAnteil();
	}

	/**
	 * Liefert einen zweistelligen Centbetrag zurück.
	 * 
	 * @return eine String-Repräsentation des Cent-Anteils.
	 */
	private String getFormatiertenCentAnteil() {
		String result = "";
		if (_centAnteil < 10) {
			result += "0";
		}
		result += _centAnteil;
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime + _centAnteil;
		result = prime * result + _euroAnteil;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Geldbetrag) {
			Geldbetrag other = (Geldbetrag) obj;
			result = (_centAnteil == other._centAnteil) && (_euroAnteil == other._euroAnteil);
		}
		return result;
	}

	/**
	 * Gibt diesen Geldbetrag in der Form "10,21" zurück.
	 */
	@Override
	public String toString() {
		return getFormatiertenString();
	}
	
	public boolean groesserAls(Geldbetrag betrag) {
		int temp = (this._euroAnteil * 100) + this._centAnteil;
		int temp1 = (betrag._euroAnteil * 100) + betrag._centAnteil;
		
		return temp >= temp1;
	}
}
