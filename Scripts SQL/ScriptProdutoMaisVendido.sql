DELIMITER $$

CREATE PROCEDURE produtos_mais_vendidos_por_categoria(IN categoria_param VARCHAR(255))
BEGIN
    SELECT 
        p.id_produto,
        p.nome_produto,
        SUM(pd.quantidade) AS quantidade_vendida,
        i.url AS url_imagem
    FROM 
        produto p
    INNER JOIN 
        produto_pedido pp ON p.id_produto = pp.id_produto
    INNER JOIN 
        pedido pd ON pp.id_pedido = pd.id_pedido
    LEFT JOIN 
        imagens i ON p.id_produto = i.id_produto
    WHERE
        p.categoria = categoria_param
    GROUP BY 
        p.id_produto
    ORDER BY 
        quantidade_vendida DESC
    LIMIT 4;
END $$

DELIMITER ;

call produtos_mais_vendidos_por_categoria('Luta')