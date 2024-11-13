<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atualizar Produto</title>
</head>
<body>
    <h1>Atualizar Produto</h1>
    <form action="" method="POST">
        <label for="id_produto">ID do Produto:</label>
        <input type="number" id="id_produto" name="id_produto" required>

        <label for="novoPreco">Novo Preço:</label>
        <input type="number" id="novoPreco" name="novoPreco" step="0.01" required>

        <button type="submit">Atualizar Produto</button>
    </form>

    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $link = 'http://localhost:8080/api/produtos';

        $id_produto = $_POST['id_produto'];
        $novoPreco = $_POST['novoPreco'];

        $dadosProduto = [
            'id_produto' => $id_produto,     
            'novoPreco' => (float)$novoPreco    
        ];

        $dadosJson = json_encode($dadosProduto);

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, $link . '/' . $id_produto . '/preco?novoPreco='. $novoPreco);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

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
                echo 'Produto atualizado com sucesso: ' . $response;
            } else {
                echo 'Erro ao atualizar o produto. Código HTTP: ' . $httpCode . '. Resposta: ' . $response;
            }
        }

        curl_close($ch);
    }
    ?>
</body>
</html>
