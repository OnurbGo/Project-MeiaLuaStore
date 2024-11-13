<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Produto</title>
</head>
<body>

    <h1>Cadastro de Produto</h1>
    <form action="" method="POST">
        <label for="nome">Nome do Produto:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="descricao">Descrição do Produto:</label>
        <input type="text" id="descricao" name="descricao" required>
        
        <label for="preco">Preço:</label>
        <input type="number" id="preco" name="preco" step="0.01" required>

        <label for="categoria">Categoria do Produto:</label>
        <input type="text" id="categoria" name="categoria" required>

        <button type="submit">Enviar Produto</button>
    </form>

    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $link = 'http://localhost:8080/api/produtos';

        $nome = $_POST['nome'];
        $descricao = $_POST['descricao'];
        $preco = $_POST['preco'];
        $categoria = $_POST['categoria'];

        $dadosProduto = [
            'nome' => $nome,
            'desc' => $descricao,
            'preco' => (float)$preco,
            'categoria' => $categoria
        ];

        $dadosJson = json_encode($dadosProduto);

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, $link);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_TIMEOUT, 30); 

        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($dadosJson)
        ]);

        curl_setopt($ch, CURLOPT_POSTFIELDS, $dadosJson);

        $response = curl_exec($ch);

        if (curl_errno($ch)) {
            echo 'Erro cURL: ' . curl_error($ch);
        } else {
            $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
            
            if ($httpCode >= 200 && $httpCode < 300) {
                echo '<p>Produto criado com sucesso: ' . $response . '</p>';
            } else {
                echo '<p>Erro ao criar produto. Código HTTP: ' . $httpCode . '. Resposta: ' . $response . '</p>';
            }
        }
        curl_close($ch);
    }
    ?>

</body>
</html>
