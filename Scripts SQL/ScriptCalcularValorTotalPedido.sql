DELIMITER $$

create or replace PROCEDURE calcular_valor_total_pedido(IN p_id_pedido INT)
BEGIN
    DECLARE total DECIMAL(10,2) DEFAULT 0;
    
    -- Total = Quantidade * Preço
    SELECT SUM(p.preco * pp.quantidade)  INTO total
    FROM produto p
    JOIN produto_pedido pp ON p.id_produto = pp.id_produto
    WHERE pp.id_pedido = p_id_pedido;
    
    -- Exibe o valor total
    SELECT total AS valor_total_pedido;
END $$

DELIMITER ;
