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



INSERT INTO Cliente (nome, email, senha) VALUES ('Ricardo', 'Ric@email.com', '12345');
INSERT INTO Cliente (nome, email, senha) VALUES ('Maria', 'maria@email.com', '54321');
INSERT INTO Cliente (nome, email, senha) VALUES ('Zé', 'zeze@email.com', '32415');
INSERT INTO Cliente (nome, email, senha) VALUES ('Marcos', 'masr@email.com', '13245');
INSERT INTO Cliente (nome, email, senha) VALUES ('Lucas', 'lucas@email.com', '53241');
INSERT INTO Cliente (nome, email, senha) VALUES ('Junior', 'juninho@email.com', '12345');


INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Abismo', 4, 225, 1, 4);
INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Conjurador', 11, 300, 2, 2);
INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Casa de vidro', 6, 350, 3, 1);
INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Antologia dos cavalos', 9, 300, 1, 3);
INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Principe Cruel', 3, 300, 2, 5);

INSERT INTO Avaliacao (nota, comentario, numero_paginas_lidas, data_inicio_leitura, data_termino_leitura, livro, cliente) 
VALUES (4, 'Bom', 225, '2023-01-21', '2023-01-21', 1, 4);
INSERT INTO Avaliacao (nota, numero_paginas_lidas, data_inicio_leitura, data_termino_leitura, livro, cliente) 
VALUES (5, 300, '2023-01-18', '2023-01-21', 4, 3);
INSERT INTO Avaliacao (nota, comentario, numero_paginas_lidas, livro, cliente) 
VALUES (5, 'Muito bom, chorei no meio', 225, 2, 2);

INSERT INTO Avaliacao (nota,comentario,numero_paginas_lidas,data_inicio_leitura,data_termino_leitura,livro,cliente)
VALUES (?,?,?,?,?,?,?);

INSERT INTO Avaliacao (nota, comentario, numero_paginas_lidas, livro, cliente) 
VALUES (5, 'Muito bom, chorei no meio', 300, 2, 2);

INSERT INTO Livro (titulo, genero, numero_paginas_livro, tag, cliente) VALUES ('Sua Alteza Real', 3, 325, 1, 6);