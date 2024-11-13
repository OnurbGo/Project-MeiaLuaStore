DELIMITER $$

create or replace PROCEDURE buscar_por_categoria(IN categoriaFiltro VARCHAR(100))
BEGIN
    SELECT 
        p.id_produto, 
        p.nome_produto, 
        i.url AS url_imagem
    FROM produto p
    LEFT JOIN imagens i ON p.id_produto = i.id_produto
    WHERE p.categoria = categoriaFiltro;
END $$

DELIMITER ;



call buscar_por_categoria ('Luta')