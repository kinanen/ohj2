package HT2022;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author otsokinanen
 * @version 29 Aug 2022
 *
 *Rekisteri luokka, joka hoitaa tiedostojen lukemisen ja tallettamisen 
 */
public class Rekisteri {
    private static Henkilot henkilot;
    private static Lajit lajit;
    private static Harjoitusajat harjoitusajat;

    private static String henkiloTiedosto = "nimet.txt";
    private static String lajiTiedosto = "lajit.txt";
    private static String hATiedosto = "harjoitusajat.txt";

    /**
     * @return the henkiot
     */
    public static Henkilot getHenkilot() {
        return henkilot;
    }


    /**
     * @param henkiot the henkiot to set
     */
    public static void setHenkilot(Henkilot henkiot) {
        Rekisteri.henkilot = henkiot;
    }


    /**
     * @return the lajit
     */
    public static Lajit getLajit() {
        return lajit;
    }


    /**
     * @param lajit the lajit to set
     */
    public static void setLajit(Lajit lajit) {
        Rekisteri.lajit = lajit;
    }


    /**
     * @return the harjoitusajat
     */
    public static Harjoitusajat getHarjoitusajat() {
        return harjoitusajat;
    }


    /**
     * @param harjoitusajat the harjoitusajat to set
     */
    public static void setHarjoitusajat(Harjoitusajat harjoitusajat) {
        Rekisteri.harjoitusajat = harjoitusajat;
    }


    /**
     *  Lukee tiedoston parametrina saadun nimisen tiedoston levyltä ja palauttaa taulukkona tiedston sisällön, Kukin rivi yhdessä taulukon alkiossa String tyyppisessä taulukossa 
     *
     * @param tiedostonimi luettavan tiedoston nimi
     * @return String muotoinen taulukko, yksi rivi ydhessä taulukon indekissä.
     */
    public static String[] lueTiedosto(String tiedostonimi) {

        String[] rivit = new String[50];
        int i = 0;
        try (BufferedReader br = new BufferedReader(
                new FileReader(tiedostonimi))) {

            String line = br.readLine();

            while (line != null) {
                rivit[i] = line;
                line = br.readLine();
                i++;
            }
            br.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getStackTrace().toString());
        }

        return rivit;

    }


    /**
     * 
     * Kirjoittaa tiedoston levylle parametrinä saadulla nimiellä, kukin taulukon alkio kirjoitetaan omalle riville tiedostossa.
     * @param tiedostonimi luettavan tiedoston nimi
     * @param rivit luettava tieto
     */
    public static void kirjoitaTiedosto(String tiedostonimi, String[] rivit) {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(tiedostonimi))) {
            for (int i = 0; i < rivit.length; i++) {
                bw.write(rivit[i]+"\r\n");
            }
            bw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getStackTrace().toString());
        }

    }


    /**
     * Luetaan Harjoitusajat tiedosto ja palautetaan sen mukaisesti lista jossa harjoitusajat on asetettuna Harjoitusajat oliona.
     * Ottaa vastaan muuttujat joiden perusteella osa harjoitusajan tiedoista kjirjataan
     * 
     * @param r relaatiolista johon harjoitusaikojen relaatiot lisätään
     * @param l lajit lista
     * @return lista harjoitusajoista
     */
    public static Harjoitusajat lueHArekisteri(Relaatiot r, Lajit l){
        String[] x = Rekisteri.lueTiedosto(hATiedosto);

        Harjoitusajat hat = new Harjoitusajat();

        for (int i = 0; i < x.length; i++) {
            if (x[i] == null)
                break;
            Harjoitusaika ha = Harjoitusaika.lueHarjoitusaika(x[i], r, l);
            hat.getHarjoitusajat().add(ha);
        }

        setHarjoitusajat(hat);

        return hat;
    }
    
    /**
     * @param paivitettyHarjoitusajat ottaa parametrina päivitetyn harjoitusaikalistan 
     */
    public static void kirjoitaHArekisteri(Harjoitusajat paivitettyHarjoitusajat){
        String[] rivit =new  String [paivitettyHarjoitusajat.getHarjoitusajat().size()];
        int i  = 0;
        for (Harjoitusaika hA : paivitettyHarjoitusajat.getHarjoitusajat()) {
            rivit[i]=hA.getHarjoitusaikaKirjoitettava();
            i++;
        }
        
        kirjoitaTiedosto(hATiedosto, rivit);
    }


    /**
     * Lukee tiedoston ja palauttaa listan lajeista.
     * @return lista lajeista
     */
    public static Lajit lueLajiRekisteri() {
        String[] x = Rekisteri.lueTiedosto(lajiTiedosto);

        Lajit lisattavatLajit = new Lajit();

        for (int i = 0; i < x.length; i++) {
            if (x[i] == null)
                break;
            Laji l = Laji.lueLaji(x[i]);
            lisattavatLajit.getLajit().add(l);
        }

        setLajit(lisattavatLajit);

        return lisattavatLajit;
    }

    /**
     * @param paivitetytLajit lajit
     */
    public static void kirjoitaLajiRekisteri(Lajit paivitetytLajit) {
        String[] rivit = new String[paivitetytLajit.getLajit().size()];
        int i = 0;
        for (Laji l : paivitetytLajit.getLajit()) {
            rivit[i] = l.getLajiKirjoitettava();
            i++;
        }

        kirjoitaTiedosto(lajiTiedosto, rivit);

    }
    /**
     * @param relaatiot relaatiot lista johon henkilön lajirelaatiot lisätään
     * @return henkilöt lista
     */
    public static Henkilot lueHenkiloRekisteri(Relaatiot relaatiot) {
        String[] x = Rekisteri.lueTiedosto(henkiloTiedosto);

        Henkilot hlot = new Henkilot();

        for (int i = 0; i < x.length; i++) {
            if (x[i] == null)
                break;
            Henkilo h = Henkilo.lueHenkilo(x[i], relaatiot);
            hlot.getHenkilot().add(h);
        }

        setHenkilot(hlot);

        return hlot;

    }
    
    


    /**
     * @param paivitetytHenkilot  paivitetyt henkilot
     */
    public static void kirjoitaHenkiloRekisteri(Henkilot paivitetytHenkilot) {
        String[] rivit = new String[paivitetytHenkilot.getHenkilot().size()];
        int i = 0;
        for (Henkilo h : paivitetytHenkilot.getHenkilot()) {
            rivit[i] = h.getHenkiloKirjoitettava();
            i++;
        }

        kirjoitaTiedosto(henkiloTiedosto, rivit);

    }
}
