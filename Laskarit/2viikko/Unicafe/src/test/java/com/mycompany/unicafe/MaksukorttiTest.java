package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void oikeaMaaraRahaaUudellaKortilla() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortillaLisaamisenJalkeenOikeaMaaraRahaa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMeneMiinukselle() {
        kortti.otaRahaa(40);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahatRiittaaPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void rahatEiRiitaPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(40));
    }
    
    @Test
    public void lisataanNegatiivinenSumma() {
        kortti.lataaRahaa(-10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldonVoiTarkistaa() {
        assertEquals(10, kortti.saldo());
    }
}
