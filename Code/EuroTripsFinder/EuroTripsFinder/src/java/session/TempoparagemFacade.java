/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Tempoparagem;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    public List<ResultadoPesquisaPar> caminhos(int localOrigemId, int localDestinoId) {

        localOrigemId = 7848;
        localDestinoId = 7849;
        Query query = em.createNativeQuery(" SELECT * FROM tempoparagem WHERE transporteviagemid IN ("
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

        List<Object> resultadosObjeto = query.getResultList();

        List<ResultadoPesquisa> resultados = new ArrayList<ResultadoPesquisa>();
        
        for(Object o : resultadosObjeto){
            resultados.add(new ResultadoPesquisa((Object[]) o));
        }

        List<ResultadoPesquisaPar> pares = new ArrayList<ResultadoPesquisaPar>();

        for(ResultadoPesquisa ro : resultados){
            if(ro.getLocalparagemid() == localOrigemId){
                for(ResultadoPesquisa rc : resultados){
                    if(rc.getLocalparagemid() == localDestinoId && ro.getViagemid().equals(rc.getViagemid())){
                        pares.add(new ResultadoPesquisaPar(ro, rc));
                    }
                }
            }
        }
        
        return pares;
    }

    public class ResultadoPesquisaPar{
        private ResultadoPesquisa _origem;
        private ResultadoPesquisa _destino;

        public ResultadoPesquisaPar(ResultadoPesquisa _origem, ResultadoPesquisa _destino) {
            this._origem = _origem;
            this._destino = _destino;
        }

        public ResultadoPesquisa getOrigem() {
            return _origem;
        }

        public void setOrigem(ResultadoPesquisa _origem) {
            this._origem = _origem;
        }

        public ResultadoPesquisa getDestino() {
            return _destino;
        }

        public void setDestino(ResultadoPesquisa _destino) {
            this._destino = _destino;
        }
        
        
        
    }
    
    public class ResultadoPesquisa {

        private int _id;
        private String _viagemid;
        private Time _datapartida;
        private Time _datachegada;
        private int _localparagemid;
        private int _localparagemsequencia;

        ResultadoPesquisa(Object[] params) {
            this._id = ((Integer) params[5]);
            this._viagemid =  ((String) params[0]);
            this._datapartida = (Time) params[1];
            this._datachegada = (Time) params[2];
            this._localparagemid = (Integer) params[3];
            this._localparagemsequencia = (Integer) params[4];
        }

        public ResultadoPesquisa(int _id, String _viagemid, Time _datapartida, Time _datachegada, int _localparagemid, int _localparagemsequencia) {
            this._id = _id;
            this._viagemid = _viagemid;
            this._datapartida = _datapartida;
            this._datachegada = _datachegada;
            this._localparagemid = _localparagemid;
            this._localparagemsequencia = _localparagemsequencia;
        }

        public int getId() {
            return _id;
        }

        public void setId(int _id) {
            this._id = _id;
        }

        public String getViagemid() {
            return _viagemid;
        }

        public void setViagemid(String _viagemid) {
            this._viagemid = _viagemid;
        }

        public Time getDatapartida() {
            return _datapartida;
        }

        public void setDatapartida(Time _datapartida) {
            this._datapartida = _datapartida;
        }

        public Time getDatachegada() {
            return _datachegada;
        }

        public void setDatachegada(Time _datachegada) {
            this._datachegada = _datachegada;
        }

        public int getLocalparagemid() {
            return _localparagemid;
        }

        public void setLocalparagemid(int _localparagemid) {
            this._localparagemid = _localparagemid;
        }

        public int getLocalparagemsequencia() {
            return _localparagemsequencia;
        }

        public void setLocalparagemsequencia(int _localparagemsequencia) {
            this._localparagemsequencia = _localparagemsequencia;
        }
       
        
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + this._id;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ResultadoPesquisa other = (ResultadoPesquisa) obj;
            if (this._id != other._id) {
                return false;
            }
            if ((this._viagemid == null) ? (other._viagemid != null) : !this._viagemid.equals(other._viagemid)) {
                return false;
            }
            if (this._datapartida != other._datapartida && (this._datapartida == null || !this._datapartida.equals(other._datapartida))) {
                return false;
            }
            if (this._datachegada != other._datachegada && (this._datachegada == null || !this._datachegada.equals(other._datachegada))) {
                return false;
            }
            if (this._localparagemid != other._localparagemid) {
                return false;
            }
            if (this._localparagemsequencia != other._localparagemsequencia) {
                return false;
            }
            return true;
        }
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
