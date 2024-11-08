DELIMITER $$

CREATE OR REPLACE PROCEDURE calcular_valor_total_pedido(IN p_id_pedido INT)
BEGIN
    DECLARE total DECIMAL(10,2) DEFAULT 0;
    
    SELECT SUM(p.preco * pd.quantidade) INTO total
    FROM produto p
    JOIN produto_pedido pp ON p.id_produto = pp.id_produto
    JOIN pedido pd ON pp.id_pedido = pd.id_pedido
    WHERE pp.id_pedido = p_id_pedido;
    
    SELECT total AS valor_total_pedido;
END $$

DELIMITER ;
