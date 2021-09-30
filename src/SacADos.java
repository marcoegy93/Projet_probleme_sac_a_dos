public class SacADos {
    private Objet[] objets;
    private String chemin;
    private float valeurTotale;
    private float poidsTotal;
    private float poidsMaximal;

    public SacADos(){

    }

    public SacADos(String chemin, float poidsMaximal) {
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
        return "";
    }

    // METHODES //
    public int ajouterObjet(Objet o){
        if(this.poidsTotal + o.getPds() <= this.poidsMaximal){
            this.objets[this.objets.length] = o;
            this.poidsTotal += o.getPds();
            this.valeurTotale += o.getVal();
            return 1;
        }
        return -1;
    }

    public int retirerObjet(int i){
        if(this.objets[i] != null){
            this.objets[i] = null;
            this.poidsTotal -= this.objets[i].getPds();
            this.valeurTotale -= this.objets[i].getVal();
            return 1;
        }
        return -1;
    }

    public void resoudre(String methode){
        switch(methode){
            case "gloutonne":
                gloutonne();
                break;
            case "prog dynamique":
                progDynamique();
                break;
            case "pse":
                pse();
                break;
        }
    }

    public void gloutonne(){
        for(int i=0;i<this.objets.length;i++){
            this.objets[i].getRapportValPds();
        }
    }

    public void progDynamique(){

    }

    public void pse(){

    }

    // Echanger deux éléments (d'indices respectifs i et j) du tableau
    private void echanger(int i, int j)
    {
        Objet temp = this.objets[i];
        this.objets[i] = this.objets[j];
        this.objets[j] = temp;
    }
    
    /* Cette fonction prend le dernier élément comme pivot, place
    l'élément pivot à sa position correcte dans le tableau trié.
    trié, et place tous les éléments plus grands (plus grands que le pivot)
    à la gauche du pivot et tous les éléments plus petits à la droite
    du pivot */
    private int partition(int low, int high)
    {
        
        // pivot
        float pivot = this.objets[high].getRapportValPds(); 
        
        int i = low - 1;
        for(int j = low; j < high; j++)
        {
            
            // Si le rapport de l'objet actuel est plus grand que le pivot (car ordre décroissant)
            if (this.objets[j].getRapportValPds() > pivot) 
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
