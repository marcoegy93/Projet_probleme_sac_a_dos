package methodes;
import sacados.*;

import java.util.ArrayList;
import java.util.List;

import methodes.Methode;
import sacados.Objet;
import java.util.List;
import java.util.ArrayList;
public class PSE extends Methode {
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
