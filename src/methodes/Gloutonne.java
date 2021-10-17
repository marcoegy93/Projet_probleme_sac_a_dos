/**
 * Fichier : Gloutonne.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package methodes;

import sacados.*;
import quickSort.QuickSort;

import java.util.List;

public class Gloutonne extends Methode {

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Méthode resoudre()
     * Résout le problème du sac à dos en utilisant la méthode gloutonne
     * @param sac (SacADos)
     */
    public static void resoudre(SacADos sac){
        List<Objet> objetsAajouter = sac.getObjetsChemin();
        int cpt = 0;
        QuickSort.quickSort(objetsAajouter, 0, objetsAajouter.size() - 1);
        for(Objet o : objetsAajouter){
            cpt = sac.ajouterObjet(o) > 0 ? cpt + 1 : cpt;
        }
        System.out.println("METHODE GLOUTONNE");
        System.out.println(cpt + " objets sur " + objetsAajouter.size() + " ont été ajoutés au sac à dos");
    }

}
