DELIMITER $$

CREATE OR REPLACE PROCEDURE injecao_de_dados()
BEGIN
INSERT INTO produto (nome_produto, descricao_produto, preco, categoria) 
VALUES
('Street Fighter 6', 'Street Fighter 6 é o mais recente título da famosa série de jogos de luta da Capcom, com gráficos melhorados, novos personagens e modos de jogo inovadores.', 250, 'Luta'),
('Tekken 8', 'Tekken 8 traz novas mecânicas e uma inovação técnica a esta franquia tão antiga e amada', 250, 'Luta');

INSERT INTO usuarios (login, senha, nome, cpf)
VALUES ('Bruno', '1234', 'Bruno', '078.558.909-04');

INSERT INTO pedido (status, usuario_id, quantidade) 
VALUES ('APROVADO',1,2);

INSERT INTO pedido (status, usuario_id, quantidade) 
VALUES ('APROVADO',1,20);

INSERT INTO pedido (status, usuario_id, quantidade) 
VALUES ('APROVADO',6,30);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (6,7);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (3,4);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (2,3);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (1,1);

INSERT INTO forma_pagamento (metodo_forma) 
VALUES ('A vista'),('A prazo');

INSERT INTO pagamento (metodo_pagamento,data_pagamento,valor_pago)
values ('Pix',NOW(),250);

INSERT INTO pedido (status, usuario_id, quantidade) 
VALUES ('APROVADO',6,98);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (9,9);

INSERT INTO produto_pedido (id_produto, id_pedido) 
VALUES (7,8);

insert into imagens (url,id_produto) values ('https://assetsio.gnwcdn.com/eurogamer-zjp1vx.jpg?width=1200&height=630&fit=crop&enable=upscale&auto=webp',9);

insert into imagens (url,id_produto) values ('https://images6.alphacoders.com/124/thumb-1920-1243578.jpg',1);
END $$

DELIMITER ;
