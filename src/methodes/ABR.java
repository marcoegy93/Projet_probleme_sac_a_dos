package methodes;


import sacados.Objet;

import java.util.List;

public class ABR {

    private Objet[] objetsDeLArbre;
    private ABR leftTree, rightTree;
    private int profondeur;
    private static double borneInferieure;
    private double borneSuperieure;
    private static Objet[] meilleureSolution;

    /*
     *  Constructeur récursif
     *  Construction des combinaisons possibles ET qui ont un intérêt
     */
    public ABR(List<Objet> listeObjetsChemin, double PoidsMaximal, Objet[] tabObj, int i){
        if (i <= listeObjetsChemin.size()) {

            // recopiage dans this.objetsDeLArbre le tableau tabObj
            this.objetsDeLArbre = new Objet[listeObjetsChemin.size()];
            for (int j=0; j<listeObjetsChemin.size(); ++j){
                if (tabObj[j] != null){
                    this.objetsDeLArbre[j] = tabObj[j];
                }
            }

            this.profondeur = i;
            this.calculBorneSuperieure(listeObjetsChemin);
            this.calculBorneInferieure();

            if (i != listeObjetsChemin.size()){
                this.leftTree = new ABR(listeObjetsChemin, PoidsMaximal, tabObj, i+1);

                tabObj[i] = listeObjetsChemin.get(i);
                if (this.poidsListeObjets(tabObj)<=PoidsMaximal && this.borneSuperieure>ABR.borneInferieure){
                    //vérification pour optimiser l'arbre
                    this.rightTree = new ABR(listeObjetsChemin, PoidsMaximal, tabObj, i+1);
                }
                tabObj[i] = null; // pour supprimer le dernier objet dans tabObj MAIS AUSSI dans this.objetsDeLArbre (car référence)
            }

        }
    }

    /*
     * fonction r�cursive pour trouver la combinaison (en initialisant l'attribut statique tabMeilleureValeur)_
     * � partir de la meilleure valeur trouv�e dans tout le tableau (qui est obtenue avec borneInferieure en construisant l'arbre)
     */
    public void chercherSolution(){
        if (this.valeurListeObjets() == ABR.borneInferieure){
            ABR.meilleureSolution = this.objetsDeLArbre;
        }
        else {
            if (this.leftTree==null && this.rightTree==null){
                return;
            }
            if (this.leftTree==null){
                this.rightTree.chercherSolution();
            }
            if (this.rightTree==null){
                this.leftTree.chercherSolution();
            }
            if (this.rightTree!=null && this.leftTree!=null){
                this.rightTree.chercherSolution();
                this.leftTree.chercherSolution();
            }
        }
    }

    public double getBorneInferieure(){
        return ABR.borneInferieure;
    }

    public double getBorneSuperieure(){
        return this.borneSuperieure;
    }

    /*
     * mis � jour de l'attribut statique borneInferieure lorsqu'une meilleure valeur (correspondant � une combinaison) est trouv�e
     * mis � jour lors de la construction de l'arbre
     */
    public void calculBorneInferieure(){
        if (this.valeurListeObjets() > ABR.borneInferieure){
            ABR.borneInferieure = this.valeurListeObjets();
        }
    }

    /*
     * calcul pour chaque noeud (ABR) la valeur max que pourra avoir la combinaison finale � partir d'un noeud
     */
    public void calculBorneSuperieure(List<Objet> listeObjetsSac){
        double res = 0.0;
        res += this.valeurListeObjets(); // valeur totale du noeud courant
        for (int i=this.profondeur; i<listeObjetsSac.size(); ++i){
            res += listeObjetsSac.get(i).getVal(); // ajout des valeurs des objets restants
        }
        this.borneSuperieure = res;
    }

    /*
     * retourne la valeur totale de this.value (tableau d'objets)
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

    /*
     * retourne le poids total de this.value (tableau d'objets)
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

    /*
     * retourne la valeur totale d'un tableau d'objets
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

    /*
     * retourne le poids total d'un tableau d'objets
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

    public Objet[] getMeilleureSolution(){
        return ABR.meilleureSolution;
    }

    public int getProfondeur(){
        return this.profondeur;
    }

}



