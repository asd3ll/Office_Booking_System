-- Luodaan tietokanta ja otetaan se käyttöön
DROP DATABASE IF EXISTS dataBaseNameHere;
CREATE DATABASE IF NOT EXISTS dataBaseNameHere;
USE dataBaseNameHere;


-- luodaan taulut
CREATE TABLE asiakas
(
  asiakasNro INT(6) NOT NULL PRIMARY KEY,
  hyTunnus VARCHAR(11) NOT NULL,
  nimi VARCHAR(50) NOT NULL,
  lahiosoite VARCHAR(50),
  postinumero VARCHAR(5),
  postitoimipaikka VARCHAR(25),
  puhelinnumero VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL,
  yhteyshenkilo VARCHAR(50)
);

CREATE TABLE toimisto
(
  toId INT(6) NOT NULL PRIMARY KEY,
  nimi VARCHAR(50) NOT NULL,
  koko DECIMAL(6,2),
  henkilomaara INT(3),
  vuokra DECIMAL(6,2) NOT NULL,
  kuvaus VARCHAR(300),
  alv INT(3) NOT NULL,
  toimipiste VARCHAR(50) NOT NULL
);

CREATE TABLE palvelu
(
  paId INT(6) NOT NULL PRIMARY KEY,
  nimi VARCHAR(50) NOT NULL,
  kuvaus VARCHAR(300),
  vuokra DECIMAL(6,2) NOT NULL,
  alv INT(3) NOT NULL,
  toimipiste VARCHAR(50) NOT NULL
);

CREATE TABLE varaus
(
  varausNro INT(12) NOT NULL PRIMARY KEY,
  vuokra DECIMAL(6,2) NOT NULL,
  paVuokra DECIMAL(6,2) NOT NULL,
  alkuPvm DATE NOT NULL,
  loppuPvm DATE NOT NULL,
  vuokraAlv INT(3) NOT NULL,
  paAlv INT(3) NOT NULL,
  varausTehty DATETIME NOT NULL,
  toId INT(6) NOT NULL,
  asiakasNro INT(6) NOT NULL,
  FOREIGN KEY (toId) REFERENCES toimisto(toId),
  FOREIGN KEY (asiakasNro) REFERENCES asiakas(asiakasNro)
);

CREATE TABLE palveluvaraus
(
  varausNro INT(12) NOT NULL,
  paId INT(6) NOT NULL,
  kpl INT(3) NOT NULL,
  PRIMARY KEY (varausNro, paId),
  FOREIGN KEY (paId) REFERENCES palvelu(paId),
  FOREIGN KEY (varausNro) REFERENCES varaus(varausNro)
);

CREATE TABLE lasku
(
  laskuNro INT(12) NOT NULL PRIMARY KEY,
  loppusumma DECIMAL(7,2) NOT NULL,
  vastaanottaja VARCHAR(50) NOT NULL,
  lahiosoite VARCHAR(50),
  postinumero VARCHAR(5),
  postitoimipaikka VARCHAR(25),
  email VARCHAR(50) NOT NULL,
  viite VARCHAR(30),
  erapaiva DATE NOT NULL,
  paperilasku INT(1),
  pvm DATE NOT NULL,
  maksuehto VARCHAR(30),
  kustannuspaikka VARCHAR(30),
  tila INT(1) NOT NULL,
  asiakasNro INT(6) NOT NULL,
  varausNro INT(12) NOT NULL,
  FOREIGN KEY (asiakasNro) REFERENCES asiakas(asiakasNro),
  FOREIGN KEY (varausNro) REFERENCES varaus(varausNro)
);


-- Luodaan tiedot (kesken)
INSERT INTO `asiakas` (`asiakasNro`, `hyTunnus`, `nimi`, `lahiosoite`, `postinumero`, `postitoimipaikka`, `puhelinnumero`, `email`, `yhteyshenkilo`) VALUES
	(1, '010101A0101', 'Esko Kepponen', 'Vislauskuja 3', '90100', 'Oulu', '0450101010', 'esko@roskaposti.fi', NULL),
	(2, '0202020-2', 'Paukun Betoni Oy', 'Siihtalanpussi 5', '80100', 'Joensuu', '020020020', 'toimisto@paukkub.fi', 'Oiva Paukku'),
	(3, '030303A0303', 'Mari Tonsikka', 'Säkkijärvenkuja 6', '80200', 'Joensuu', '0303300333', 'mari@hillo.net', NULL),
	(4, '0404040-4', 'Iitan Strösseli Ky', 'Pajakuja 1', '53100', 'Lappeenranta', '0440400404', 'iita@strosseli.fi', 'Iita Virtanen');
	
INSERT INTO `toimisto` (`toId`, `nimi`, `koko`, `henkilomaara`, `vuokra`, `kuvaus`, `alv`, `toimipiste`) VALUES
	(1, 'Mega-Neukkari', 80.00, 35, 85.00, 'Yllättävän tilava neuvotteluhuone, jossa pitkä pöytä ja paljon istuimia.', 0, 'Joensuu'),
	(2, 'MiniKoppi2', 5.00, 1, 12.00, 'Yllättävän tilava työskentelytila, jossa pöytä ja istuin.', 0, 'Joensuu'),
	(3, 'Tilaihme', 100.00, 80, 122.00, 'Yllättävän tilava työskentelytila, jossa paljon pöytiä ja istuimia.', 0, 'Joensuu'),
	(4, 'Pikku-Sahara', 75.00, 20, 60.00, 'Yllättävän tilava työskentelytila, jossa pehmustetut seinät ja lattiat. Pöydät ja tuolit kevyesti pehmustettuja.', 0, 'Lappeenranta'),
	(5, 'Laiskurin piilo', 12.00, 2, 24.00, 'Yllättävän ahdas tila, jossa mukitelineet ja säkkituolit kahdelle henkilölle.', 0, 'Lappeenranta'),
	(6, 'Sopu-sopukka', 14.00, 3, 32.00, 'Muuten vaan yllättävä tila, jonka saa nopeasti jakautumaan erimielisyyksien mukaan.', 0, 'Oulu'),
	(7, 'Viinikellari', 120.00, 66, 135.00, 'Varsin tunnelmallinen tila. Tynnyreiden hanat toimivat kolikkoautomaateilla. Tynnyreissä alkoholiton Pepsi.', 0, 'Oulu');
	
INSERT INTO `palvelu` (`paId`, `nimi`, `kuvaus`, `vuokra`, `alv`, `toimipiste`) VALUES
	(1, 'Laiskanlinna', 'Upottava, keinumekanismi narisee.', 12.00, 0, 'Joensuu'),
	(2, 'Tulostin', 'Värilaser', 20.00, 0, 'Joensuu'),
	(3, 'Työntekijä', 'Valmiiksi työhönsä kyllästynyt vuokratyöntekijä.', 120.00, 0, 'Joensuu'),
	(4, 'Huonekasvi', 'Värit haalistuneet auringopaisteesta, kangasta.', 5.00, 0, 'Lappeenranta'),
	(5, 'Silppuri', 'Tuhoaa melkein mitä vain.', 15.00, 0, 'Lappeenranta'),
	(6, 'Pomppulinna', 'Pientä tuoksua aiemmista virikepäivistä. Kolmas torni tahmea.', 150.00, 0, 'Oulu'),
	(7, 'Juoma-automaatti', 'Valmistaa erilaisia kahvi ja kaakaojuomia. Saatavilla myös mehukeitto- ja limonadi-versiot.', 90.00, 0, 'Oulu');

INSERT INTO `varaus` (`varausNro`, `vuokra`, `paVuokra`, `alkuPvm`, `loppuPvm`, `vuokraAlv`, `paAlv`, `varausTehty`, `toId`, `asiakasNro`) VALUES
	(1, 135.00, 450.00, '2022-04-25', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 7, 1),
	(2, 24.00, 15.00, '2022-04-20', '2022-04-25', 0, 0, '2022-04-30 15:06:47', 5, 4),
	(3, 122.00, 0.00, '2022-04-20', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 3, 2),
	(4, 85.00, 0.00, '2022-04-01', '2022-04-30', 0, 0, '2022-04-30 15:06:47', 1, 3);
	
INSERT INTO `palveluvaraus` (`varausNro`, `paId`, `kpl`) VALUES
	(1, 7, 5),
	(2, 5, 1);
	
INSERT INTO `lasku` (`laskuNro`, `loppusumma`, `vastaanottaja`, `lahiosoite`, `postinumero`, `postitoimipaikka`, `email`, `viite`, `erapaiva`, `paperilasku`, `pvm`, `maksuehto`, `kustannuspaikka`, `tila`, `asiakasNro`, `varausNro`) VALUES
	(1, 1464.00, 'Iitan Strösseli Ky', 'Pajakuja 1', '53100', 'Lappeenranta', 'iita@strosseli.fi', '00000001', '2022-05-14', 1, '2022-05-15', '14pv netto', '86868668', 1, 4, 3),
	(2, 234.00, 'Mari Tonsikka', 'Säkkijärvenkuja 6', '80200', 'Joensuu', 'mari@hillo.net', NULL, '2022-05-14', NULL, '2022-05-10', '14pv netto', NULL, 4, 3, 2),
	(3, 2550.00, 'Paukun Betoni Oy', 'Siihtalanpussi 5', '80100', 'Joensuu', 'toimisto@paukkub.fi', '00000003', '2022-06-04', NULL, '2022-05-05', '30pv netto', '25252522', 1, 2, 4),
	(4, 3510.00, 'Esko Kepponen', 'Vislauskuja 3', '90100', 'Oulu', 'esko@roskaposti.fi', NULL, '2022-05-14', NULL, '2022-05-01', '14pv netto', NULL, 1, 1, 1);