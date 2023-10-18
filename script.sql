CREATE TABLE ARMAZEM (
    armazem_id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    capacidade INT NOT NULL,
    responsavel_id BIGINT,
    FOREIGN KEY (responsavel_id) REFERENCES RESPONSAVEL(responsavel_id)
);

CREATE TABLE RESPONSAVEL (
    responsavel_id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE PRODUTO (
    produto_id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    data_validade DATE NOT NULL,
    armazem_id BIGINT,
    FOREIGN KEY (armazem_id) REFERENCES ARMAZEM(armazem_id)
);
