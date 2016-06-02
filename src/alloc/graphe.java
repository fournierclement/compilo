package alloc;

import java.util.*;

public class Graphe{

	ArrayList<Sommet> sommets;

	public Graphe();

	public ajouterSommet(String nom){
		if (chercheSommet == null){
			sommets.add(new Sommet(nom));
		}
	}

	public supprSommet(String nom){
		sommets
	}

	private Sommet chercheSommet(String nom){

	}
}

private class Sommet{
	String nom;
	int couleur;
	ArrayList<Sommet> voisins;
	ArrayList<Sommet> preference;

	Sommet(String nom){
		this.nom = nom ; 
	}
	
	int getCouleur() {
		return this.couleur;
	}

	setCouleur(int couleur) {
		this.couleur = couleur ;
	}

	String getNom() {
		return this.nom ;
	}

	AjouterVoisin(Sommet voisin){
		voisins.add(voisin);
	}

	AjouterPref(Sommet pref){
		preference.add(preference);
	}

	ArrayList<Sommet> getVoisinsIncolor(){
		ArrayList<Sommet> voisinsIncolor;

		return voisinsIncolor;
	}

}