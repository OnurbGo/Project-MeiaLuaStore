<?php
if (isset($_GET['id'])) {
    $produtoId = $_GET['id'];

    // Conexão com o banco de dados
    $host = "localhost";
    $dbname = "meialuastore";
    $user = "root";          
    $password = "";            

    $conn = new mysqli($host, $user, $password, $dbname);

    if ($conn->connect_error) {
        die("Falha na conexão: " . $conn->connect_error);
    }

    $sql_produto = "SELECT * FROM produto WHERE id_produto = ?";
    $stmt = $conn->prepare($sql_produto);
    $stmt->bind_param("i", $produtoId);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $produto = $result->fetch_assoc();
        $nome_produto = htmlspecialchars($produto['nome_produto']);
        $descricao_produto = nl2br(htmlspecialchars($produto['descricao_produto']));
        $categoria = htmlspecialchars($produto['categoria']);
        $preco = number_format($produto['preco'], 2, ',', '.');

        $sql_imagem = "SELECT url FROM imagens WHERE id_produto = ?";
        $stmt_imagem = $conn->prepare($sql_imagem);
        $stmt_imagem->bind_param("i", $produtoId);
        $stmt_imagem->execute();
        $result_imagem = $stmt_imagem->get_result();

        if ($result_imagem->num_rows > 0) {
            $imagem = $result_imagem->fetch_assoc();
            $url_imagem = $imagem['url'];
        } else {
            $url_imagem = 'default.jpg';
        }
    } else {
        echo "Produto não encontrado.";
        exit;
    }

    $conn->close();
}
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Meia-Lua Store - <?= $nome_produto ?></title>
  <link rel="stylesheet" href="css/style.css" />
  <link rel="shortcut icon" href="imagens/icone.png" />
</head>
<body>

  <main>
    <h1 class="h1"><?= $nome_produto ?></h1>
    <div class="conteiner-capa">
      <img class="capa" src="<?= $url_imagem ?>" alt="<?= $nome_produto ?>" title="<?= $nome_produto ?>" />
      <p><?= $descricao_produto ?></p>
    </div>

    <div class="conteiner-centro">
      <ul class="textocentro">
        <li><strong>Categoria:</strong> <?= $categoria ?></li>
        <li><strong>Preço:</strong> R$ <?= $preco ?></li>
      </ul>
    </div>
  </main>

  <br>
  <br>
  <br>
  <br>
  
</body>
</html>
