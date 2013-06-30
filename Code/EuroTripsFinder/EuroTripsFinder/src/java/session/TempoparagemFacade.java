/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Tempoparagem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class TempoparagemFacade extends AbstractFacade<Tempoparagem> {

    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TempoparagemFacade() {
        super(Tempoparagem.class);
    }

    public List<Tempoparagem> caminhos(int localOrigemId, int localDestinoId) {

        Query query = em.createNativeQue-ry(" SELECT * FROM tempoparagem WHERE transporteviagemid IN ("
                + " SELECT transporteviagem.transporteviagemid "
                + " FROM transporteviagem,calendario,rota "
                + " WHERE transporteviagemid IN ( "
                + "		SELECT DISTINCT transporteviagemid FROM tempoparagem "
                + "		WHERE transporteviagemid "
                + "			IN "
                + "			   (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=?) "
                + "		AND transporteviagemid "
                + "			IN "
                + "			   (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=?)) "
                + " AND transporteviagem.servicoid = calendario.servicoid "
                + " AND transporteviagem.Rotaid = rota.id "
                + " AND calendario.datainicio >= '2012-01-01' "
                + " AND calendario.datafim <= '2012-12-31' "
                + " AND calendario.quarta = 1) "
                + " AND localparagemid=? OR localparagemid=?");

        query.setParameter(1, localOrigemId);
        query.setParameter(2, localDestinoId);
        query.setParameter(3, localOrigemId);
        query.setParameter(4, localDestinoId);

        List<Tempoparagem> resultados = query.getResultList();
        
        List<Tempoparagem> resultados2 = new ArrayList<Tempoparagem>();
        
        HashMap<Tempoparagem, Tempoparagem> pares = new HashMap<Tempoparagem, Tempoparagem>();
        for (Object x : resultados) {
            
            if (((Tempoparagem) x).getLocalparagemid().getId() == localOrigemId) {
                pares.put(((Tempoparagem) x), null);
            }
        }

        for (Tempoparagem p : pares.keySet()) {
            for (Tempoparagem t : resultados) {
                if (t.getLocalparagemid().getId() == localDestinoId
                        && t.getTransporteviagemid().getTransporteviagemid().equals(t.getTransporteviagemid().getTransporteviagemid())) {
                    pares.put(p, t);
                }
            }
        }

        return resultados;
    }
}
//
// Query query = em.createNativeQuery("SELECT * FROM Tempoparagem WHERE Transporteviagemid IN (" +
//"SELECT Transporteviagem.Transporteviagemid" +
//"FROM Transporteviagem,Calendario,Rota" +
//"WHERE transporteviagemid IN (" +
//"		SELECT DISTINCT transporteviagemid FROM Tempoparagem" +
//"		WHERE transporteviagemid " +
//"			IN " +
//"			   (SELECT DISTINCT transporteviagemid FROM Tempoparagem WHERE localparagemid=7848) " +
//"		AND transporteviagemid " +
//"			IN " +
//"			   (SELECT DISTINCT transporteviagemid FROM Tempoparagem WHERE localparagemid=7849))" +
//"AND Transporteviagem.servicoid = Calendario.servicoid" +
//"AND Transporteviagem.Rotaid = Rota.id" +
//"AND Calendario.datainicio >= '2012-01-01'" +
//"AND Calendario.datafim <= '2012-12-31'" +
//"AND Calendario.quarta = 1)" +
//"AND localparagemid=7848 OR localparagemid=7849");
