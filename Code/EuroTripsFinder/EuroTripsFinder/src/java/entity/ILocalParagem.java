/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Miguel
 */
public interface ILocalParagem {
    
    void setNome(String nome);
    
    String getNome();
    
    void setDescricao(String descricao);
    
    String getDescricao();
    
    void setCoordenadaid(Coordenada c);
    
    Coordenada getCoordenadaid();
    
}
