drop database meialuastore
create database meialuastore
use meialuastore

show tables

/*----------------------------------------------------------------------*/

create table usuarios(
id_usuario int not null primary key, 
login varchar(50) unique not null,
senha varchar(255) not null,
nome varchar(100),
cpf varchar(14)
)

create table pedido(
id_pedido INT NOT NULL PRIMARY KEY,
usuario_id INT,
data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
status varchar (20),
FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario)
)

create table pagamento(
id_pagamento int not null primary key,
metodo_pagamento varchar(50),
id_pedido INT, 
data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
valor_pago DECIMAL(10,2),
FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
)

create table forma_pagamento(
id_forma_pagamento int not null primary key,
metodo_forma varchar(50)
)

create table produto(
id_produto int not null primary key,
nome_produto varchar(100),
descricao_produto varchar(500),
preco int,
categoria varchar (100)
)

create table produto_pedido(
id_produto_pedido int not null primary key,
id_pedido int,
id_produto int,
quantidade int,
FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
)

create table imagens(
id_imagens int not null primary key,
url longtext,
id_produto INT,
FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
)

create table aditoria_preco_item(
id_auditoria_preco_item INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_produto INT,                   
preco_antigo DECIMAL(10,2),        
preco_novo DECIMAL(10,2),          
data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
)







