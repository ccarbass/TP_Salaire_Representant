

public class Representant {

	private final int numero;
	private final String nom;
	private final String prenom;
	private String adresse;
	private float salaireFixe;
	public float[] CAMensuel;
	public ZoneGeographique secteur;


	public Representant(int numero, String nom, String prenom, ZoneGeographique secteur) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.CAMensuel=new float[12];
		this.secteur = secteur;
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public float getSalaireFixe() {
		return salaireFixe;
	}

	public void setSalaireFixe(float salaireFixe) {
		this.salaireFixe = salaireFixe;
	}

	public ZoneGeographique getSecteur() {
		return this.secteur;
	}

	public ZoneGeographique setSecteur(ZoneGeographique secteur) {
		return this.secteur=secteur;
	}

	/**
	 * Enregistre le CA de ce représentant pour un mois donné. 
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param montant le CA réalisé pour ce mois (positif ou nul)
	 **/
	public void enregistrerCA(int mois, float montant) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11) {
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		}
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant doit être positif ou null");
		}
		CAMensuel[mois]=montant;
	}

	/**
	 * Calcule le salaire mensuel de ce répresentant pour un mois donné
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param pourcentage le pourcentage (>= 0 ) à appliquer sur le CA réalisé pour ce mois
	 * @return le salaire pour ce mois, tenant compte du salaire fixe, de l'indemnité repas, et du pourcentage sur CA
	 */
	public float salaireMensuel(int mois, float pourcentage) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11) {
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		}
		if (pourcentage < 0) {
			throw new IllegalArgumentException("Le montant doit être positif ou null");
		}
		float salaire= this.salaireFixe+this.CAMensuel[mois]*pourcentage+this.secteur.getIndemniteRepas();
		return salaire;

	}

	@Override
	public String toString() {
		return "Representant{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + '}';
	}

}
