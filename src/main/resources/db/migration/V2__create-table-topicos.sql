CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    estado CHAR NOT NULL,
    autor_id VARCHAR NOT NULL,
    curso_id VARCHAR NOT NULL,

    PRIMARY KEY(id)
);