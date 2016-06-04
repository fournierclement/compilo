package alloc;

import java.util.*;

public class TestGraphe{
    public static void main(String[] args){
        for (int k = 1; k < 4 ; k++){
            Graphe grapheTest= new Graphe();
            grapheTest.ajouterSommet("v");
            grapheTest.ajouterSommet("z");
            grapheTest.ajouterSommet("x");
            grapheTest.ajouterSommet("y");
            grapheTest.ajouterSommet("u");
            grapheTest.ajouterSommet("t");
            grapheTest.ajouterPref("v","z");
            grapheTest.ajouterPref("u","t");
            grapheTest.ajouterDegre("u","x");
            grapheTest.ajouterDegre("u","y");
            grapheTest.ajouterDegre("y","x");
            grapheTest.ajouterDegre("y","t");

            System.out.println(grapheTest.toString());
            System.out.println("Colorier avec " + k +" couleurs :");
            Graphe grapheColor = Color.colorGraphe(grapheTest,k);
            System.out.println(grapheColor.toString());
        }
    }
}