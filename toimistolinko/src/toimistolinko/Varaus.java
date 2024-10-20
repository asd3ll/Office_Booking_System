/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toimistolinko;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author R01
 */
public class Varaus {

    // attribuutit, vastaavat tietokantataulun sarakkeita
    private int varausNro;
    private double vuokra;
    private double paVuokra;
    private LocalDate alkuPvm;
    private LocalDate loppuPvm;
    private int vuokraAlv;
    private int paAlv;
    private LocalDateTime varausTehty;
    private int toId;
    private int asiakasNro;

    public Varaus() {
    }

    public Varaus(int varausNro, double vuokra, double paVuokra, LocalDate alkuPvm, LocalDate loppuPvm,
            int vuokraAlv, int paAlv, LocalDateTime varausTehty, int toId, int asiakasNro) {

        this.varausNro = varausNro;
        this.vuokra = vuokra;
        this.paVuokra = paVuokra;
        this.alkuPvm = alkuPvm;
        this.loppuPvm = loppuPvm;
        this.vuokraAlv = vuokraAlv;
        this.paAlv = paAlv;
        this.varausTehty = varausTehty;
        this.asiakasNro = asiakasNro;
        
    }

    // getterit ja setterit 
    public int getVarausNro() {
        return varausNro;
    }

    public double getVuokra() {
        return vuokra;
    }

    public double getPaVuokra() {
        return paVuokra;
    }

    public LocalDate getAlkuPvm() {
        return alkuPvm;
    }

    public LocalDate getLoppuPvm() {
        return loppuPvm;
    }

    public int getVuokraAlv() {
        return vuokraAlv;
    }

    public int getPaAlv() {
        return paAlv;
    }

    public LocalDateTime getVarausTehty() {
        return varausTehty;
    }

    public int getToId() {
        return toId;
    }

    public int getAsiakasNro() {
        return asiakasNro;
    }

    public void setVarausNro(int varausNro) {
        this.varausNro = varausNro;
    }

    public void setVuokra(double vuokra) {
        this.vuokra = vuokra;
    }

    public void setPaVuokra(double paVuokra) {
        this.paVuokra = paVuokra;
    }

    public void setAlkuPvm(LocalDate alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    public void setLoppuPvm(LocalDate loppuPvm) {
        this.loppuPvm = loppuPvm;
    }

    public void setVuokraAlv(int vuokraAlv) {
        this.vuokraAlv = vuokraAlv;
    }

    public void setPaAlv(int paAlv) {
        this.paAlv = paAlv;
    }

    public void setVarausTehty(LocalDateTime varausTehty) {
        this.varausTehty = varausTehty;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setAsiakasNro(int asiakasNro) {
        this.asiakasNro = asiakasNro;
    }

    @Override
    public String toString() {
        return (varausNro + " " + vuokra + " " + paVuokra + " " + alkuPvm + " " + loppuPvm
                + " " + vuokraAlv + " " + paAlv + " " + varausTehty + " " + toId + " " + asiakasNro);
    }
}
