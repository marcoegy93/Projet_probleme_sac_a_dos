/**
 * Fichier : QuickSort.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package quickSort;

import sacados.Objet;

import java.util.Collections;
import java.util.List;

public abstract class QuickSort {
    /**
     * Méthode quickSort()
     * Méthode de tri rapide d'un tableau d'objets
     * Trie les objets par ordre décroissant en fonction de leur rapport prix / poids
     * @param objets (List<Objet>)
     * @param premier (Integer)
     * @param dernier (Integer)
     */
    public static void quickSort(List<Objet> objets, int premier, int dernier) {
        if(premier < dernier) {
            int pivot = (dernier - premier) / 2 + premier;
            pivot = repartir(objets, premier, dernier, pivot);
            quickSort(objets, premier, pivot - 1);
            quickSort(objets, pivot + 1, dernier);
        }
    }

    /**
     * Méthode repartir()
     * La méthode répartit les objets autour du pivot d'indice donné en paramètre
     * Les objets au rapport prix / poids plus élevés que le pivot sont placés à sa gauche et inversement pour
     * ceux au rapport prix / poids moins élevé
     * @param objets (List<Objet>)
     * @param premier (Integer)
     * @param dernier (Integer)
     * @param pivot (Integer)
     * @return (Integer)
     */
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
