package toimistolinko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author R01
 */
public class Tietokanta {   
    
    
    //I have remove connection. Add the address, name and password of the database and rigth database name.
    private static final String connectionString = "Database anddress, name and password here.";
    private static final String dataBaseName = "your database name here";
    private static Connection con;
    
   
    /**
     * Avaa yhteyden tietokantaan
     * @return connection
     * @throws SQLException
     */
    private static void avaaYhteys() throws SQLException {
        con = DriverManager.getConnection(connectionString);
        System.out.println(">> Yhteys avattu");
    }

    /**
     * Sulkee yhteyden tietokantaan
     * @throws SQLException
     */
    private static void suljeYhteys() throws SQLException {
        if (con != null) {
            con.close();
        }
        System.out.println(">> Yhteys suljettu");
    }
    
    /**
     * Luo tietokannan
     */
    public static void luoTietokanta() {
        
        try {
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("DROP DATABASE IF EXISTS " + dataBaseName);
            System.out.println("\t>> Tietokanta " + dataBaseName + " poistettu");
            
            stmt.executeQuery("CREATE DATABASE " + dataBaseName);
            System.out.println("\t>> Tietokanta " + dataBaseName + " luotu");
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Luo tarvittavat taulut
     */
    public static void luoTaulut() {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            stmt.executeQuery("CREATE TABLE asiakas ("
                    + "asiakasNro INT(6) NOT NULL PRIMARY KEY, "
                    + "hyTunnus VARCHAR(11) NOT NULL, "
                    + "nimi VARCHAR(50) NOT NULL, "
                    + "lahiosoite VARCHAR(50), "
                    + "postinumero VARCHAR(5), "
                    + "postitoimipaikka VARCHAR(25), "
                    + "puhelinnumero VARCHAR(20) NOT NULL, "
                    + "email VARCHAR(50) NOT NULL, "
                    + "yhteyshenkilo VARCHAR(50))"
            );
            
            stmt.executeQuery("CREATE TABLE toimisto ("
                    + "toId INT(6) NOT NULL PRIMARY KEY, "
                    + "nimi VARCHAR(50) NOT NULL, "
                    + "koko DECIMAL(6,2), "
                    + "henkilomaara INT(3), "
                    + "vuokra DECIMAL(6,2) NOT NULL, "
                    + "kuvaus VARCHAR(300), "
                    + "alv INT(3) NOT NULL, "
                    + "toimipiste VARCHAR(50) NOT NULL)"
            );
            
            stmt.executeQuery("CREATE TABLE palvelu ("
                    + "paId INT(6) NOT NULL PRIMARY KEY, "
                    + "nimi VARCHAR(50) NOT NULL, "
                    + "kuvaus VARCHAR(300), "
                    + "vuokra DECIMAL(6,2) NOT NULL, "
                    + "alv INT(3) NOT NULL, "
                    + "toimipiste VARCHAR(50) NOT NULL)"
            );
            
            stmt.executeQuery("CREATE TABLE varaus ("
                    + "varausNro INT(12) NOT NULL PRIMARY KEY, "
                    + "vuokra DECIMAL(6,2) NOT NULL, "
                    + "paVuokra DECIMAL(6,2) NOT NULL, "
                    + "alkuPvm DATE NOT NULL, "
                    + "loppuPvm DATE NOT NULL, "
                    + "vuokraAlv INT(3) NOT NULL, "
                    + "paAlv INT(3) NOT NULL, "
                    + "varausTehty DATETIME NOT NULL, "
                    + "toId INT(6) NOT NULL, "
                    + "asiakasNro INT(6) NOT NULL, "
                    + "FOREIGN KEY (toId) REFERENCES toimisto(toId), "
                    + "FOREIGN KEY (asiakasNro) REFERENCES asiakas(asiakasNro))"
            );
            
            stmt.executeQuery("CREATE TABLE palveluvaraus ("
                    + "varausNro INT(12) NOT NULL, "
                    + "paId INT(6) NOT NULL, "
                    + "kpl INT(3) NOT NULL, "
                    + "PRIMARY KEY (varausNro, paId), "
                    + "FOREIGN KEY (paId) REFERENCES palvelu(paId), "
                    + "FOREIGN KEY (varausNro) REFERENCES varaus(varausNro))"
            );
            
            stmt.executeQuery("CREATE TABLE lasku ("
                    + "laskuNro INT(12) NOT NULL PRIMARY KEY, "
                    + "loppusumma DECIMAL(7,2) NOT NULL, "
                    + "vastaanottaja VARCHAR(50) NOT NULL, "
                    + "lahiosoite VARCHAR(50), "
                    + "postinumero VARCHAR(5), "
                    + "postitoimipaikka VARCHAR(25), "
                    + "email VARCHAR(50) NOT NULL, "
                    + "viite VARCHAR(30), "
                    + "erapaiva DATE NOT NULL, "
                    + "paperilasku INT(1), "
                    + "pvm DATE NOT NULL, "
                    + "maksuehto VARCHAR(30), "
                    + "kustannuspaikka VARCHAR(30), "
                    + "tila INT(1) NOT NULL, "
                    + "asiakasNro INT(6) NOT NULL, "
                    + "varausNro INT(12) NOT NULL, "
                    + "FOREIGN KEY (asiakasNro) REFERENCES asiakas(asiakasNro), "
                    + "FOREIGN KEY (varausNro) REFERENCES varaus(varausNro))"
            );

            System.out.println("\t>> Taulut luotu");
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Lisää testidatan
     */
    public static void lisaaTestidata() {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            stmt.executeQuery("INSERT INTO `asiakas` (`asiakasNro`, `hyTunnus`, `nimi`, `lahiosoite`, `postinumero`, `postitoimipaikka`, `puhelinnumero`, `email`, `yhteyshenkilo`) VALUES"
                    + "	(1, '010101A0101', 'Esko Kepponen', 'Vislauskuja 3', '90100', 'Oulu', '0450101010', 'esko@roskaposti.fi', NULL),"
                    + "	(2, '0202020-2', 'Paukun Betoni Oy', 'Siihtalanpussi 5', '80100', 'Joensuu', '020020020', 'toimisto@paukkub.fi', 'Oiva Paukku'),"
                    + "	(3, '030303A0303', 'Mari Tonsikka', 'Säkkijärvenkuja 6', '80200', 'Joensuu', '0303300333', 'mari@hillo.net', NULL),"
                    + "	(4, '0404040-4', 'Iitan Strösseli Ky', 'Pajakuja 1', '53100', 'Lappeenranta', '0440400404', 'iita@strosseli.fi', 'Iita Virtanen')"
            );
            
            stmt.executeQuery("INSERT INTO `toimisto` (`toId`, `nimi`, `koko`, `henkilomaara`, `vuokra`, `kuvaus`, `alv`, `toimipiste`) VALUES"
                    + "	(1, 'Mega-Neukkari', 80.00, 35, 85.00, 'Yllättävän tilava neuvotteluhuone, jossa pitkä pöytä ja paljon istuimia.', 0, 'Joensuu'),"
                    + "	(2, 'MiniKoppi2', 5.00, 1, 12.00, 'Yllättävän tilava työskentelytila, jossa pöytä ja istuin.', 0, 'Joensuu'),"
                    + "	(3, 'Tilaihme', 100.00, 80, 122.00, 'Yllättävän tilava työskentelytila, jossa paljon pöytiä ja istuimia.', 0, 'Joensuu'),"
                    + "	(4, 'Pikku-Sahara', 75.00, 20, 60.00, 'Yllättävän tilava työskentelytila, jossa pehmustetut seinät ja lattiat. Pöydät ja tuolit kevyesti pehmustettuja.', 0, 'Lappeenranta'),"
                    + "	(5, 'Laiskurin piilo', 12.00, 2, 24.00, 'Yllättävän ahdas tila, jossa mukitelineet ja säkkituolit kahdelle henkilölle.', 0, 'Lappeenranta'),"
                    + "	(6, 'Sopu-sopukka', 14.00, 3, 32.00, 'Muuten vaan yllättävä tila, jonka saa nopeasti jakautumaan erimielisyyksien mukaan.', 0, 'Oulu'),"
                    + "	(7, 'Viinikellari', 120.00, 66, 135.00, 'Varsin tunnelmallinen tila. Tynnyreiden hanat toimivat kolikkoautomaateilla. Tynnyreissä alkoholiton Pepsi.', 0, 'Oulu')"
            );
            
            stmt.executeQuery("INSERT INTO `palvelu` (`paId`, `nimi`, `kuvaus`, `vuokra`, `alv`, `toimipiste`) VALUES"
                    + "	(1, 'Laiskanlinna', 'Upottava, keinumekanismi narisee.', 12.00, 0, 'Joensuu'),"
                    + "	(2, 'Tulostin', 'Värilaser', 20.00, 0, 'Joensuu'),"
                    + "	(3, 'Työntekijä', 'Valmiiksi työhönsä kyllästynyt vuokratyöntekijä.', 120.00, 0, 'Joensuu'),"
                    + "	(4, 'Huonekasvi', 'Värit haalistuneet auringopaisteesta, kangasta.', 5.00, 0, 'Lappeenranta'),"
                    + "	(5, 'Silppuri', 'Tuhoaa melkein mitä vain.', 15.00, 0, 'Lappeenranta'),"
                    + "	(6, 'Pomppulinna', 'Pientä tuoksua aiemmista virikepäivistä. Kolmas torni tahmea.', 150.00, 0, 'Oulu'),"
                    + "	(7, 'Juoma-automaatti', 'Valmistaa erilaisia kahvi ja kaakaojuomia. Saatavilla myös mehukeitto- ja limonadi-versiot.', 90.00, 0, 'Oulu')"
            );
            
            stmt.executeQuery("INSERT INTO `varaus` (`varausNro`, `vuokra`, `paVuokra`, `alkuPvm`, `loppuPvm`, `vuokraAlv`, `paAlv`, `varausTehty`, `toId`, `asiakasNro`) VALUES"
                    + "	(1, 135.00, 450.00, '2022-04-25', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 7, 1),"
                    + "	(2, 24.00, 15.00, '2022-04-20', '2022-04-25', 0, 0, '2022-04-30 15:06:47', 5, 4),"
                    + "	(3, 122.00, 0.00, '2022-04-20', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 3, 2),"
                    + "	(4, 85.00, 0.00, '2022-04-01', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 1, 3)"
            );

            stmt.executeQuery("INSERT INTO `palveluvaraus` (`varausNro`, `paId`, `kpl`) VALUES"
                    + "	(1, 7, 5),"
                    + "	(2, 5, 1)"
            );
            
            stmt.executeQuery("INSERT INTO `lasku` (`laskuNro`, `loppusumma`, `vastaanottaja`, `lahiosoite`, `postinumero`, `postitoimipaikka`, `email`, `viite`, `erapaiva`, `paperilasku`, `pvm`, `maksuehto`, `kustannuspaikka`, `tila`, `asiakasNro`, `varausNro`) VALUES"
                    + "	(1, 1464.00, 'Iitan Strösseli Ky', 'Pajakuja 1', '53100', 'Lappeenranta', 'iita@strosseli.fi', '00000001', '2022-05-14', 1, '2022-05-15', '14pv netto', '86868668', 1, 4, 3),"
                    + "	(2, 234.00, 'Mari Tonsikka', 'Säkkijärvenkuja 6', '80200', 'Joensuu', 'mari@hillo.net', NULL, '2022-05-14', NULL, '2022-05-10', '14pv netto', NULL, 4, 3, 2),"
                    + "	(3, 2550.00, 'Paukun Betoni Oy', 'Siihtalanpussi 5', '80100', 'Joensuu', 'toimisto@paukkub.fi', '00000003', '2022-06-04', NULL, '2022-05-05', '30pv netto', '25252522', 1, 2, 4),"
                    + "	(4, 3510.00, 'Esko Kepponen', 'Vislauskuja 3', '90100', 'Oulu', 'esko@roskaposti.fi', NULL, '2022-05-14', NULL, '2022-05-01', '14pv netto', NULL, 1, 1, 1)"
            );

            System.out.println("\t>> Testidata lisatty");
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Lisää asiakkaan tietokantaan
     * @param a
     */
    public static void lisaaAsiakas(Asiakas a) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO asiakas "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            ps.setInt(1, a.getAsiakasNro());
            ps.setString(2, a.gethyTunnus());
            ps.setString(3, a.getNimi());
            ps.setString(4, a.getLahiosoite());
            ps.setString(5, a.getPostinumero());
            ps.setString(6, a.getPostitoimipaikka());
            ps.setString(7, a.getPuhelinnumero());
            ps.setString(8, a.getEmail());
            ps.setString(9, a.getYhteyshenkilo());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + a.getAsiakasNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hakee asiakkaan asiakasNrolla, palauttaa tyhjän olion jos ei löydy.
     * @param asiakasNro
     * @return Asiakas a
     */
    public static Asiakas haeAsiakas(int asiakasNro) {
        
        Asiakas a = new Asiakas();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM asiakas "
                    + "WHERE asiakasNro = " + asiakasNro
            );
            
            if (rs.next()) {
                a.setAsiakasNro(rs.getInt("asiakasNro"));
                a.sethyTunnus(rs.getString("hyTunnus"));
                a.setNimi(rs.getString("nimi"));
                a.setLahiosoite(rs.getString("lahiosoite"));
                a.setPostinumero(rs.getString("postinumero"));
                a.setPostitoimipaikka(rs.getString("postitoimipaikka"));
                a.setPuhelinnumero(rs.getString("puhelinnumero"));
                a.setEmail(rs.getString("email"));
                a.setYhteyshenkilo(rs.getString("yhteyshenkilo"));
                System.out.println("\t>> Tiedot haettu asiakkaasta " + asiakasNro);
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
        
    }    
    
    /**
     * Päivittää asiakkaan tiedot Asiakas-olion asiakasNron perusteella
     * @param a
     */
    public static void muokkaaAsiakas(Asiakas a) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE asiakas "
                            + "SET hyTunnus = ?, nimi = ?, lahiosoite = ?, postinumero = ?, postitoimipaikka = ?, puhelinnumero = ?, email = ?, yhteyshenkilo = ? "
                            + "WHERE asiakasNro = ?"
            );
            
            ps.setString(1, a.gethyTunnus());
            ps.setString(2, a.getNimi());
            ps.setString(3, a.getLahiosoite());
            ps.setString(4, a.getPostinumero());
            ps.setString(5, a.getPostitoimipaikka());
            ps.setString(6, a.getPuhelinnumero());
            ps.setString(7, a.getEmail());
            ps.setString(8, a.getYhteyshenkilo());
            ps.setInt(9, a.getAsiakasNro());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty asiakas " + a.getAsiakasNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa asiakkaan tietokannasta asiakasNro:n perusteella
     * @param asiakasNro
     */
    public static void poistaAsiakas(int asiakasNro) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM asiakas "
                            + "WHERE asiakasNro = ?"
            );
            ps.setInt(1, asiakasNro);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu asiakas " + asiakasNro);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lisää toimiston tietokantaan
     * @param t
     */
    public static void lisaaToimisto(Toimisto t) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO toimisto "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            ps.setInt(1, t.getToId());
            ps.setString(2, t.getNimi());
            ps.setDouble(3, t.getKoko());
            ps.setInt(4, t.getHenkilomaara());
            ps.setDouble(5, t.getVuokra());
            ps.setString(6, t.getKuvaus());
            ps.setInt(7, t.getAlv());
            ps.setString(8, t.getToimipiste());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + t.getToId());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hakee toimiston toId:llä, palauttaa tyhjän olion jos ei löydy.
     * @param toId
     * @return Toimisto t
     */
    public static Toimisto haeToimisto(int toId) {
        
        Toimisto t = new Toimisto();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM toimisto "
                    + "WHERE toId = " + toId
            );
            
            if (rs.next()) {
                t.setToId(rs.getInt("toId"));
                t.setNimi(rs.getString("nimi"));
                t.setKoko(rs.getDouble("koko"));
                t.setHenkilomaara(rs.getInt("henkilomaara"));
                t.setVuokra(rs.getDouble("vuokra"));
                t.setKuvaus(rs.getString("kuvaus"));
                t.setAlv(rs.getInt("alv"));
                t.setToimipiste(rs.getString("toimipiste"));
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return t;
        
    }    
    
    /**
     * Päivittää toimiston tiedot Toimisto-olion toId:n perusteella
     * @param t
     */
    public static void muokkaaToimisto(Toimisto t) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE toimisto "
                            + "SET nimi = ?, koko = ?, henkilomaara = ?, vuokra = ?, kuvaus = ?, alv = ?, toimipiste = ? "
                            + "WHERE toId = ?"
            );
            
            ps.setString(1, t.getNimi());
            ps.setDouble(2, t.getKoko());
            ps.setInt(3, t.getHenkilomaara());
            ps.setDouble(4, t.getVuokra());
            ps.setString(5, t.getKuvaus());
            ps.setInt(6, t.getAlv());
            ps.setString(7, t.getToimipiste());
            ps.setInt(8, t.getToId());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty toimisto " +t.getToId());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa toimiston tietokannasta toId:n perusteella
     * @param toId
     */
    public static void poistaToimisto(int toId) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM toimisto "
                            + "WHERE toId = ?"
            );
            ps.setInt(1, toId);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu toimisto " + toId);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lisää palvelun tietokantaan
     * @param p
     */
    public static void lisaaPalvelu(Palvelu p) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO palvelu "
                            + "VALUES(?, ?, ?, ?, ?, ?)"
            );
            
            ps.setInt(1, p.getPaId());
            ps.setString(2, p.getNimi());
            ps.setString(3, p.getKuvaus());
            ps.setDouble(4, p.getVuokra());
            ps.setInt(5, p.getAlv());
            ps.setString(6, p.getToimipiste());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + p.getPaId());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     * Hakee palvelun paId:llä, palauttaa tyhjän olion jos ei löydy.
     * @param paId
     * @return Toimisto t
     */
    public static Palvelu haePalvelu(int paId) {
        
        Palvelu p = new Palvelu();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM palvelu "
                    + "WHERE paId = " + paId
            );
            
            if (rs.next()) {
                p.setPaId(rs.getInt("paId"));
                p.setNimi(rs.getString("nimi"));
                p.setKuvaus(rs.getString("kuvaus"));
                p.setVuokra(rs.getDouble("vuokra"));
                p.setAlv(rs.getInt("alv"));
                p.setToimipiste(rs.getString("toimipiste"));
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
        
    }    
    
    /**
     * Päivittää palvelun tiedot Palvelu-olion paId:n perusteella
     * @param p
     */
    public static void muokkaaPalvelu(Palvelu p) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE palvelu "
                            + "SET nimi = ?, kuvaus = ?, vuokra = ?, alv = ?, toimipiste = ? "
                            + "WHERE paId = ?"
            );
            
            ps.setString(1, p.getNimi());
            ps.setString(2, p.getKuvaus());
            ps.setDouble(3, p.getVuokra());
            ps.setInt(4, p.getAlv());
            ps.setString(5, p.getToimipiste());
            ps.setInt(6, p.getPaId());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty palvelu" +p.getPaId());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa palvelun tietokannasta paId:n perusteella
     * @param paId
     */
    public static void poistaPalvelu(int paId) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM palvelu "
                            + "WHERE paId = ?"
            );
            ps.setInt(1, paId);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu palvelu " + paId);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lisää palveluvarauksen tietokantaan
     * @param pv
     */
    public static void lisaaPalveluvaraus(Palveluvaraus pv) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO palveluvaraus "
                            + "VALUES(?, ?, ?)"
            );
            
            ps.setInt(1, pv.getVarausNro());
            ps.setInt(2, pv.getPaId());
            ps.setInt(3, pv.getKpl());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + pv.getVarausNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 
    /**
     * Hakee palveluvarauksen varausNrolla, palauttaa tyhjän olion jos ei löydy.
     * @param varausNro
     * @param paId
     * @return Palveluvaraus p
     */
    public static Palveluvaraus haePalveluvaraus(int varausNro, int paId) {
        
        Palveluvaraus p = new Palveluvaraus();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM palveluvaraus "
                    + "WHERE varausNro = " + varausNro
                    + " AND paId = " + paId
            );
            
            if (rs.next()) {
                p.setVarausNro(rs.getInt("varausNro"));
                p.setPaId(rs.getInt("paId"));
                p.setKpl(rs.getInt("kpl"));
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
        
    }

    /**
     * Hakee palveluvaraukset varausNrolla
     * @param varausNro
     * @return ObservableList p
     */
    public static ObservableList<Map<String, Object>> haePalveluvarausLista(int varausNro) {
        
        ObservableList<Map<String, Object>> paTiedot = FXCollections.<Map<String, Object>>observableArrayList();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT pv.paId, p.nimi, pv.kpl "
                    + "FROM palveluvaraus pv, palvelu p "
                    + "WHERE pv.paId = p.paId AND varausNro = " + varausNro
                    + " GROUP BY pv.paId"
            );
            
            while (rs.next()) {
                Map<String, Object> rivi = new HashMap<>();
                rivi.put("paId", rs.getString("pv.paId"));
                rivi.put("paNimi", rs.getString("p.nimi"));
                rivi.put("paKpl", rs.getString("pv.kpl"));
                paTiedot.add(rivi);
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paTiedot;
        
    }
    
    /**
     * Päivittää palveluvarauksen tiedot Palvelu-olion varausNro:n ja paId:n perusteella
     * @param p
     */
    public static void muokkaaPalveluvaraus(Palveluvaraus p) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE palveluvaraus "
                            + "SET kpl = ? "
                            + "WHERE varausNro = ? "
                            + "AND paId = ? "
            );
            
            ps.setInt(1, p.getKpl());
            ps.setInt(2, p.getVarausNro());
            ps.setInt(3, p.getPaId());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty palveluvaraus " + p.getPaId() + " - " + p.getVarausNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa palveluvarauksen tietokannasta varausNro:n ja paId:n perusteella
     * @param varausNro
     * @param paId
     */
    public static void poistaPalveluvaraus(int varausNro, int paId) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM palveluvaraus "
                            + "WHERE varausNro = ? "
                            + "AND paId = ?"
            );
            ps.setInt(1, varausNro);
            ps.setInt(2, paId);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu palveluvaraus " + varausNro + " - " + paId);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lisää varauksen tietokantaan
     * @param v
     */
    public static void lisaaVaraus(Varaus v) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO varaus "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            ps.setInt(1, v.getVarausNro());
            ps.setDouble(2, v.getVuokra());
            ps.setDouble(3, v.getPaVuokra());
            ps.setDate(4, java.sql.Date.valueOf(v.getAlkuPvm()));
            ps.setDate(5, java.sql.Date.valueOf(v.getLoppuPvm()));
            ps.setInt(6, v.getVuokraAlv());
            ps.setInt(7, v.getPaAlv());
            ps.setTimestamp(8, Timestamp.valueOf(v.getVarausTehty()));
            ps.setInt(9, v.getToId());
            ps.setInt(10, v.getAsiakasNro());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + v.getVarausNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Hakee varauksen varausNrolla, palauttaa tyhjän olion jos ei löydy.
     * @param varausNro
     * @return Varaus v
     */
    public static Varaus haeVaraus(int varausNro) {
        
        Varaus v = new Varaus();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM varaus "
                    + "WHERE varausNro = " + varausNro
            );
            
            if (rs.next()) {
                v.setVarausNro(rs.getInt("varausNro"));
                v.setVuokra(rs.getDouble("vuokra"));
                v.setPaVuokra(rs.getDouble("paVuokra"));
                v.setAlkuPvm(rs.getDate("alkuPvm").toLocalDate());
                v.setLoppuPvm(rs.getDate("loppuPvm").toLocalDate());
                v.setVuokraAlv(rs.getInt("vuokraAlv"));
                v.setPaAlv(rs.getInt("paAlv"));
                v.setVarausTehty(rs.getTimestamp("varausTehty").toLocalDateTime());
                v.setToId(rs.getInt("toId"));
                v.setAsiakasNro(rs.getInt("asiakasNro"));
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;
        
    }        
    
    /**
     * Päivittää varauksen tiedot Varaus-olion varausNro:n perusteella
     * @param v
     */
    public static void muokkaaVaraus(Varaus v) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE varaus "
                            + "SET vuokra = ?, paVuokra = ?, alkuPvm = ?, loppuPvm = ?, vuokraAlv = ?, paAlv = ?, varausTehty = ?, toId = ?, asiakasNro = ? "
                            + "WHERE varausNro = ?"
            );
            
            ps.setDouble(1, v.getVuokra());
            ps.setDouble(2, v.getPaVuokra());
            ps.setDate(3, java.sql.Date.valueOf(v.getAlkuPvm()));
            ps.setDate(4, java.sql.Date.valueOf(v.getLoppuPvm()));
            ps.setInt(5, v.getVuokraAlv());
            ps.setInt(6, v.getPaAlv());
            ps.setTimestamp(7, Timestamp.valueOf(v.getVarausTehty()));
            ps.setInt(8, v.getToId());
            ps.setInt(9, v.getAsiakasNro());
            ps.setInt(10, v.getVarausNro());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty varaus " + v.getVarausNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa varauksen tietokannasta varausNro:n perusteella
     * @param varausNro
     */
    public static void poistaVaraus(int varausNro) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM varaus "
                            + "WHERE varausNro = ?"
            );
            ps.setInt(1, varausNro);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu varaus " + varausNro);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lisää laskun tietokantaan
     * @param l
     */
    public static void lisaaLasku(Lasku l) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO lasku "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            ps.setInt(1, l.getLaskuNro());
            ps.setDouble(2, l.getLoppuSumma());
            ps.setString(3, l.getVastaanottaja());
            ps.setString(4, l.getLahiosoite());
            ps.setString(5, l.getPostinumero());
            ps.setString(6, l.getPostitoimipaikka());
            ps.setString(7, l.getEmail());
            ps.setString(8, l.getViite());
            ps.setDate(9, java.sql.Date.valueOf(l.getEraPaiva()));
            ps.setInt(10, l.getPaperilasku());
            ps.setDate(11, java.sql.Date.valueOf(l.getPvm()));
            ps.setString(12, l.getMaksuehto());
            ps.setString(13, l.getKustannuspaikka());
            ps.setInt(14, l.getTila());
            ps.setInt(15, l.getAsiakasNro());
            ps.setInt(16, l.getVarausNro());
            ps.execute();
            System.out.println("\t>> Tauluun lisatty " + l.getLaskuNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Hakee laskun laskuNrolla, palauttaa tyhjän olion jos ei löydy.
     * @param laskuNro
     * @return Lasku l
     */
    public static Lasku haeLasku(int laskuNro) {
        
        Lasku l = new Lasku();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM lasku "
                    + "WHERE laskuNro = " + laskuNro
            );
            
            if (rs.next()) {
                l.setLaskuNro(rs.getInt("laskuNro"));
                l.setLoppusumma(rs.getDouble("loppusumma"));
                l.setVastaanottaja(rs.getString("vastaanottaja"));
                l.setLahiosoite(rs.getString("lahiosoite"));
                l.setPostinumero(rs.getString("postinumero"));
                l.setPostitoimipaikka(rs.getString("postitoimipaikka"));
                l.setEmail(rs.getString("email"));
                l.setViite(rs.getString("viite"));
                l.setErapaiva(rs.getDate("erapaiva").toString());
                l.setPaperilasku(rs.getInt("paperilasku"));
                l.setPvm(rs.getDate("pvm").toString());
                l.setMaksuehto(rs.getString("maksuehto"));
                l.setKustannuspaikka(rs.getString("kustannuspaikka"));
                l.setTila(rs.getInt("tila"));
                l.setAsiakasNro(rs.getInt("asiakasNro"));
                l.setVarausNro(rs.getInt("varausNro"));
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
        
    }        
    
    /**
     * Päivittää laskun tiedot Lasku-olion laskuNro:n perusteella
     * @param l
     */
    public static void muokkaaLasku(Lasku l) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE lasku "
                            + "SET loppusumma = ?, "
                            + "vastaanottaja = ?, "
                            + "lahiosoite = ?, "
                            + "postinumero = ?, "
                            + "postitoimipaikka = ?, "
                            + "email = ?, "
                            + "viite = ?, "
                            + "erapaiva = ?, "
                            + "paperilasku = ?, "
                            + "pvm = ?, "
                            + "maksuehto = ?, "
                            + "kustannuspaikka = ?, "
                            + "tila = ?, "
                            + "asiakasNro = ?, "
                            + "varausNro = ? "
                            + "WHERE laskuNro = ?"
            );
            
            ps.setDouble(1, l.getLoppuSumma());
            ps.setString(2, l.getVastaanottaja());
            ps.setString(3, l.getLahiosoite());
            ps.setString(4, l.getPostinumero());
            ps.setString(5, l.getPostitoimipaikka());
            ps.setString(6, l.getEmail());
            ps.setString(7, l.getViite());
            ps.setDate(8, java.sql.Date.valueOf(l.getEraPaiva()));
            ps.setInt(9, l.getPaperilasku());
            ps.setDate(10, java.sql.Date.valueOf(l.getPvm()));
            ps.setString(11, l.getMaksuehto());
            ps.setString(12, l.getKustannuspaikka());
            ps.setInt(13, l.getTila());
            ps.setInt(14, l.getAsiakasNro());
            ps.setInt(15, l.getVarausNro());
            ps.setInt(16, l.getLaskuNro());
            ps.execute();
            System.out.println("\t>> Tauluun päivitetty lasku " + l.getLaskuNro());
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Poistaa laskun tietokannasta laskuNro:n perusteella
     * @param laskuNro
     */
    public static void poistaLasku(int laskuNro) {
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM lasku "
                            + "WHERE laskuNro = ?"
            );
            ps.setInt(1, laskuNro);
            ps.execute();
            System.out.println("\t>> Taulusta poistettu lasku " + laskuNro);
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Hakee varaukset listaan, jotka alkaa tai päättyy annettujen päivämäärien välissä ja valitussa toimipisteessä, palauttaa tyhjän listan jos ei löydy.
     * @param alkupvm
     * @param loppupvm
     * @param toimipiste
     * @return varaukset
     */
    public static ArrayList<Varaus> haeVaratutTilat(LocalDate alkupvm, LocalDate loppupvm, String toimipiste) {
        
        ArrayList<Varaus> varaukset = new ArrayList<>();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM varaus "
                    + "WHERE alkuPvm BETWEEN '" + alkupvm.toString()
                    + "' AND '" + loppupvm.toString()
                    + "' OR loppuPvm BETWEEN '" + alkupvm.toString()
                    + "' AND '" + loppupvm.toString()
                    + "'"
            );
            
            while (rs.next()) {
                
                Varaus v = new Varaus();
                Toimisto t = new Toimisto();
                
                v.setVarausNro(rs.getInt("varausNro"));
                v.setVuokra(rs.getDouble("vuokra"));
                v.setPaVuokra(rs.getDouble("paVuokra"));
                v.setAlkuPvm(rs.getDate("alkuPvm").toLocalDate());
                v.setLoppuPvm(rs.getDate("loppuPvm").toLocalDate());
                v.setVuokraAlv(rs.getInt("vuokraAlv"));
                v.setPaAlv(rs.getInt("paAlv"));
                v.setVarausTehty(rs.getTimestamp("varausTehty").toLocalDateTime());
                v.setToId(rs.getInt("toId"));
                v.setAsiakasNro(rs.getInt("asiakasNro"));
                
                t = haeToimisto(v.getToId());
                
                if (t.getToimipiste().equalsIgnoreCase(toimipiste)){
                    varaukset.add(v);
                }
                
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return varaukset;
        
    }       
    
    /**
     * Hakee varatut palvelut listaan, jotka alkaa tai päättyy annettujen päivämäärien välissä ja valitussa toimipisteessä, palauttaa tyhjän listan jos ei löydy.
     * @param alkupvm
     * @param loppupvm
     * @param toimipiste
     * @return palvelut
     */
    public static ArrayList<Palveluvaraus> haeVaratutPalvelut(LocalDate alkupvm, LocalDate loppupvm, String toimipiste) {
        
        ArrayList<Palveluvaraus> palvelut = new ArrayList<>();
        
        try {
            
            avaaYhteys();
            
            Statement stmt = con.createStatement();
            
            stmt.executeQuery("USE " + dataBaseName);
            System.out.println("\t>> Kaytetaan tietokantaa " + dataBaseName);
            
            ResultSet rs = stmt.executeQuery("SELECT pv.varausNro, pv.paId, pv.kpl "
                    + "FROM varaus v, palvelu p, palveluvaraus pv "
                    + "WHERE pv.paId = p.paId "
                    + "AND pv.varausNro = v.varausNro "
                    + "AND p.toimipiste = " + '"' + toimipiste + '"'
                    + "AND pv.varausNro IN ("
                            + "SELECT varausNro "
                            + "FROM varaus "
                            + "WHERE (alkuPvm BETWEEN '" + alkupvm + "' AND '" + loppupvm + "') "
                                    + "OR (loppuPvm BETWEEN '" + alkupvm + "' AND '" + loppupvm + "'))"
            );
            
            while (rs.next()) {
                
                Palveluvaraus v = new Palveluvaraus();
                
                v.setVarausNro(rs.getInt("pv.varausNro"));
                v.setPaId(rs.getInt("pv.paId"));
                v.setKpl(rs.getInt("pv.kpl"));
                
                palvelut.add(v);
                
            }
            
            suljeYhteys();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return palvelut;
        
    }        
}
