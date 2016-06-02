package alloc;

public abstract class Color {
	public static Graphe colorGraphe(Graphe gin, int k) {
		minSommet = gin.minDegresSommet();
		if ( gin.compterDegres(minSommet) < k ) {
			gin.colorSommet(minSommet, k);
			return colorGraphe(gin, k);
		} else {
			gin.spillerSommet(minSommet);
			return colorGraphe(gin, k);
		}
	}
}