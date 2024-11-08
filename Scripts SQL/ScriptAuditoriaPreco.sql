DELIMITER $$

CREATE  or replace TRIGGER aditoria_preco
after UPDATE ON produto
FOR EACH ROW
BEGIN
    /* verificar se preço do produto já alterado, se o produto for alterado
     o preço novo vira o antigo e a função pode ser ativada novamente */
    IF OLD.preco <> NEW.preco THEN
        INSERT INTO aditoria_preco_item (id_produto, preco_antigo, preco_novo, data_alteracao)
        VALUES (OLD.id_produto, OLD.preco, NEW.preco, NOW());
    END IF;
END $$

DELIMITER ;