delimiter $$
    drop procedure if exists add_numero_paginas_lidas $$
        create procedure add_numero_paginas_lidas(in livro int, in num int)
        begin

        if(SE)
        select nome from pessoa where nome like concat('%',sobrenome);
    end $$
delimiter ;

call ex1('Black');


DELIMITER $$
    DROP PROCEDURE IF EXISTS getGenero $$
    CREATE PROCEDURE getGenero()
    BEGIN
        SELECT es.estadoID AS estadoID, es.estado AS estado,
        pa.paisID AS paisID, pa.pais AS pais
        FROM athena_estado AS es
        JOIN athena_pais AS pa USING(paisID);
    END $$
DELIMITER ;

CALL getGenero();