//package de.hawhh.informatik.sml.kino.fachwerte;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//public class GeldbetragTest {
//	
//	@Test
//	public final void testGeldbetrag() {
//		Geldbetrag betrag = Geldbetrag.get(100);
//        assertEquals(1, betrag.getEuroAnteil());
//        assertEquals(0, betrag.getCentAnteil());
//        assertEquals(" 1,00", betrag.getFormatiertenString());
//
//        betrag = Geldbetrag.get(0);
//        assertEquals(0, betrag.getEuroAnteil());
//        assertEquals(0, betrag.getCentAnteil());
//        assertEquals("0,00", betrag.getFormatiertenString());
//
//        betrag = Geldbetrag.get(99);
//        assertEquals(0, betrag.getEuroAnteil());
//        assertEquals(99, betrag.getCentAnteil());
//        assertEquals("0,99", betrag.getFormatiertenString());
//
//        betrag = Geldbetrag.get(101);
//        assertEquals(1, betrag.getEuroAnteil());
//        assertEquals(1, betrag.getCentAnteil());
//        assertEquals("1,01", betrag.getFormatiertenString());
//	}
//	
//	@Test
//	public final void testAddition() {
//		Geldbetrag betrag1 = Geldbetrag.get(100);
//		Geldbetrag betrag2 = Geldbetrag.get(200);
//		assertEquals(Geldbetrag.get(300), betrag1.addiere(betrag2));
//	}
//	
//	@Test
//	public final void testSubtraktion() {
//		Geldbetrag betrag1 = Geldbetrag.get(100);
//		Geldbetrag betrag2 = Geldbetrag.get(50);
//		assertEquals(Geldbetrag.get(50), betrag1.subtrahiere(betrag2));
//	}
//	
//	@Test
//	public final void testMultiplikation() {
//		Geldbetrag betrag1 = Geldbetrag.get(100);
//		assertEquals(Geldbetrag.get(1000), betrag1.multipliziereMit(10));
//	}
//	
//	@Test
//    public final void testEqualsHashcode()
//    {
//        Geldbetrag betrag1 = Geldbetrag.get(100);
//        Geldbetrag betrag2 = Geldbetrag.get(100);
//        assertTrue(betrag1.equals(betrag2));
//        assertTrue(betrag1.hashCode() == betrag2.hashCode());
//
//        Geldbetrag betrag3 = Geldbetrag.get(101);
//        assertFalse(betrag1.equals(betrag3));
//        assertFalse(betrag1.hashCode() == betrag3.hashCode());
//
//        Geldbetrag betrag4 = Geldbetrag.get(1000);
//        assertFalse(betrag1.equals(betrag4));
//        assertFalse(betrag1.hashCode() == betrag4.hashCode());
//    }
//}

