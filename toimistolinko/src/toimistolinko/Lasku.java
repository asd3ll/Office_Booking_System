/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

/**
 *
 * @author R01
 */
public class Lasku {
    
    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int laskuNro;
    private double loppusumma;
    private String vastaanottaja;
    private String lahiosoite;
    private String postinumero;
    private String postitoimipaikka;
    private String email;
    private String viite;
    private String erapaiva;  
    private int paperilasku;
    private String pvm;
    private String maksuehto;
    private String kustannuspaikka;
    private int tila;
    private int asiakasNro;
    private int varausNro;
    
    
    public Lasku() {
    }
    
    public Lasku(int laskuNro, double loppusumma, String vastaanottaja, String lahiosoite, 
            String postinumero, String postitoimipaikka, String email, String viite, String erapaiva, 
            int paperilasku, String pvm, String maksuehto, String kustannuspaikka, int tila, int asiakasNro, int varausNro){
        
        this.laskuNro = laskuNro;
        this.loppusumma = loppusumma;
        this.vastaanottaja = vastaanottaja;
        this.lahiosoite = lahiosoite;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
        this.email = email;
        this.viite = viite;
        this.erapaiva = erapaiva;
        this.paperilasku = paperilasku;
        this.pvm = pvm;
        this.maksuehto = maksuehto;
        this.kustannuspaikka = kustannuspaikka;
        this.tila = tila;
        this.asiakasNro = asiakasNro;
        this. varausNro = varausNro;
        
    }
    // getterit ja setterit 

    public int getLaskuNro() {
        return laskuNro;
    }

    public double getLoppuSumma() {
        return loppusumma;
    }

    public String getVastaanottaja() {
        return vastaanottaja;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public String getEmail() {
        return email;
    }

    public String getViite() {
        return viite;
    }
    
    public String getEraPaiva() {
        return erapaiva;
    }

    public int getPaperilasku() {
        return paperilasku;
    }

    public String getPvm() {
        return pvm;
    }

    public String getMaksuehto() {
        return maksuehto;
    }
    
    public String getKustannuspaikka() {
        return kustannuspaikka;
    }

    public int getTila() {
        return tila;
    }

    public int getAsiakasNro() {
        return asiakasNro;
    }

    public int getVarausNro() {
        return varausNro;
    }
    
    public void setLaskuNro(int laskuNro) {
        this.laskuNro = laskuNro;
    }

    public void setLoppusumma(double loppusumma) {
        this.loppusumma = loppusumma;
    }

    public void setVastaanottaja(String vastaanottaja) {
        this.vastaanottaja = vastaanottaja;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setViite(String viite) {
        this.viite = viite;
    }
    
    public void setErapaiva(String erapaiva) {
        this.erapaiva = erapaiva;
    }

    public void setPaperilasku(int paperilasku) {
        this.paperilasku = paperilasku;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }
    
    public void setMaksuehto(String maksuehto) {
        this.maksuehto = maksuehto;
    }

    public void setKustannuspaikka(String kustannuspaikka) {
        this.kustannuspaikka = kustannuspaikka;
    }

    public void setTila(int tila) {
        this.tila = tila;
    }
    
    public void setAsiakasNro(int asiakasNro) {
        this.asiakasNro = asiakasNro;
    }

    public void setVarausNro(int varausNro) {
        this.varausNro = varausNro;
    }
    @Override
    public String toString() {
        return (laskuNro + " " + loppusumma + " " + vastaanottaja + " " + lahiosoite + " " + postinumero + " " + postitoimipaikka 
                + " " + email + " " + viite + " " + erapaiva + " " + paperilasku + " " + pvm 
                + " " + maksuehto + " " + kustannuspaikka + " " + tila + " " + asiakasNro + " " + varausNro);
    }
}
