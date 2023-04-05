/* Calcula e retorna a porcentagem de leitura do leitura do livro  */

delimiter $$
    drop function if exists porcentagem $$
        create function porcentagem (numero_paginas_livro int, numero_paginas_lidas int)
        returns varchar(4)
        deterministic
        begin
        DECLARE retorno varchar(4);
        DECLARE porc float;
        DECLARE red int;

        SELECT numero_paginas_lidas * 100 / numero_paginas_livro INTO porc;
        SELECT ROUND(porc, 0) INTO red;
        SELECT CONCAT(red, '%') INTO retorno;

        RETURN retorno;
    end $$
delimiter ;

SELECT porcentagem(300, 100);

/* Calcula e retorna quantos dias foram necessarios para a leitura do livro  */

delimiter $$
    drop function if exists dias_leitura $$
        create function dias_leitura (data_inicio_leitura DATE, data_termino_leitura DATE)
        returns INT
        deterministic
        begin
        DECLARE retorno INT;

        SELECT TIMESTAMPDIFF(DAY, data_inicio_leitura, data_termino_leitura) INTO retorno;
        RETURN retorno;
    end $$
delimiter ;

SELECT dias_leitura('2023-01-18', '2023-01-18');

delimiter $$
drop function if exists verifica_estrutura_email $$
create function verifica_estrutura_email (email varchar(150))
returns VARCHAR(25)
deterministic
    begin
        DECLARE retorno VARCHAR(25);
        DECLARE servidorDominio VARCHAR(50);
        IF((SELECT LOCATE("@", email)) = 0) THEN
            SELECT 'Estrutura Inválida' INTO retorno;
        ELSE
            SELECT SUBSTRING_INDEX(email, '@', -1) INTO servidorDominio;
        IF((SELECT LOCATE(".", servidorDominio)) = 0) THEN
            SELECT 'Estrutura Inválida' INTO retorno;
        ELSE
            SELECT 'Estrutura Válida' INTO retorno;
        END IF;
        END IF;
        RETURN retorno;
    end $$
delimiter ;

SELECT verifica_estrutura_email ('wagner.weinert@ifpr.edu.br');



