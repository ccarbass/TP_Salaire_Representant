import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie);
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);

		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}

	@Test
	public void testCAParDefaut() {
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		
		// On n'enregistre aucun CA
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0
		
		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}

	@Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}


	}
	@Test
	public void testpourcentageNegatifImpossible() {

		try {
			// On enregistre un pourcentage négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(0, -0.1f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un pourcentage négatif doit générer une exception"); // Forcer l'échec du test
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	@Test
	public void testMoisInfZeroCAImpossible() {

		try {
			// On enregistre un mois inférieur à 0
			r.enregistrerCA(-30, 100f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois négatif doit générer une exception"); // Forcer l'échec du test
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	@Test
	public void testMoisSupOnzeCAImpossible() {

		try {
			// On enregistre un mois supérieur à 12
			r.enregistrerCA(12, 100f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois supérieur à 11 doit générer une exception"); // Forcer l'échec du test
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	@Test
	public void testMoisInfZeroSalaireImpossible() {

		try {
			// On enregistre un mois inférieur à 0
			r.salaireMensuel(-30, 0.1f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois négatif doit générer une exception"); // Forcer l'échec du test
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	@Test
	public void testMoisSupOnzeSalaireImpossible() {

		try {
			// On enregistre un mois supérieur à 12
			r.salaireMensuel(12, 0.1f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois supérieur à 11 doit générer une exception"); // Forcer l'échec du test
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	@Test
	public void testgetNum(){
		assertEquals(r.getNumero(),36, "Numéro différents");
	}
	@Test
	public void testgetPrenom(){
		assertEquals(r.getPrenom(),"Rémi", "Prénom différent");
	}
	@Test
	public void testgetNom(){
		assertEquals(r.getNom(),"Bastide", "Nom différents");
	}


}
