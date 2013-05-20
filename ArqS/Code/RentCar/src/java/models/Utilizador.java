package models;
// Generated 20/Mai/2013 14:00:49 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Utilizador generated by hbm2java
 */
public class Utilizador  implements java.io.Serializable {


     private Integer id;
     private String nome;
     private String email;
     private Set<Servico> servicos = new HashSet<Servico>(0);

    public Utilizador() {
    }

	
    public Utilizador(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    public Utilizador(String nome, String email, Set<Servico> servicos) {
       this.nome = nome;
       this.email = email;
       this.servicos = servicos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Servico> getServicos() {
        return this.servicos;
    }
    
    public void setServicos(Set<Servico> servicos) {
        this.servicos = servicos;
    }




}


