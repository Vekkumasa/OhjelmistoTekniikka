package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class kassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        this.kassa = new Kassapaate();
        this.kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassassaOikeaMaaraRahaaAlussa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alussaLounaitaEiOleMyyty() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanJalkeenKassassaRahaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanOstonJalkeenEdullisiaMyyty() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanJalkeenKassassaRahaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanOstonJalkeenMaukkaitaMyyty() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuEiOllutRiittavaKassassaRahaa() {
        kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maksuEiRiittanytPaljonkoVaihtoRahaaTakaisin() {
        assertEquals(300, kassa.syoMaukkaasti(300));
    }
    
    @Test
    public void maksuEiRiittanytMyytyjenLounaidenMaaraEiMuutu() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaRiittavastiRahaaMaksuVeloitetaanJaPalautetaanTrue() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertTrue(kassa.syoEdullisesti(kortti));        
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaaKorttiOstoissa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksuEiRiittanytMyytyjenMaukkaidenLounaidenMaaraEiMuutu() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaRiittavastiRahaaMaukkaaseenLounaaseenMaksuVeloitetaanJaPalautetaanTrue() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
        assertTrue(kassa.syoMaukkaasti(kortti));        
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKorttiOstoissa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiKatetta() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void kassanRahatEiMuutuKorttiOstoissa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaRahaaKassanSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void negatiivinenSummaLadattunaEiOnnistu() {
        kassa.lataaRahaaKortille(kortti, -50);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kortillaEiRahaaMaukkaaseenLounaaseen() {
        kortti.otaRahaa(800);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
}
