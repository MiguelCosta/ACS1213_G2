/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Miguel
 */
public interface IAtividade {

    void setId(Integer id);

    Integer getId();

    void setNome(String nome);

    String getNome();

    void setDescricao(String descricao);

    String getDescricao();
}
