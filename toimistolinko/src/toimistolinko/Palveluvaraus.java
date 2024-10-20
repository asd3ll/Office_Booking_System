/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

/**
 *
 * @author R01
 */
public class Palveluvaraus {

    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int varausNro;
    private int paId;
    private int kpl;

    public Palveluvaraus() {
    }

    public Palveluvaraus(int varausNro, int paId, int kpl) {

        this.varausNro = varausNro;
        this.paId = paId;
        this.kpl = kpl;

    }
    // getterit ja setterit 

    public int getVarausNro() {
        return varausNro;
    }

    public int getPaId() {
        return paId;
    }

    public int getKpl() {
        return kpl;
    }

    public void setVarausNro(int varausNro) {
        this.varausNro = varausNro;
    }

    public void setPaId(int paId) {
        this.paId = paId;
    }

    public void setKpl(int kpl) {
        this.kpl = kpl;
    }

    @Override
    public String toString() {
        return (varausNro + " " + paId + " " + kpl);
    }
}
