CREATE TABLE IF NOT EXISTS musica (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  duracao INT NOT NULL,
  album_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_musica_album_idx (album_id ASC),
  CONSTRAINT fk_musica_album
    FOREIGN KEY (album_id)
    REFERENCES album (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO musica (nome, duracao, album_id) VALUES ('Musica do album Construcao', 340, 1);
