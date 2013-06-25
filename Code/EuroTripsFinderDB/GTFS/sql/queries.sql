

SELECT * FROM tempoparagem WHERE transporteviagemid IN (

SELECT 
transporteviagem.transporteviagemid

FROM transporteviagem,calendario,rota

WHERE transporteviagemid IN (

		SELECT DISTINCT transporteviagemid FROM tempoparagem
		WHERE transporteviagemid 
			IN 
			   (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=7848) 
		AND transporteviagemid 
			IN 
			   (SELECT DISTINCT transporteviagemid FROM tempoparagem WHERE localparagemid=7849)

	)
AND transporteviagem.servicoid = calendario.servicoid
AND transporteviagem.Rotaid = rota.id
AND calendario.datainicio >= '2012-01-01'
AND calendario.datafim <= '2012-12-31'
AND calendario.quarta = 1

)
AND localparagemid=7848 OR localparagemid=7849

