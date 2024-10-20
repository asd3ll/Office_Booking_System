/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

/**
 *
 * @author R01
 */
public class Asiakas {

    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int asiakasNro;
    private String hyTunnus;
    private String nimi;
    private String lahiosoite;
    private String postinumero;
    private String postitoimipaikka;
    private String puhelinnumero;
    private String email;
    private String yhteyshenkilo;

    public Asiakas() {
    }

    public Asiakas(int asiakasNro, String hyTunnus, String nimi, String lahiosoite, String postinumero,
            String postitoimipaikka, String puhelinnumero, String email, String yhteyshenkilo) {

        this.asiakasNro = asiakasNro;
        this.hyTunnus = hyTunnus;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
        this.puhelinnumero = puhelinnumero;
        this.email = email;
        this.yhteyshenkilo = yhteyshenkilo;

    }
    // getterit ja setterit 

    public int getAsiakasNro() {
        return asiakasNro;
    }

    public String gethyTunnus() {
        return hyTunnus;
    }

    public String getNimi() {
        return nimi;
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

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public String getEmail() {
        return email;
    }

    public String getYhteyshenkilo() {
        return yhteyshenkilo;
    }

    public void setAsiakasNro(int asiakasNro) {
        this.asiakasNro = asiakasNro;
    }

    public void sethyTunnus(String hyTunnus) {
        this.hyTunnus = hyTunnus;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
    }

    @Override
    public String toString() {
        return (asiakasNro + " " + hyTunnus + " " + nimi + " " + lahiosoite + " " + postinumero + " " + postitoimipaikka + " " + puhelinnumero + " " + email + " " + yhteyshenkilo);
    }
}


