DELIMITER $$

CREATE OR REPLACE PROCEDURE buscar_por_categoria(IN categoriaFiltro VARCHAR(100))
BEGIN
    IF categoriaFiltro = 'Todos' THEN
        SELECT 
            p.id_produto, 
            p.nome_produto, 
            i.url AS url_imagem
        FROM produto p
        LEFT JOIN imagens i ON p.id_produto = i.id_produto;
    ELSE

        SELECT 
            p.id_produto, 
            p.nome_produto, 
            i.url AS url_imagem
        FROM produto p
        LEFT JOIN imagens i ON p.id_produto = i.id_produto
        WHERE p.categoria = categoriaFiltro;
    END IF;
END $$

DELIMITER ;

call buscar_por_categoria ('Luta')
call buscar_por_categoria ('Todos')