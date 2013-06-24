
SELECT * FROM tempoparagem WHERE transporteviagemid IN (

SELECT DISTINCT transporteviagemid FROM tempoparagem 
WHERE transporteviagemid 
	IN 
	   (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=:stop_id_origem) 
AND transporteviagemid 
	IN 
       (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=:stop_id_destino)

)




