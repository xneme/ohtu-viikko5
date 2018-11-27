package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 1) {
            return;
        }

        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (alkioidenLkm == luvut.length) {
                this.kasvataTaulukkoa();
            }

            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }

        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luvut[i] == luku) {
                luvut[i] = luvut[alkioidenLkm - 1]; // Siirretään viimeinen luku poistettavan tilalle.
                alkioidenLkm--;
                return true;
            }
        }

        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String merkkijonoesitys = Arrays.toString(this.toIntArray());
        return "{" + merkkijonoesitys.substring(1, merkkijonoesitys.length() - 1) + "}";
    }

    public int[] toIntArray() {
        return Arrays.copyOf(luvut, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        
        for (int i : bTaulu) {
            a.lisaa(i);
        }
        
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        
        for (int i : aTaulu) {
            if (!b.kuuluu(i)) {
                a.poista(i);
            }
        }
        
        return a;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        
        for (int i : bTaulu) {
            a.poista(i);
        }
        
        return a;
    }

    private void kasvataTaulukkoa() {
        this.luvut = Arrays.copyOf(luvut, alkioidenLkm + kasvatuskoko);
    }

}
