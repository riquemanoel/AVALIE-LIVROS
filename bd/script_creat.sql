Drop table Avaliacao;
Drop table Livro;
Drop table Genero;
Drop table Nota;
Drop table Tag;
Drop table Cliente;

CREATE TABLE Cliente(
    id_cliente int AUTO_INCREMENT not null,
    nome varchar(45) not null,
    email varchar(255) not null unique,
    senha varchar(32) not null,
    paginas_lidas int DEFAULT 0,
    qtd_livros int DEFAULT 0,
    ativo BIT DEFAULT 0,

    primary key(id_cliente)
)AUTO_INCREMENT = 1;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_email_insert$$
    CREATE TRIGGER verifica_email_insert BEFORE INSERT ON Cliente FOR EACH ROW
    BEGIN
        DECLARE servidorDominio VARCHAR(50);

        IF((SELECT LOCATE("@", new.email)) = 0) THEN
            SELECT 0 FROM `Adicione @ no email` INTO @error;
        ELSE
            SELECT SUBSTRING_INDEX(new.email, '@', -1) INTO servidorDominio;

            IF((SELECT LOCATE(".", servidorDominio)) = 0) THEN
                SELECT 0 FROM `Estrutura Inválida` INTO @error;
            END IF;
        END IF;
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_email_update$$
    CREATE TRIGGER verifica_email_update BEFORE UPDATE ON Cliente FOR EACH ROW
    BEGIN
        DECLARE servidorDominio VARCHAR(50);

        IF((SELECT LOCATE("@", new.email)) = 0) THEN
            SELECT 0 FROM `Adicione @ no email` INTO @error;
        ELSE
            SELECT SUBSTRING_INDEX(new.email, '@', -1) INTO servidorDominio;

            IF((SELECT LOCATE(".", servidorDominio)) = 0) THEN
                SELECT 0 FROM `Estrutura Inválida` INTO @error;
            END IF;
        END IF;
    END $$
DELIMITER ;

CREATE TABLE Tag(
    id_tag int AUTO_INCREMENT not null,
    tag varchar(45) not null,

    primary key(id_tag)
)AUTO_INCREMENT = 1;

CREATE TABLE Nota(
    id_nota int not null,
    nota varchar(45) not null,

    primary key(id_nota)
);

CREATE TABLE Genero(
    id_genero int AUTO_INCREMENT not null,
    genero varchar(45) not null,

    primary key(id_genero)
)AUTO_INCREMENT = 1;

CREATE TABLE Livro(
    id_livro int AUTO_INCREMENT  not null,
    titulo varchar(100) not null,
    autor varchar(45),
    ano_lancamento int,
    numero_paginas_livro int not null,
    genero int not null,
    tag int not null,
    cliente int not null,
    data_cadastro DATE not null,
    ativo BIT DEFAULT 0,

    primary key(id_livro),
    FOREIGN KEY (tag) REFERENCES Tag(id_tag),
    FOREIGN KEY (cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (genero) REFERENCES Genero(id_genero)
)AUTO_INCREMENT = 1;

CREATE TABLE Avaliacao(
    id_avaliacao int AUTO_INCREMENT not null,
    nota int not null,
    comentario varchar(255),
    numero_paginas_lidas int,
    livro int not null,
    cliente int not null,

    primary key(id_avaliacao),
    FOREIGN KEY (nota) REFERENCES Nota(id_nota),
    FOREIGN KEY (livro) REFERENCES Livro(id_livro),
    FOREIGN KEY (cliente) REFERENCES Cliente(id_cliente)
)AUTO_INCREMENT = 1;



DELIMITER $$
    DROP TRIGGER IF EXISTS add_data_cadastro$$
    CREATE TRIGGER add_data_cadastro BEFORE INSERT ON Livro FOR EACH ROW
    BEGIN
        SET new.data_cadastro = CURRENT_DATE;
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS atualiza_paginas_lidas_insert$$
    CREATE TRIGGER atualiza_paginas_lidas_insert AFTER INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        call calcula_paginas_lidas(new.cliente);
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_ano_lancamento_insert$$
    CREATE TRIGGER verifica_ano_lancamento_insert BEFORE INSERT ON Livro FOR EACH ROW
    BEGIN
        IF(new.ano_lancamento > Year(CURRENT_DATE) || new.ano_lancamento < 500) THEN
        SELECT 0 FROM `Ano invalido!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_ano_lancamento_update$$
    CREATE TRIGGER verifica_ano_lancamento_update BEFORE UPDATE ON Livro FOR EACH ROW
    BEGIN
        IF(new.ano_lancamento > Year(CURRENT_DATE) || new.ano_lancamento < 500) THEN
        SELECT 0 FROM `Ano invalido!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS atualiza_paginas_lidas_update$$
    CREATE TRIGGER atualiza_paginas_lidas_update AFTER INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        call calcula_paginas_lidas(new.cliente);
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_paginas_lidas$$
    CREATE TRIGGER verifica_paginas_lidas BEFORE INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        IF(new.numero_paginas_lidas > (SELECT numero_paginas_livro FROM Livro WHERE id_livro = new.livro) ) THEN
        SELECT 0 FROM `Número de páginas inválido!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;

INSERT INTO Tag (tag) VALUES ('Lidos');
INSERT INTO Tag (tag) VALUES ('Lendo');
INSERT INTO Tag (tag) VALUES ('Quero ler');

INSERT INTO Nota (id_nota, nota) VALUES (0, 'Horrivel');
INSERT INTO Nota (id_nota, nota) VALUES (1, 'Ruim');
INSERT INTO Nota (id_nota, nota) VALUES (2, 'Bonzinho');
INSERT INTO Nota (id_nota, nota) VALUES (3, 'Bom');
INSERT INTO Nota (id_nota, nota) VALUES (4, 'Ótimo');
INSERT INTO Nota (id_nota, nota) VALUES (5, 'Perfeito');

INSERT INTO Genero (genero) VALUES ('Ação');
INSERT INTO Genero (genero) VALUES ('Terror');
INSERT INTO Genero (genero) VALUES ('Romance');
INSERT INTO Genero (genero) VALUES ('Ficção Científica');
INSERT INTO Genero (genero) VALUES ('Comédia');
INSERT INTO Genero (genero) VALUES ('Mistério');
INSERT INTO Genero (genero) VALUES ('Drama');
INSERT INTO Genero (genero) VALUES ('Policiais');
INSERT INTO Genero (genero) VALUES ('Teen');
INSERT INTO Genero (genero) VALUES ('Asiáticos');
INSERT INTO Genero (genero) VALUES ('Fantasia');


