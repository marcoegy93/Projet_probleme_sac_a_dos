package Appli;

import java.lang.reflect.Type;

public abstract class Tri<T> {
    // Echanger deux éléments (d'indices respectifs i et j) du tableau
    private static void echanger(Objet[] t, int i, int j) {
        Objet temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    /* Cette fonction prend le dernier élément comme pivot, place
    l'élément pivot à sa position correcte dans le tableau trié.
    trié, et place tous les éléments plus grands (plus grands que le pivot)
    à la gauche du pivot et tous les éléments plus petits à la droite
    du pivot */
    private static int partition(Objet[] t, int low, int high) {

        // pivot
        float pivot = t[high].getRapportValPds();

        int i = low - 1;
        for (int j = low; j < high; j++) {

            // Si le rapport de l'objet actuel est plus grand que le pivot (car ordre décroissant)
            if (t[j].getRapportValPds() > pivot) {

                // On incrémente l'index du plus grand élément
                i++;
                echanger(t, i, j);
            }
        }
        echanger(t, i + 1, high - 1);
        return (i + 1);
    }

    /* La fonction récursive QuickSort principale
            low est l'indice du premier élément du tableau,
            high est l'indice du dernier élément
    */
    public static void quickSort(Objet[] t, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(t, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(t, low, pi - 1);
            quickSort(t, pi + 1, high);
        }
    }
}
