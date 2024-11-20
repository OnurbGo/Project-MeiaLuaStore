<?php
function buscarProduto($id_produto) {
    $url = "http://localhost:8080/api/produtos/$id_produto";
    $ch = curl_init($url);

    // Configuração da requisição cURL
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPGET, true);

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
        curl_close($ch);
        return null;
    }

    curl_close($ch);

    // Converter resposta JSON para array PHP
    return json_decode($response, true);
}

// Função para buscar as imagens do produto
function buscarImagens($id_produto) {
    $url = "http://localhost:8080/imagens/produto/$id_produto";
    $ch = curl_init($url);

    // Configuração da requisição cURL
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPGET, true);

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
        curl_close($ch);
        return null;
    }

    curl_close($ch);

    // Converter resposta JSON para array PHP
    return json_decode($response, true);
}

if (isset($_GET['id'])) {
    $produtoId = $_GET['id'];

    // Buscar dados do produto
    $produto = buscarProduto($produtoId);
    if (!$produto) {
        echo "Produto não encontrado.";
        exit;
    }

    $nome_produto = htmlspecialchars($produto['nome_produto']);
    $descricao_produto = nl2br(htmlspecialchars($produto['descricao_produto']));
    $categoria = htmlspecialchars($produto['categoria']);
    $preco = number_format($produto['preco'], 2, ',', '.');

    // Buscar imagens do produto
    $imagens = buscarImagens($produtoId);
    if ($imagens && count($imagens) > 0) {
        $url_imagem = $imagens[0]['url']; 
    } else {
        $url_imagem = 'default.jpg'; 
    }
} else {
    echo "Produto não encontrado.";
    exit;
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
