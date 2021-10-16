package methodes;
import sacados.*;
import java.util.List;
public class Gloutonne extends Methode{
    public static void resoudre(SacADos sac){
        List<Objet> objetsAajouter = sac.getObjetsChemin();
        int cpt = 0;
        Tri.quickSort(objetsAajouter, 0, objetsAajouter.size() - 1);
        for(Objet o : objetsAajouter){
            cpt = sac.ajouterObjet(o) > 0 ? cpt + 1 : cpt;
        }
        System.out.println("METHODE GLOUTONNE");
        System.out.println(cpt + " objets sur " + objetsAajouter.size() + " ont été ajoutés au sac à dos");
    }

}
