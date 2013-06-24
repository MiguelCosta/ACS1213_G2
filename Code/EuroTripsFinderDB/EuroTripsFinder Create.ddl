CREATE TABLE TempoParagem (
  transporteviagemid    varchar(255) NOT NULL comment 'GTFS: stop_times->trip_id', 
  horachegada           time NOT NULL comment 'GTFS: stop_times->arrive_time', 
  horapartida           time NOT NULL comment 'GTFS: stop_times->departure_time', 
  localparagemid        int(10) NOT NULL comment 'GTFS: stop_times->stop_id', 
  localparagemsequencia int(10) NOT NULL comment 'GTFS: stop_times->stop_sequence') comment='GTFS: stop_times';
CREATE TABLE Calendario (
  servicoid  int(10) NOT NULL AUTO_INCREMENT comment 'GTFS: calendar->service_id', 
  segunda    tinyint(1) NOT NULL, 
  terca      tinyint(1) NOT NULL, 
  quarta     tinyint(1) NOT NULL, 
  quinta     tinyint(1) NOT NULL, 
  sexta      tinyint(1) NOT NULL, 
  sabado     tinyint(1) NOT NULL, 
  domingo    tinyint(1) NOT NULL, 
  datainicio date NOT NULL, 
  datafim    date NOT NULL, 
  PRIMARY KEY (servicoid));
CREATE TABLE TransporteViagem (
  Rotaid             int(10) NOT NULL, 
  servicoid          int(10) NOT NULL, 
  transporteviagemid varchar(255) NOT NULL, 
  PRIMARY KEY (transporteviagemid));
CREATE TABLE Rota (
  id          int(10) NOT NULL AUTO_INCREMENT comment 'GTFS: routes->route_id', 
  Companhiaid int(10) NOT NULL, 
  nomecurto   varchar(32) NOT NULL comment 'nome curto da rota
GTFS: routes->routes_short_name', 
  nomelongo   varchar(255) NOT NULL comment 'nome longo da rota
GTFS: routes->route_long_name', 
  tiporota    int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Companhia (
  id        int(10) NOT NULL AUTO_INCREMENT comment 'identificador da companhia, corresponde ao formato gtfs: agency->agency_id', 
  nome      varchar(255) NOT NULL comment 'nome da companhia
GTFS: agency->agency_name', 
  url       varchar(1024) NOT NULL comment 'GTFS: agency->agency_url', 
  timezone  varchar(255) NOT NULL comment 'fuso horário da companhia
GTFS:agency->agency_timezone', 
  lingua    varchar(255) NOT NULL comment 'lingua da companhia
GTFS: agency->agency_lang', 
  telefone  varchar(255) NOT NULL comment 'telefone da agencia
GTFS: agency->agenvy_phone', 
  ticketurl varchar(1024) NOT NULL comment 'url para se comprar bilhetes
GTFS: agency->agency_fare_ticket', 
  PRIMARY KEY (id));
CREATE TABLE Cliente (
  id       int(10) NOT NULL comment 'id do cliente', 
  nif      varchar(255) NOT NULL comment 'nif do cliente', 
  contacto varchar(255) NOT NULL comment 'contacto do cliente', 
  PRIMARY KEY (id));
CREATE TABLE Utilizador (
  id             int(10) NOT NULL AUTO_INCREMENT, 
  nome           varchar(255) NOT NULL comment 'nome do utilizador', 
  morada         varchar(1024) NOT NULL comment 'morada do utilizador', 
  funcao         varchar(255) NOT NULL comment 'indica se é administrador ou nao por exemplo', 
  dataregisto    date NOT NULL comment 'data em que o utilizador foi registado', 
  email          varchar(255) NOT NULL UNIQUE comment 'email do utilizador', 
  username       varchar(255) NOT NULL UNIQUE, 
  password       varchar(255) NOT NULL, 
  datanascimento date DEFAULT '0000-00-00 00:00:00' NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE ArtigoPublicitario (
  id         int(10) NOT NULL AUTO_INCREMENT, 
  nome       varchar(255) NOT NULL comment 'nome do artigo publicitario', 
  conteudo   varchar(2048) NOT NULL comment 'conteúdo html com a publicidade', 
  Contratoid int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Contrato (
  id         int(10) NOT NULL AUTO_INCREMENT comment 'id', 
  valor      decimal(10, 2) NOT NULL comment 'valor do contrato', 
  datainicio date NOT NULL comment 'data de inicio do contrato', 
  datafim    date NOT NULL comment 'data de fim do contrato', 
  clienteid  int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE TipoMeioTransporte (
  id   int(10) NOT NULL AUTO_INCREMENT, 
  nome varchar(255) NOT NULL comment 'nome do tipo de meio de transporte', 
  PRIMARY KEY (id));
CREATE TABLE MeioTransporte (
  id                   int(10) NOT NULL, 
  nome                 varchar(255) NOT NULL comment 'nome do meio de transporte', 
  servico              varchar(255) NOT NULL, 
  TipoMeioTransporteid int(10) NOT NULL, 
  Companhiaid          int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Viagem (
  id           int(10) NOT NULL AUTO_INCREMENT comment 'id da viagem', 
  nome         varchar(255) NOT NULL comment 'nome da viagem', 
  descricao    varchar(2048) NOT NULL comment 'descricao da viagem', 
  datainicio   date NOT NULL comment 'data de inicio da viagem', 
  datafim      date NOT NULL comment 'data de fim da viagem', 
  percursoid   int(10) NOT NULL, 
  Utilizadorid int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Percurso_Etapa (
  Percursoid int(10) NOT NULL, 
  Etapaid    int(10) NOT NULL, 
  PRIMARY KEY (Percursoid, 
  Etapaid));
CREATE TABLE Percurso (
  id           int(10) NOT NULL AUTO_INCREMENT, 
  limiteetapas int(10) DEFAULT 0 NOT NULL comment 'numero limite de etapas, se for 0 indica que nao há limite', 
  numeroetapas int(10) NOT NULL comment 'indica o numero de etapas que existe no percurso', 
  valortotal   decimal(10, 2) DEFAULT 0 NOT NULL comment 'indica o valor total do percurso, este campo acaba sempre por ser a soma do valor de cada etapa', 
  Utilizadorid int(10) NOT NULL, 
  nome         varchar(255), 
  PRIMARY KEY (id));
CREATE TABLE Atividade (
  id        int(10) NOT NULL AUTO_INCREMENT, 
  nome      varchar(255) NOT NULL comment 'nome da atividade', 
  descricao varchar(2048) NOT NULL, 
  cidadeid  int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE PontoInteresse (
  id        int(10) NOT NULL, 
  nome      varchar(255) NOT NULL comment 'nome do ponto de interesse', 
  descricao varchar(2048) NOT NULL comment 'descricao do ponto de interesse', 
  cidadeid  int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Cidade (
  id           int(10) NOT NULL AUTO_INCREMENT comment 'ID da cidade', 
  nome         varchar(255) NOT NULL comment 'nome da caidade', 
  pais         varchar(255) NOT NULL comment 'Nome da pais', 
  distrito     varchar(255) NOT NULL comment 'nome do distrito a que pertence a cidade', 
  regiao       varchar(255) NOT NULL comment 'nome da regiao a que pertence a cidade', 
  coordenadaid int(10) NOT NULL comment 'ID da coordenada que indica a localizacao da cidade', 
  PRIMARY KEY (id));
CREATE TABLE Coordenada (
  id        int(10) NOT NULL AUTO_INCREMENT comment 'ID da coordenada', 
  nome      varchar(255) NOT NULL comment 'Nome da coordenada', 
  latitude  decimal(19, 13) NOT NULL comment 'valor decimal que indica a latitude', 
  longitude decimal(19, 13) NOT NULL comment 'valor decimal da longitude', 
  PRIMARY KEY (id));
CREATE TABLE LocalParagem (
  id           int(10) NOT NULL AUTO_INCREMENT comment 'ID da estação', 
  nome         varchar(255) NOT NULL, 
  coordenadaid int(10) comment 'ID da coordenada que indica a locailizacao da estacao', 
  cidadeid     int(10), 
  codigo       varchar(255) NOT NULL comment 'GTFS: stops->stop_code', 
  descricao    varchar(2048) NOT NULL comment 'GTFS: stops->stop_desc', 
  PRIMARY KEY (id)) comment='GTFS: stops';
CREATE TABLE Etapa (
  id                    int(10) NOT NULL AUTO_INCREMENT, 
  datapartida           date NOT NULL, 
  datachegada           date NOT NULL, 
  valor                 decimal(10, 2) NOT NULL, 
  localparageminicialid int(10) NOT NULL, 
  localparagemdestinoid int(10) NOT NULL, 
  MeioTransporteid      int(10) NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE Percurso ADD INDEX FKPercurso573353 (Utilizadorid), ADD CONSTRAINT FKPercurso573353 FOREIGN KEY (Utilizadorid) REFERENCES Utilizador (id);
ALTER TABLE Viagem ADD INDEX FKViagem542694 (Utilizadorid), ADD CONSTRAINT FKViagem542694 FOREIGN KEY (Utilizadorid) REFERENCES Utilizador (id);
ALTER TABLE Etapa ADD INDEX FKEtapa96779 (MeioTransporteid), ADD CONSTRAINT FKEtapa96779 FOREIGN KEY (MeioTransporteid) REFERENCES MeioTransporte (id);
ALTER TABLE Contrato ADD INDEX FKContrato857532 (clienteid), ADD CONSTRAINT FKContrato857532 FOREIGN KEY (clienteid) REFERENCES Cliente (id);
ALTER TABLE Cliente ADD INDEX FKCliente459756 (id), ADD CONSTRAINT FKCliente459756 FOREIGN KEY (id) REFERENCES Utilizador (id);
ALTER TABLE ArtigoPublicitario ADD INDEX FKArtigoPubl5369 (Contratoid), ADD CONSTRAINT FKArtigoPubl5369 FOREIGN KEY (Contratoid) REFERENCES Contrato (id);
ALTER TABLE MeioTransporte ADD INDEX FKMeioTransp673657 (TipoMeioTransporteid), ADD CONSTRAINT FKMeioTransp673657 FOREIGN KEY (TipoMeioTransporteid) REFERENCES TipoMeioTransporte (id);
ALTER TABLE Viagem ADD INDEX FKViagem770414 (percursoid), ADD CONSTRAINT FKViagem770414 FOREIGN KEY (percursoid) REFERENCES Percurso (id);
ALTER TABLE Percurso_Etapa ADD INDEX FKPercurso_E489919 (Percursoid), ADD CONSTRAINT FKPercurso_E489919 FOREIGN KEY (Percursoid) REFERENCES Percurso (id);
ALTER TABLE Percurso_Etapa ADD INDEX FKPercurso_E587004 (Etapaid), ADD CONSTRAINT FKPercurso_E587004 FOREIGN KEY (Etapaid) REFERENCES Etapa (id);
ALTER TABLE Atividade ADD INDEX FKAtividade952824 (cidadeid), ADD CONSTRAINT FKAtividade952824 FOREIGN KEY (cidadeid) REFERENCES Cidade (id);
ALTER TABLE PontoInteresse ADD INDEX FKPontoInter656145 (cidadeid), ADD CONSTRAINT FKPontoInter656145 FOREIGN KEY (cidadeid) REFERENCES Cidade (id);
ALTER TABLE Cidade ADD INDEX FKCidade627273 (coordenadaid), ADD CONSTRAINT FKCidade627273 FOREIGN KEY (coordenadaid) REFERENCES Coordenada (id);
ALTER TABLE LocalParagem ADD INDEX FKLocalParag92246 (cidadeid), ADD CONSTRAINT FKLocalParag92246 FOREIGN KEY (cidadeid) REFERENCES Cidade (id);
ALTER TABLE LocalParagem ADD INDEX FKLocalParag896273 (coordenadaid), ADD CONSTRAINT FKLocalParag896273 FOREIGN KEY (coordenadaid) REFERENCES Coordenada (id);
ALTER TABLE TransporteViagem ADD INDEX FKTransporte116418 (servicoid), ADD CONSTRAINT FKTransporte116418 FOREIGN KEY (servicoid) REFERENCES Calendario (servicoid);
ALTER TABLE TransporteViagem ADD INDEX FKTransporte704329 (Rotaid), ADD CONSTRAINT FKTransporte704329 FOREIGN KEY (Rotaid) REFERENCES Rota (id);
ALTER TABLE Rota ADD INDEX FKRota82898 (Companhiaid), ADD CONSTRAINT FKRota82898 FOREIGN KEY (Companhiaid) REFERENCES Companhia (id);
ALTER TABLE MeioTransporte ADD INDEX FKMeioTransp596872 (Companhiaid), ADD CONSTRAINT FKMeioTransp596872 FOREIGN KEY (Companhiaid) REFERENCES Companhia (id);
ALTER TABLE TempoParagem ADD INDEX FKTempoParag217368 (localparagemid), ADD CONSTRAINT FKTempoParag217368 FOREIGN KEY (localparagemid) REFERENCES LocalParagem (id);
ALTER TABLE TempoParagem ADD INDEX FKTempoParag50050 (transporteviagemid), ADD CONSTRAINT FKTempoParag50050 FOREIGN KEY (transporteviagemid) REFERENCES TransporteViagem (transporteviagemid);
ALTER TABLE Etapa ADD INDEX FKEtapa296178 (localparagemdestinoid), ADD CONSTRAINT FKEtapa296178 FOREIGN KEY (localparagemdestinoid) REFERENCES LocalParagem (id);
ALTER TABLE Etapa ADD INDEX FKEtapa808378 (localparageminicialid), ADD CONSTRAINT FKEtapa808378 FOREIGN KEY (localparageminicialid) REFERENCES LocalParagem (id);

