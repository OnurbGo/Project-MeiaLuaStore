INSERT INTO produto (id_produto, nome_produto, descricao_produto, preco, categoria) 
VALUES
(1, 'Street Fighter 6', 'Street Fighter 6 é o mais recente título da famosa série de jogos de luta da Capcom, com gráficos melhorados, novos personagens e modos de jogo inovadores.', 250, 'Luta'),
(2, 'Tekken 8', 'Tekken 8 traz novas mecânicas e uma inovação técnica a esta franquia tão antiga e amada', 250, 'Luta');


INSERT INTO pedido (id_pedido) 
VALUES (1);

INSERT INTO produto_pedido (id_produto_pedido,quantidade,id_produto,id_pedido) 
VALUES (1,2,1,1);

INSERT INTO usuarios (id_usuario, login, senha, nome, cpf)
VALUES (1, 'Bruno', '1234', 'Bruno', '078.558.909-04');

select * from produto_pedido

select * from produto

select * from usuarios