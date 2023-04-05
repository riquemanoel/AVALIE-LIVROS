/* Verifica se email contém '@', antes da inserção na tabela Cliente */

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_email$$
    CREATE TRIGGER verifica_email BEFORE INSERT ON Cliente FOR EACH ROW
    BEGIN

        IF((SELECT LOCATE("@", new.email)) = 0) THEN
        SELECT 0 FROM `Adicione @ no email` INTO @error;
        END IF;

    END $$
DELIMITER ;

INSERT INTO Cliente (nome, email, senha) VALUES ('Junior', 'juninhoemail.com', '12345');

/* Preenche o campo data_cadastro na tabela Livro */

DELIMITER $$
    DROP TRIGGER IF EXISTS add_data_cadastro$$
    CREATE TRIGGER add_data_cadastro BEFORE INSERT ON Livro FOR EACH ROW
    BEGIN
        SET new.data_cadastro = CURRENT_DATE;
    END $$
DELIMITER ;

INSERT INTO Livro (nome, email, senha) VALUES ('Junior', 'juninhoemail.com', '12345');

/* Atualiza as paginas lidas na tabela cliente após cada inserção na tabela Avaliação  */

DELIMITER $$
    DROP TRIGGER IF EXISTS atualiza_paginas_lidas_insert$$
    CREATE TRIGGER atualiza_paginas_lidas_insert AFTER INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        call calcula_paginas_lidas(new.cliente);
    END $$
DELIMITER ;

/* Atualiza as paginas lidas na tabela cliente após cada modificação na tabela Avaliação  */

DELIMITER $$
    DROP TRIGGER IF EXISTS atualiza_paginas_lidas_update$$
    CREATE TRIGGER atualiza_paginas_lidas_update AFTER UPDATE ON Avaliacao FOR EACH ROW
    BEGIN
        call calcula_paginas_lidas(new.cliente);
    END $$
DELIMITER ;

INSERT INTO Avaliacao (nota, comentario, numero_paginas_lidas, livro, cliente) 
VALUES (5, 'Muito bom, chorei no meio', 300, 2, 5);

/* Verifica se o ano de lançamento do livro é valido */

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_ano_lancamento$$
    CREATE TRIGGER verifica_ano_lancamento BEFORE INSERT ON Livro FOR EACH ROW
    BEGIN
        IF(new.ano_lancamento > Year(CURRENT_DATE) || new.ano_lancamento < 500) THEN
        SELECT 0 FROM `Ano invalido!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_ano_lancamento$$
    CREATE TRIGGER verifica_ano_lancamento BEFORE UPDATE ON Livro FOR EACH ROW
    BEGIN
        IF(new.ano_lancamento > Year(CURRENT_DATE) || new.ano_lancamento < 500) THEN
        SELECT 0 FROM `Ano invalido!` INTO @error;
        END IF;
        
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


DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_paginas_lidas$$
    CREATE TRIGGER verifica_paginas_lidas AFTER INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        IF(new.numero_paginas_lidas > (SELECT numero_paginas_livro FROM Livro WHERE id_livro = new.livro) ) THEN
        SELECT 0 FROM `SEU IMBECIL NÂO DÁ PRA LER MAIS PÁGONAS DO QUE O LIVRO TEM!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;

DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_email$$
    CREATE TRIGGER verifica_email BEFORE INSERT ON Cliente FOR EACH ROW
    BEGIN
        DECLARE servidorDominio VARCHAR(50);

        IF((SELECT LOCATE("@", new.email)) = 0) THEN
            SELECT 0 FROM `Adicione @ no email` INTO @error;
        ELSE
            SELECT SUBSTRING_INDEX(new.email, '@', -1) INTO servidorDominio;

            IF((SELECT LOCATE(".", servidorDominio)) = 0) THEN
                SELECT 0 FROM `Estrutura do email inválida!` INTO @error;
            END IF;
        END IF;
    END $$
DELIMITER ;


DELIMITER $$
    DROP TRIGGER IF EXISTS verifica_pg_lidas$$
    CREATE TRIGGER verifica_pg_lidas BEFORE INSERT ON Avaliacao FOR EACH ROW
    BEGIN
        IF(new.numero_paginas_lidas < (SELECT verifica_paginas_lidas(new.livro))) THEN
        SELECT 0 FROM `Página lidas menor que o ultimo registro!` INTO @error;
        END IF;

        IF(new.numero_paginas_lidas > (SELECT numero_paginas_livro FROM Livro WHERE id_livro = new.livro)) THEN
        SELECT 0 FROM `Página lidas ultrapassou as páginas do livro!` INTO @error;
        END IF;
        
    END $$
DELIMITER ;
