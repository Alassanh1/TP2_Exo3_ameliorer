package Etudiant;

public class Etudiant {
    private int id;
    private String prenom;
    private String nom;
    private int age;
    private String niveau;
    private String email;
    private String sexe;

    // Constructeur par défaut
    public Etudiant() {}

    // Constructeur avec initialisation
    public Etudiant(int id, String prenom, String nom, int age, String niveau, String email, String sexe) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.niveau = niveau;
        this.email = email;
        this.sexe = sexe;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
}
