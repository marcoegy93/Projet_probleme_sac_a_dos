/**
 * Fichier : Methode.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */

package methodes;

import sacados.SacADos;

public abstract class Methode {

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Méthode resoudre()
     * Méthode destinée à être redéfinie par les classes héritantes
     * @param sac (SacADos)
     */
    public static void resoudre(SacADos sac){
        System.out.println("Méthode abstraite");
    }
}
