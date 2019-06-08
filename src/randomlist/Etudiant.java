/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomlist;

/**
 *
 * @author Cena
 */
public class Etudiant {
    
    private int numero;
    private String matricule;
    private String Nom;
    private int N_groupe = 0;

    public Etudiant(int numero, String matricule, String Nom) {
        this.numero = numero;
        this.matricule = matricule;
        this.Nom = Nom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getN_groupe() {
        return N_groupe;
    }

    public void setN_groupe(int N_groupe) {
        this.N_groupe = N_groupe;
    }
    
}
