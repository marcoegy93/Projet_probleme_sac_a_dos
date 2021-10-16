package methodes;

import sacados.Objet;

import java.util.Collections;
import java.util.List;

public abstract class Tri {
    // Echanger deux éléments (d'indices respectifs i et j) du tableau
    private static void echanger(List<Objet> t, int i, int j) {
        Objet temp = t.get(i);
        t.set(i, t.get(j));
        t.set(j,temp);
    }

    /* Cette fonction prend le dernier élément comme pivot, place
    l'élément pivot à sa position correcte dans le tableau trié.
    trié, et place tous les éléments plus grands (plus grands que le pivot)
    à la gauche du pivot et tous les éléments plus petits à la droite
    du pivot */
    /*private static int partition(List<Objet> t, int low, int high) {

        // pivot
        float pivot = t.get(high).getRapportValPds();

        int i = low - 1;
        for (int j = low; j < high; j++) {

            // Si le rapport de l'objet actuel est plus grand que le pivot (car ordre décroissant)
            if (t.get(j).getRapportValPds() > pivot) {

                // On incrémente l'index du plus grand élément
                i++;
                echanger(t, i, j);
            }
        }
        echanger(t, i + 1, high - 1);
        return (i + 1);
    }
*/
    /* La fonction récursive QuickSort principale
            low est l'indice du premier élément du tableau,
            high est l'indice du dernier élément
     */
    /*public static void quickSort(List<Objet> t, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(t, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(t, low, pi - 1);
            quickSort(t, pi + 1, high);
        }
    }*/

    /*public static void quickSort(List<Objet> tab, int premier, int dernier) {

        int j = premier + 1;
        int pivot = (premier + dernier) / 2;

        if (premier < dernier) {

            echanger(tab, premier, pivot);
            pivot = premier;

            for (int i = premier + 1; i <= dernier - 1; i++) {
                if (tab.get(i).getRapportValPds() > tab.get(pivot).getRapportValPds()) {
                    echanger(tab, i, j);
                    j += 1;
                }
            }

            echanger(tab, pivot, j - 1);
            pivot = j - 1;

            quickSort(tab, premier, pivot - 1);
            quickSort(tab, pivot + 1, dernier);
        }

    }*/

   /*public static void quickSort(List<Objet> t, int premier, int dernier){
        if(premier < dernier){
            int pivot = (dernier-premier)/2 +premier;
            pivot= repartition(t,premier,dernier,pivot);
            quickSort(t, premier, pivot - 1);
            quickSort(t, pivot + 1, dernier);
        }
    }

    private static int repartition(List<Objet> t, int premier, int dernier,int pivot){
        echanger(t,pivot,dernier);
        int j=premier;
        for(int i=premier;i<dernier-1;i++){
            if(t.get(i).getRapportValPds() >=t.get(dernier).getRapportValPds()){
                echanger(t,i,j);
                j++;
            }
        }
        echanger(t,dernier,j);
        return j;
    }*/

    public static void quickSort(List<Objet> objets, int premier, int dernier) {
        if(premier < dernier) {
            int pivot = (dernier - premier) / 2 + premier;
            pivot = repartir(objets, premier, dernier, pivot);
            quickSort(objets, premier, pivot - 1);
            quickSort(objets, pivot + 1, dernier);
        }
    }


    private static int repartir(List<Objet> objets, int premier, int dernier, int pivot) {
        Collections.swap(objets, pivot, dernier);
        int i = premier;
        for (int j = premier; j < dernier; ++j) {
            if(objets.get(j).getRapportValPds() >= objets.get(dernier).getRapportValPds()) {
                Collections.swap(objets, j, i);
                i++;
            }
        }
        Collections.swap(objets, dernier, i);
        return i;
    }
}
