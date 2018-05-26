CREATE TABLE artista (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NULL,
  nacionalidade VARCHAR(100) NULL,
  PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO artista (nome, nacionalidade) VALUES ('Chico Buarque', 'Brasileiro');
INSERT INTO artista (nome, nacionalidade) VALUES ('Zeca Baleiro', 'Brasileiro');
