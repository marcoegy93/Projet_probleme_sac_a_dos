import java.util.ArrayList;

public class SacADos {
    private ArrayList<Objet> objets;
    private String chemin;
    private float valeurTotale;
    private float poidsTotal;
    private float poidsMaximal;

    public SacADos(String chemin, float poidsMaximal) {
        this.objets=new ArrayList<Objet>();
        this.chemin = chemin;
        this.poidsMaximal = poidsMaximal;
        this.valeurTotale = 0;
        this.poidsTotal = 0;
    }

    // GETTERS de la classe SACADOS //
    public float getValeurTotale(){
        return this.valeurTotale;
    }
    public float getPoidsMaximal(){
        return this.poidsMaximal;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Objet o : this.objets)
            sb.append("> " + o.toString() + "\n");
        return sb.toString();
    }

    // METHODES //
    public int ajouterObjet(Objet o){
        if(this.poidsTotal + o.getPds() <= this.poidsMaximal){
            this.objets.add(o);
            this.poidsTotal += o.getPds();
            this.valeurTotale += o.getVal();
            return 1;
        }
        return -1;
    }

    /* vraiment utile comme méthode ?
    public int retirerObjet(int i){
        if(this.objets[i] != null){
            this.poidsTotal -= this.objets[i].getPds();
            this.valeurTotale -= this.objets[i].getVal();
            this.objets[i] = null; 
            return 1;
        }
        return -1;
    } */

    public void resoudre(String methode){
        switch(methode){
            case "gloutonne":
                gloutonne();
                break;
            case "progdynamique":
                progDynamique();
                break;
            case "pse":
                pse();
                break;
        }
    }

    /* Cette méthode permet de lire le fichier texte pointé par le chemin et retourner
    un tableau les contenant
    */
    public Objet[] getObjetsChemin(){

        return new Objet[0];
    }

    /* La méthode GLOUTONNE appelle la fonction quickSort qui va
    trier les objets en fonction de le rapport valeur / poids 
    par ordre décroissant et qui va essayer des les ajouter dans
    le sac un à un.
    */
    public void gloutonne(){
        Objet[] objetsAajouter = getObjetsChemin();
        int cpt = 0;
        quickSort(0, objetsAajouter.length);
        for(Objet o : objetsAajouter){
            cpt = ajouterObjet(o) > 0 ? cpt + 1 : cpt;
        }
        System.out.println(cpt + " objets sur " + objetsAajouter.length + "ont été ajoutés au sac à dos");
    }

    public void progDynamique(){

    }

    public void pse(){

    }

    // Echanger deux éléments (d'indices respectifs i et j) du tableau
    private void echanger(int i, int j)
    {
        Objet temp = this.objets.get(i);
        this.objets.set(i,this.objets.get(j));
        this.objets.set(j, temp);
    }
    
    /* Cette fonction prend le dernier élément comme pivot, place
    l'élément pivot à sa position correcte dans le tableau trié.
    trié, et place tous les éléments plus grands (plus grands que le pivot)
    à la gauche du pivot et tous les éléments plus petits à la droite
    du pivot */
    private int partition(int low, int high)
    {
        
        // pivot
        float pivot = this.objets.get(high).getRapportValPds(); 
        
        int i = low - 1;
        for(int j = low; j < high; j++)
        {
            
            // Si le rapport de l'objet actuel est plus grand que le pivot (car ordre décroissant)
            if (this.objets.get(j).getRapportValPds() > pivot) 
            {
                
                // On incrémente l'index du plus grand élément
                i++; 
                echanger(i, j);
            }
        }
        echanger(i + 1, high - 1);
        return (i + 1); 
    }
    
    /* La fonction récursive QuickSort principale
            low est l'indice du premier élément du tableau,
            high est l'indice du dernier élément
    */
    public void quickSort(int low, int high)
    {
        if (low < high) 
        {
            
            // pi is partitioning index, arr[p]
            // is now at right place 
            int pi = partition(low, high);
    
            // Separately sort elements before
            // partition and after partition
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }
}
