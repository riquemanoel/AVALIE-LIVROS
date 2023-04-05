/* Calcula e atualiza o numero de paginas lidas na tabela Cliente */

delimiter $$
    drop procedure if exists paginas_lidas $$
    create procedure paginas_lidas(id_cliente int)
    begin
        DECLARE fimLoop INT DEFAULT 0;
        DECLARE numero_paginas_lidas INT;
        DECLARE cliente INT;
        DECLARE pag INT DEFAULT 0;

        DECLARE cur_paginas_lidas CURSOR
        FOR SELECT a.cliente, a.numero_paginas_lidas FROM Avaliacao as a;

        DECLARE CONTINUE handler for NOT found SET fimLoop = 1;

        OPEN cur_paginas_lidas;

            meuLoop: LOOP
                fetch cur_paginas_lidas into cliente, numero_paginas_lidas;
                
                if fimLoop = 1 then
                    leave meuLoop;
                end if;
                
                if (cliente = id_cliente) then
                    SELECT pag + numero_paginas_lidas INTO pag;
                    UPDATE Cliente AS c SET c.paginas_lidas = pag WHERE c.id_cliente = cliente;
                end if;
            end loop meuLoop;

        CLOSE cur_paginas_lidas;

    end $$
delimiter ;

call paginas_lidas(3);

/* Calcula e atualiza o numero de livros cadastados pelo cliente */

delimiter $$
    drop procedure if exists qtd_livros $$
    create procedure qtd_livros(id_cliente int)
    begin
        DECLARE fimLoop INT DEFAULT 0;
        DECLARE cliente INT;
        DECLARE qtd_livros INT DEFAULT 0;

        DECLARE cur_qtd_livros CURSOR
        FOR SELECT l.cliente FROM Livro as l;

        DECLARE CONTINUE handler for NOT found SET fimLoop = 1;

        OPEN cur_qtd_livros;

            meuLoop: LOOP
                fetch cur_qtd_livros into cliente;
                
                if fimLoop = 1 then
                    leave meuLoop;
                end if;
                
                if (cliente = id_cliente) then
                    SELECT qtd_livros + 1 INTO qtd_livros;
                    UPDATE Cliente AS c SET c.qtd_livros = qtd_livros WHERE c.id_cliente = cliente;
                end if;
            end loop meuLoop;

        CLOSE cur_qtd_livros;

    end $$
delimiter ;

call qtd_livros(3);


/* Retorna uma lista de livros cadastados pelo cliente de um determinado genero */

delimiter $$
    drop procedure if exists qtd_livros_genero $$
    create procedure qtd_livros_genero(in id_cliente int, in id_genero int)
    begin
        select l.titulo as Titulo, g.genero as Genero, t.tag as Tag
        from Livro as l, Genero as g, Tag as t, Cliente as c
        where l.genero = g.id_genero and l.cliente = c.id_cliente and l.tag = t.id_tag
        and l.cliente = id_cliente and l.genero = id_genero;
    end $$
delimiter ;

call qtd_livros_genero(2, 3);

delimiter $$
drop function if exists verifica_paginas_lidas $$
create function verifica_paginas_lidas (id_livro int)
returns VARCHAR(25)
deterministic
    begin
        DECLARE fimLoop INT DEFAULT 0;
        DECLARE numero_paginas_lidas INT;
        DECLARE livro INT;
        DECLARE pag INT DEFAULT 0;

        DECLARE cur_paginas_lidas CURSOR
        FOR SELECT a.livro, a.numero_paginas_lidas FROM Avaliacao as a;

        DECLARE CONTINUE handler for NOT found SET fimLoop = 1;

        OPEN cur_paginas_lidas;

            meuLoop: LOOP
                fetch cur_paginas_lidas into livro, numero_paginas_lidas;
                
                if fimLoop = 1 then
                    leave meuLoop;
                end if;
                
                if (livro = id_livro AND numero_paginas_lidas > pag) then
                    SELECT numero_paginas_lidas INTO pag;
                end if;
            end loop meuLoop;

        CLOSE cur_paginas_lidas;

        RETURN pag;
    end $$
delimiter ;

SELECT verifica_paginas_lidas(3);


delimiter $$
drop function if exists verifica_paginas_lidas $$
create function verifica_paginas_lidas (id_livro int)
returns VARCHAR(25)
deterministic
    begin
        DECLARE fimLoop INT DEFAULT 0;
        DECLARE numero_paginas_lidas INT;
        DECLARE livro INT;
        DECLARE pag INT DEFAULT 0;

        DECLARE cur_paginas_lidas CURSOR
        FOR SELECT a.livro, a.numero_paginas_lidas FROM Avaliacao as a;

        DECLARE CONTINUE handler for NOT found SET fimLoop = 1;

        OPEN cur_paginas_lidas;

            meuLoop: LOOP
                fetch cur_paginas_lidas into livro, numero_paginas_lidas;
                
                if fimLoop = 1 then
                    leave meuLoop;
                end if;
                
                if (livro = id_livro AND numero_paginas_lidas > pag) then
                    SELECT numero_paginas_lidas INTO pag;
                end if;
            end loop meuLoop;

        CLOSE cur_paginas_lidas;

        RETURN pag;
    end $$
delimiter ;


delimiter $$
    drop procedure if exists calcula_paginas_lidas $$
    create procedure calcula_paginas_lidas(id_cliente int)
    begin
        DECLARE fimLoop INT DEFAULT 0;
        DECLARE livro INT;
        DECLARE cliente INT;
        DECLARE pag INT DEFAULT 0;

        DECLARE cur_paginas_lidas CURSOR
        FOR SELECT a.id_livro, a.cliente FROM Livro as a;

        DECLARE CONTINUE handler for NOT found SET fimLoop = 1;

        OPEN cur_paginas_lidas;

            meuLoop: LOOP
                fetch cur_paginas_lidas into livro, cliente;
                
                if fimLoop = 1 then
                    leave meuLoop;
                end if;
                
                if (cliente = id_cliente) then
                    SELECT verifica_paginas_lidas(livro) + pag INTO pag;
                end if;
            end loop meuLoop;

        CLOSE cur_paginas_lidas;

        UPDATE Cliente AS c SET c.paginas_lidas = pag WHERE c.id_cliente = id_cliente;
    end $$
delimiter ;

CALL calcula_paginas_lidas(1);







