package HT2022;

/**
 * @author otsokinanen
 * @version 30 Aug 2022
 *
 * Henkilön ja Lajin tai Lajin ja harjoitusajan välinen suhde kirjattuna ID arvojen perusteella omaan olioon. Kullakin relaatiolla on myös oma id jolla niitä voidaan käsitellä. 
 */
public class Relaatio {
    private int relaatioId;
    private static int nextId = 1; //seuraava vapaa relaatio id
    private int henkilo;
    private int laji;
    private int harjoitusaikaId;
    
    /**
     * Muodostaja relaatiolla Hnkilön ja lajin välillä
     * @param henkilo henkilö id
     * @param laji laji id
     */
    public Relaatio(int henkilo, int laji) {
       setRelaatioId(nextId);
       setHenkilo(henkilo);
       setLaji(laji);
    }
    
    /**
     * argumentiton muodostaja, antaa Relaatiolle Id:n
     */
    public Relaatio() {
        setRelaatioId(nextId);
    }
    
    /**
     * @return the relaatioId
     */
    public int getRelaatioId() {
        return relaatioId;
    }
    /**
     * @param relaatioId the relaatioId to set
     */
    public void setRelaatioId(int relaatioId) {
        this.relaatioId = relaatioId;
        nextId++;
    }
    /**
     * @return the henkilo
     */
    public int getHenkilo() {
        return henkilo;
    }
    /**
     * @param henkilo the henkilo to set
     */
    public void setHenkilo(int henkilo) {
        this.henkilo = henkilo;
    }
    /**
     * @return the lajt
     */
    public int getLaji() {
        return laji;
    }
    /**
     * @param laji the laji to set
     */
    public void setLaji(int laji) {
        this.laji = laji;
    }
    
    /**
     * @return the harjoitusajat
     */
    public int getHarjoitusaikaId() {
        return harjoitusaikaId;
    }

    /**
     * @param harjoitusaika the harjoitusajat to set
     */
    public void setHarjoitusaikaId(int harjoitusaika) {
        harjoitusaikaId = harjoitusaika;
    }

    @Override
    public String toString() {
        return (this.getRelaatioId() + " : " + this.getHenkilo() + " : " + this.getLaji() +" : " + this.getHarjoitusaikaId());
    }

    /**
     * nollaa relaation laji id:n
     */
    public void nollaaLaji() {
        setLaji(0);
        
    }
    
}
