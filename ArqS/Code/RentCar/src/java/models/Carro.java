package models;
// Generated 20/Mai/2013 14:00:49 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Carro generated by hbm2java
 */
public class Carro  implements java.io.Serializable {


     private int id;
     private Categoria categoria;
     private String matricula;
     private String descricao;
     private String imagem;
     private Set<Servico> servicos = new HashSet<Servico>(0);

    public Carro() {
    }

	
    public Carro(int id, Categoria categoria, String matricula, String descricao, String imagem) {
        this.id = id;
        this.categoria = categoria;
        this.matricula = matricula;
        this.descricao = descricao;
        this.imagem = imagem;
    }
    public Carro(int id, Categoria categoria, String matricula, String descricao, String imagem, Set<Servico> servicos) {
       this.id = id;
       this.categoria = categoria;
       this.matricula = matricula;
       this.descricao = descricao;
       this.imagem = imagem;
       this.servicos = servicos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getImagem() {
        return this.imagem;
    }
    
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public Set<Servico> getServicos() {
        return this.servicos;
    }
    
    public void setServicos(Set<Servico> servicos) {
        this.servicos = servicos;
    }




}

