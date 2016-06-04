package alloc;

public abstract class Color {
	public static Graphe colorGraphe(Graphe gin, int k) {
		if (gin.sommetRestant()){
			String minSommet = gin.minDegresSommet();
			if ( gin.compterDegresRestants(minSommet) < k ) {
				gin.colorierSommet(minSommet, k);
				return colorGraphe(gin, k);
			} else {
				gin.spillerSommet(gin.maxDegresSommet());
				return colorGraphe(gin, k);
			}
		} else { return gin ; }
	}
}