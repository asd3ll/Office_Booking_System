/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

/**
 *
 * @author R01
 */
public class Toimisto {

    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int toId;
    private String nimi;
    private double koko;
    private int henkilomaara;
    private double vuokra;
    private String kuvaus;
    private int alv;
    private String toimipiste;

    public Toimisto() {
    }
    
    public Toimisto(int toId, String nimi, double koko, int henkilomaara, double vuokra, String kuvaus, int alv, String toimipiste){
        
        this.toId = toId;
        this.nimi = nimi;
        this.koko = koko;
        this.henkilomaara = henkilomaara;
        this.vuokra = vuokra;
        this.kuvaus = kuvaus;
        this.alv = alv;
        this.toimipiste = toimipiste;
  
    }
    
    
    // getterit ja setterit 

    public int getToId() {
        return toId;
    }

    public String getNimi() {
        return nimi;
    }

    public double getKoko() {
        return koko;
    }

    public int getHenkilomaara() {
        return henkilomaara;
    }

    public double getVuokra() {
        return vuokra;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public int getAlv() {
        return alv;
    }

    public String getToimipiste() {
        return toimipiste;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKoko(double koko) {
        this.koko = koko;
    }

    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public void setVuokra(double vuokra) {
        this.vuokra = vuokra;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setAlv(int alv) {
        this.alv = alv;
    }

    public void setToimipiste(String toimipiste) {
        this.toimipiste = toimipiste;
    }

    @Override
    public String toString() {
        return (toId + " " + nimi + " " + koko + " " + henkilomaara + " " + vuokra + " " + kuvaus + " " + alv + " " + toimipiste);
    }
}

