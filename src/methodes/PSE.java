/**
 * Fichier : PSE.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */

package methodes;

import arbre.ABR;
import sacados.*;

import sacados.Objet;

public class PSE extends Methode {

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Méthode résoudre()
     * Résout le problème du sac à dos en utilisant la méthode
     * procédure par séparation et évaluation (PSE)
     * @param sac (SacADos)
     */
    public static void resoudre(SacADos sac){
        Objet[] tabObj = new Objet[sac.getObjetsChemin().size()];
        ABR arbre = new ABR(sac.getObjetsChemin(), sac.getPoidsMaximal(), tabObj, 0);

        arbre.chercherSolution();

        Objet[] meilleureSolution = arbre.getMeilleureSolution();
        int cpt=0;
        for (int i=0; i<sac.getObjetsChemin().size(); ++i){
            if (meilleureSolution[i] != null){
                sac.ajouterObjet(sac.getObjetsChemin().get(i));
                cpt++;
            }
        }
        System.out.println("METHODE PSE");
        System.out.println(cpt + " objets sur " + tabObj.length + " ont été ajoutés au sac à dos");
    }

}
