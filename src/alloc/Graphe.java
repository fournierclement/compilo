package alloc;

import java.util.*;

public class Graphe{

	ArrayList<Sommet> sommets = new ArrayList<Sommet>();

	public Graphe(){};

	public void ajouterSommet(String nom){
		this.sommets.add(new Sommet(nom));
	}

	public void supprSommet(String nom){
		Sommet sommet = this.chercheSommet(nom);
		for ( Sommet s : sommet.getVoisins() ) {
			s.retirerVoisin(sommet);
		}
		for ( Sommet s : sommet.getPref() ) {
			s.retirerPref(sommet);
		}
		this.sommets.remove(sommet);
	}

	public void ajouterDegre(String s1, String s2){
		Sommet som1 = this.chercheSommet(s1);
		Sommet som2 = this.chercheSommet(s2);
		if ( ( som1 != null ) && ( som2 != null ) ) {
			som1.ajouterVoisin(som2);
			som2.ajouterVoisin(som1);
		}
	}

	public void enleverDegre(String s1, String s2){
		Sommet som1 = this.chercheSommet(s1);
		Sommet som2 = this.chercheSommet(s2);
		if ( ( som1 != null ) && ( som2 != null ) ) {
			som1.retirerVoisin(som2);
			som2.retirerVoisin(som1);
		}
	}

	public void ajouterPref(String s1, String s2){
		Sommet som1 = this.chercheSommet(s1);
		Sommet som2 = this.chercheSommet(s2);
		if ( ( som1 != null ) && ( som2 != null ) ) {
			som1.ajouterPref(som2);
			som2.ajouterPref(som1);
		}
	}

	public void enleverPref(String s1, String s2){
		Sommet som1 = this.chercheSommet(s1);
		Sommet som2 = this.chercheSommet(s2);
		if ( ( som1 != null ) && ( som2 != null ) ) {
			som1.retirerPref(som2);
			som2.retirerPref(som1);
		}
	}

	public boolean sommetRestant(){
		boolean restant = false;
		for ( Sommet s : this.sommets){
			if ( s.getCouleur()<0){
				restant = true;
			}
		}
		return restant;
	}

	public int compterDegresRestants(String nom){
		Sommet sommet = this.chercheSommet(nom);
		return sommet.degresRestants() ;
	}

	public String minDegresSommet(){
		String sommetMin ="";
		int min = this.sommets.size();
		for (Sommet s : this.sommets){
			int degres = s.degresRestants();
			if ( ( s.getCouleur() < 0 ) && ( degres < min ) ){
				min = degres;
				sommetMin = s.getNom();
			}
		}
		return sommetMin;
	}

	public String maxDegresSommet(){
		String sommetMax ="";
		int max = 0;
		for (Sommet s : this.sommets){
			int degres = s.degresRestants();
			if ( ( s.getCouleur() < 0 ) && ( degres > max ) ){
				max = degres;
				sommetMax = s.getNom();
			}
		}
		return sommetMax;
	}

	private Sommet chercheSommet(String nom){
		Sommet sommet = null;
		for (Sommet s : this.sommets) {
			if ( s.getNom().equals(nom) ){
				sommet = s;
			}
		}
		return sommet;
	}

	public void colorierSommet(String nom, int k){
		Sommet sommet = this.chercheSommet(nom);
		if (sommet != null){

			int couleurDispo = 0;

			for ( Sommet s : sommet.getPref()){
				if ( sommet.couleurDispo( s.getCouleur() ) ){
					couleurDispo = s.getCouleur();
				}
			}
			if (couleurDispo > 0){
				sommet.setCouleur(couleurDispo);
			}else{
				while ( ( couleurDispo < k ) && ( sommet.getCouleur() < 0 ) ){
					couleurDispo++;
					if (sommet.couleurDispo(couleurDispo)){
						sommet.setCouleur(couleurDispo);
					}
				}
			}
		}
	}

	public void spillerSommet(String nom){
		Sommet sommet = this.chercheSommet(nom) ;
		if (sommet != null){
			sommet.setCouleur(0);
		}
	}

	public String toString(){
		String toString = "sommets (nom(couleur)), 0 = spillé, -1 = incolore :";
		for (Sommet s : this.sommets){
			toString += "\n" + s + "; Interfere avec : " + s.getVoisins() + "; preferences avec : " + s.getPref();
		}
		return toString;
	}

	private class Sommet{
		String nom;
		int couleur = -1 ;
		ArrayList<Sommet> voisins = new ArrayList<Sommet>();
		ArrayList<Sommet> preferences = new ArrayList<Sommet>();

		Sommet(String nom){
			this.nom = nom ; 
		}

		int getCouleur() {
			return this.couleur;
		}

		void setCouleur(int couleur) {
			this.couleur = couleur ;
		}

		String getNom() {
			return this.nom ;
		}

		void ajouterVoisin(Sommet voisin){
			voisins.add(voisin);
		}

		void retirerVoisin(Sommet voisin){
			voisins.remove(voisin);
		}

		void ajouterPref(Sommet pref){
			preferences.add(pref);
		}

		void retirerPref(Sommet pref){
			preferences.remove(pref);
		}

		int degresRestants(){
			int compteur = 0 ;
			for ( Sommet s : this.getVoisins() ){
				if ( s.getCouleur() < 0 ){
					compteur++ ;
				}
			}
			return compteur;
		}

		ArrayList<Sommet> getVoisins(){
			return this.voisins;
		}
		ArrayList<Sommet> getPref(){
			return this.preferences;
		}

		boolean couleurDispo(int i){
			boolean dispo = (i > 0);
			for ( Sommet s : this.voisins) {
				if ( i == s.getCouleur() ){ dispo = false ;}
			}
			return dispo;
		}

		public String toString(){
			return this.getNom() + "(" + this.getCouleur() + ")";
		}
	}
}