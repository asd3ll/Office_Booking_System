/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

/**
 *
 * @author R01
 */
public class Palvelu {

    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int paId;
    private String nimi;
    private String kuvaus;
    private double vuokra;
    private int alv;
    private String toimipiste;

    public Palvelu() {
    }

    public Palvelu(int paId, String nimi, String kuvaus, double vuokra, int alv, String toimipiste) {

        this.paId = paId;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.vuokra = vuokra;
        this.alv = alv;
        this.toimipiste = toimipiste;

    }
    // getterit ja setterit 

    public int getPaId() {
        return paId;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public double getVuokra() {
        return vuokra;
    }

    public int getAlv() {
        return alv;
    }

    public String getToimipiste() {
        return toimipiste;
    }

    public void setPaId(int paId) {
        this.paId = paId;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setVuokra(double vuokra) {
        this.vuokra = vuokra;
    }

    public void setAlv(int alv) {
        this.alv = alv;
    }

    public void setToimipiste(String toimipiste) {
        this.toimipiste = toimipiste;
    }

    @Override
    public String toString() {
        return (paId + " " + nimi + " " + kuvaus + " " + vuokra + " " + alv + " " + toimipiste);
    }
}


