DELIMITER $$

CREATE OR REPLACE PROCEDURE produtos_mais_vendidos_por_categoria(IN categoriaFiltro VARCHAR(100))
BEGIN
    IF categoriaFiltro = 'Todos' THEN
        -- Seleciona os 4 produtos mais vendidos de todas as categorias
        SELECT 
            p.id_produto, 
            p.nome_produto, 
            i.url AS url_imagem
        FROM produto p
        LEFT JOIN imagens i ON p.id_produto = i.id_produto
        LEFT JOIN produto_pedido pp ON p.id_produto = pp.id_produto
        GROUP BY p.id_produto
        ORDER BY COUNT(pp.id_produto) DESC
        LIMIT 4;
    ELSE
        -- Seleciona os 4 produtos mais vendidos de uma categoria específica
        SELECT 
            p.id_produto, 
            p.nome_produto, 
            i.url AS url_imagem
        FROM produto p
        LEFT JOIN imagens i ON p.id_produto = i.id_produto
        LEFT JOIN produto_pedido pp ON p.id_produto = pp.id_produto
        WHERE p.categoria = categoriaFiltro
        GROUP BY p.id_produto
        ORDER BY COUNT(pp.id_produto) DESC
        LIMIT 4;
    END IF;
END $$

DELIMITER ;


call produtos_mais_vendidos_por_categoria('Luta');
call produtos_mais_vendidos_por_categoria('todos');

