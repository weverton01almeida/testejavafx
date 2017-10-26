CREATE DATABASE if not exists banco_teste;
USE banco_teste;
CREATE TABLE IF NOT EXISTS tipos_bd(  
    tip_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  
    tip_sigla VARCHAR(30) NOT NULL

)ENGINE = InnoDB;

INSERT INTO `tipos_bd` (`tip_sigla`) VALUES
('TIPO A'),
('TIPO B'),
('TIPO C'),
('TIPO D'),
('TIPO E');

CREATE TABLE IF NOT EXISTS banco_de_dados( 
Banco_Cod INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
banco_NomeBancoDados VARCHAR(50), 
banco_TipoBanco INT NOT NULL,
CONSTRAINT FK_tipos_banco FOREIGN KEY (banco_TipoBanco) REFERENCES tipos_bd (tip_id) 
)ENGINE = INNODB;

INSERT INTO `banco_de_dados` (`banco_NomeBancoDados`,`banco_TipoBanco`) VALUES
('NomeA',1),
('NomeB',1),
('NomeC',3),
('NomeD',4),
('NomeE',5),
('NomeF',4),
('NomeG',5),
('NomeH',5);