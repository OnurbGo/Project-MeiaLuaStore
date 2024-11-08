INSERT INTO produto (nome_produto, descricao_produto, preco, categoria) 
VALUES
('Street Fighter 6', 'Street Fighter 6 é o mais recente título da famosa série de jogos de luta da Capcom, com gráficos melhorados, novos personagens e modos de jogo inovadores.', 250, 'Luta'),
('Tekken 8', 'Tekken 8 traz novas mecânicas e uma inovação técnica a esta franquia tão antiga e amada', 250, 'Luta');

INSERT INTO usuarios (login, senha, nome, cpf)
VALUES ('Bruno', '1234', 'Bruno', '078.558.909-04');

INSERT INTO pedido (status, usuario_id, quantidade) 
VALUES ('APROVADO',1,2);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (1,1);


select * from produto_pedido

select * from produto

select * from usuarios

select * from pedido for update;

DESCRIBE produto_pedido;

select * from forma_pagamento
DELETE FROM forma_pagamento WHERE id_forma_pagamento = 1;

select * from aditoria_preco_item

select * from pagamento ;