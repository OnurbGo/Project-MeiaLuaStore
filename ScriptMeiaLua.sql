create table usuarios(
id_usuario int not null primary key, 
login varchar() unique not null,
senha varchar() not null,
nome varchar(100),
cpf varchar()
)

create table pedido(
id_pedido int not null primary key,
produtos int[],
valor int[]
)

create table pagamento(
id_pagamento int not null primary key,
metodo_pagamento varchar()
)

create table forma_pagamento(
id_forma_pagamento int not null primary key,
metodo_forma varchar()
)

create table produto(
id_produto int not null primary key,
nome_produto varchar(),
descricao_produto varchar(),
preco int,
)

create table produto_pedido(
id_produto_pedido int not null primary key,
id_pedido int,
id_produto int,
FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
)

create table imagens(
id_imagens int not null primary key,
url longtext,
)

create table aditoria_preco_item(
id_auditoria_preco_item int not null primary key,
)