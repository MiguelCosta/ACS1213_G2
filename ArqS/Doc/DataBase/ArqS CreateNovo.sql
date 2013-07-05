CREATE TABLE Categoria (
  id            int(10) NOT NULL AUTO_INCREMENT, 
  nome          varchar(255) NOT NULL UNIQUE, 
  descricao     varchar(1023) NOT NULL, 
  precoDeposito decimal(10, 2) NOT NULL, 
  precoPorHora  decimal(10, 2) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE ServicoTaxi (
  id             int(10) NOT NULL AUTO_INCREMENT, 
  Utilizadorid   int(10) NOT NULL, 
  Localpartidaid int(10) NOT NULL, 
  Localchegadaid int(10) NOT NULL, 
  datapartida    datetime NOT NULL, 
  bagagem        tinyint(1) DEFAULT 0 NOT NULL, 
  passageiros    int(10), 
  codigotaxi     varchar(255), 
  PRIMARY KEY (id));
CREATE TABLE Utilizador (
  id       int(10) NOT NULL AUTO_INCREMENT, 
  nome     varchar(255) NOT NULL, 
  email    varchar(255) NOT NULL UNIQUE, 
  password varchar(45) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Servico (
  id             int(10) NOT NULL AUTO_INCREMENT, 
  Utilizadorid   int(10) NOT NULL, 
  LocalPartidaid int(10) NOT NULL, 
  LocalChegadaid int(10) NOT NULL, 
  Carroid        int(10) NOT NULL, 
  dataPartida    timestamp NOT NULL, 
  dataChegada    timestamp NOT NULL, 
  depositoCheio  tinyint(1) NOT NULL, 
  preco          decimal(10, 2) NOT NULL, 
  Obs            int(10), 
  GPS            tinyint(1), 
  cadeiraBebe    tinyint(1), 
  seguroCTRiscos tinyint, 
  condutorExtra  tinyint, 
  PRIMARY KEY (id));
CREATE TABLE Carro (
  id          int(10) NOT NULL AUTO_INCREMENT, 
  matricula   varchar(255) NOT NULL UNIQUE, 
  descricao   varchar(1023) NOT NULL, 
  imagem      varchar(1023) NOT NULL, 
  Categoriaid int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Local (
  id        int(10) NOT NULL AUTO_INCREMENT, 
  nome      varchar(255) NOT NULL, 
  latitude  numeric(19, 9), 
  longitude decimal(19, 9), 
  Cidadeid  int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Cidade (
  id     int(10) NOT NULL AUTO_INCREMENT, 
  nome   varchar(255) NOT NULL, 
  Paisid int(10) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Pais (
  id   int(10) NOT NULL AUTO_INCREMENT, 
  nome varchar(255) NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE Servico ADD INDEX FKServico742812 (Carroid), ADD CONSTRAINT FKServico742812 FOREIGN KEY (Carroid) REFERENCES Carro (id);
ALTER TABLE Servico ADD INDEX FKServico68876 (LocalChegadaid), ADD CONSTRAINT FKServico68876 FOREIGN KEY (LocalChegadaid) REFERENCES Local (id);
ALTER TABLE Servico ADD INDEX FKServico562328 (LocalPartidaid), ADD CONSTRAINT FKServico562328 FOREIGN KEY (LocalPartidaid) REFERENCES Local (id);
ALTER TABLE Servico ADD INDEX FKServico196310 (Utilizadorid), ADD CONSTRAINT FKServico196310 FOREIGN KEY (Utilizadorid) REFERENCES Utilizador (id);
ALTER TABLE Carro ADD INDEX FKCarro67785 (Categoriaid), ADD CONSTRAINT FKCarro67785 FOREIGN KEY (Categoriaid) REFERENCES Categoria (id);
ALTER TABLE Local ADD INDEX FKLocal184904 (Cidadeid), ADD CONSTRAINT FKLocal184904 FOREIGN KEY (Cidadeid) REFERENCES Cidade (id);
ALTER TABLE Cidade ADD INDEX FKCidade869916 (Paisid), ADD CONSTRAINT FKCidade869916 FOREIGN KEY (Paisid) REFERENCES Pais (id);
ALTER TABLE ServicoTaxi ADD INDEX FKServicoTax29215 (Localchegadaid), ADD CONSTRAINT FKServicoTax29215 FOREIGN KEY (Localchegadaid) REFERENCES Local (id);
ALTER TABLE ServicoTaxi ADD INDEX FKServicoTax311171 (Localpartidaid), ADD CONSTRAINT FKServicoTax311171 FOREIGN KEY (Localpartidaid) REFERENCES Local (id);
ALTER TABLE ServicoTaxi ADD INDEX FKServicoTax68191 (Utilizadorid), ADD CONSTRAINT FKServicoTax68191 FOREIGN KEY (Utilizadorid) REFERENCES Utilizador (id);

