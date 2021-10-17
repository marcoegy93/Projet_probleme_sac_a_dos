/**
 * Fichier : ABR.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */

package arbre;

import sacados.Objet;

import java.util.List;

public class ABR {

    // ------ ATTRIBUTS DE LA CLASSE ------ //
    private Objet[] objetsDeLArbre;
    private ABR filsGauche, filsDroit;
    private int profondeur;
    private static double borneInferieure;
    private double borneSuperieure;
    private static Objet[] meilleureSolution;

    // ------ MÉTHODES DE LA CLASSE ------//
    /**
     * Constructeur de la classe ABR
     * Constructeur récursif
     * Construction des combinaisons possibles ET qui ont un intérêt
     * @param listeObjetsChemin (List<Objets>)
     * @param PoidsMaximal (Double)
     * @param tabObj (Objet[])
     * @param i (Integer)
     */
    public ABR(List<Objet> listeObjetsChemin, double PoidsMaximal, Objet[] tabObj, int i){
        if (i <= listeObjetsChemin.size()) {
            setObjetsDeLArbre(listeObjetsChemin, tabObj);

            this.profondeur = i;
            this.calculBorneSuperieure(listeObjetsChemin);
            this.calculBorneInferieure();

            if (i != listeObjetsChemin.size()){
                créerFils(listeObjetsChemin, PoidsMaximal, tabObj);
            }

        }
    }

    /**
     * Méthode setObjetsDeLArbre()
     * Racopiage d'un tableau d'objets dans le tableau this.objetsDeLArbre
     * @param listeObjetsChemin (List<Objet>)
     * @param tabObj (Objet[])
     */
    public void setObjetsDeLArbre(List<Objet> listeObjetsChemin, Objet[] tabObj){
        this.objetsDeLArbre = new Objet[listeObjetsChemin.size()];
        for (int j=0; j<listeObjetsChemin.size(); ++j){
            if (tabObj[j] != null){
                this.objetsDeLArbre[j] = tabObj[j];
            }
        }
    }

    /**
     * Méthode créerFils()
     * Méthode appelée dans le constructeur pour créer les fils du noeud courant
     * @param listeObjetsChemin (List<Objet>)
     * @param PoidsMaximal (Double)
     * @param tabObj (Objet[])
     */
    public void créerFils(List<Objet> listeObjetsChemin, double PoidsMaximal, Objet[] tabObj){
        int i = this.profondeur;
        this.filsGauche = new ABR(listeObjetsChemin, PoidsMaximal, tabObj, i+1);

        tabObj[i] = listeObjetsChemin.get(i);
        if (this.poidsListeObjets(tabObj)<=PoidsMaximal && this.borneSuperieure>ABR.borneInferieure){
            //vérification pour optimiser l'arbre
            this.filsDroit = new ABR(listeObjetsChemin, PoidsMaximal, tabObj, i+1);
        }
        tabObj[i] = null; // pour supprimer le dernier objet dans tabObj MAIS AUSSI dans this.objetsDeLArbre (car référence)
    }

    /**
     * Méthode chercherSolution()
     * Méthode récursive pour trouver la combinaison (en initialisant l'attribut statique tabMeilleureValeur)_
     * à partir de la meilleure valeur trouvée dans tout le tableau (qui est obtenue avec borneInferieure en construisant l'arbre)
     */
    public void chercherSolution(){
        if (this.valeurListeObjets() == ABR.borneInferieure){
            ABR.meilleureSolution = this.objetsDeLArbre;
        }
        else {
            if (this.filsGauche ==null && this.filsDroit ==null){
                return;
            }
            if (this.filsGauche ==null){
                this.filsDroit.chercherSolution();
            }
            if (this.filsDroit ==null){
                this.filsGauche.chercherSolution();
            }
            if (this.filsDroit !=null && this.filsGauche !=null){
                this.filsDroit.chercherSolution();
                this.filsGauche.chercherSolution();
            }
        }
    }

    /**
     * Méthode calculBorneInferieure()
     * mise à jour de l'attribut statique borneInferieure lorsqu'une meilleure valeur (correspondant à une combinaison) est trouvée
     * mise à jour lors de la construction de l'arbre
     */
    public void calculBorneInferieure(){
        if (this.valeurListeObjets() > ABR.borneInferieure){
            ABR.borneInferieure = this.valeurListeObjets();
        }
    }

    /**
     * Méthode calculBorneSuperieure()
     * Calcule pour chaque noeud (ABR) la valeur max que pourra avoir la combinaison finale à partir d'un noeud
     * @param listeObjetsSac (List<Objet>)
     */
    public void calculBorneSuperieure(List<Objet> listeObjetsSac){
        double res = 0.0;
        res += this.valeurListeObjets(); // valeur totale du noeud courant
        for (int i=this.profondeur; i<listeObjetsSac.size(); ++i){
            res += listeObjetsSac.get(i).getVal(); // ajout des valeurs des objets restants
        }
        this.borneSuperieure = res;
    }

    /**
     * Méthode valeurListeObjets()
     * @return (Double) la valeur totale du tableau d'objets
     */
    public double valeurListeObjets(){
        double res=0.0;
        for(int i = 0; i<this.objetsDeLArbre.length; ++i){
            if (this.objetsDeLArbre[i] != null){
                res += this.objetsDeLArbre[i].getVal();
            }
        }
        return res;
    }

    /**
     * Méthode poidsListeObjets()
     * @return le poids total du tableau d'objets
     */
    public double poidsListeObjets(){
        double res=0.0;
        for(int i = 0; i<this.objetsDeLArbre.length; ++i){
            if (this.objetsDeLArbre[i] != null){
                res += this.objetsDeLArbre[i].getVal();
            }
        }
        return res;
    }

    /**
     * Méthode valeurListeObjets()
     * @return la valeur totale d'un tableau d'objets
     */
    public double valeurListeObjets(Objet[] listeObjets){
        double res=0.0;
        for(int i=0; i<listeObjets.length; ++i){
            if (listeObjets[i] != null){
                res += listeObjets[i].getVal();
            }
        }
        return res;
    }

    /**
     * Méthode poidsListeObjets()
     * @return le poids total d'un tableau d'objets
     */
    public double poidsListeObjets(Objet[] listeObjets){
        double res=0.0;
        for(int i=0; i<listeObjets.length; ++i){
            if (listeObjets[i] != null){
                res += listeObjets[i].getPds();
            }
        }
        return res;
    }

    // ------ GETTERS & SETTERS ------ //
    /**
     * Méthode getBorneInférieure()
     * @return (Double) la borne inférieure
     */
    public double getBorneInferieure(){
        return ABR.borneInferieure;
    }

    /**
     * Méthode getBorneSupérieure()
     * @return (Double) la borne supérieure
     */
    public double getBorneSuperieure(){
        return this.borneSuperieure;
    }

    /**
     * Méthode getMeilleureSolution()
     * @return (Objet[]) la meilleure solution de l'arbre
     */
    public Objet[] getMeilleureSolution(){
        return ABR.meilleureSolution;
    }

    /**
     * Méthode getProfondeur()
     * @return (Integer) la profondeur du noeud courant
     */
    public int getProfondeur(){
        return this.profondeur;
    }

}



