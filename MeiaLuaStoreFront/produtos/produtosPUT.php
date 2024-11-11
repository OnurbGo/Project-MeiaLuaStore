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
    // Verifica se o formulário foi enviado
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // URL do backend Java
        $link = 'http://localhost:8080/api/produtos';

        // Obtém o ID e o novo preço do produto do formulário
        $id_produto = $_POST['id_produto'];
        $novoPreco = $_POST['novoPreco'];

        // Cria o array com os dados do produto
        $dadosProduto = [
            'id_produto' => $id_produto,           // ID do produto a ser atualizado
            'novoPreco' => (float)$novoPreco      // Novo preço
        ];

        // Converte os dados para JSON
        $dadosJson = json_encode($dadosProduto);

        // Inicia a sessão cURL
        $ch = curl_init();

        // Configurações da requisição PUT
        curl_setopt($ch, CURLOPT_URL, $link . '/' . $id_produto . '/preco?novoPreco='. $novoPreco); // URL com o ID do produto
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT'); // Define o método como PUT
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // Retorna a resposta como string

        // Define os cabeçalhos HTTP, incluindo o Content-Type para JSON
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($dadosJson)
        ]);

        // Define os dados a serem enviados no corpo da requisição
        curl_setopt($ch, CURLOPT_POSTFIELDS, $dadosJson);

        // Executa a requisição e pega a resposta
        $response = curl_exec($ch);

        // Verifica se ocorreu algum erro
        if (curl_errno($ch)) {
            echo 'Erro cURL: ' . curl_error($ch);
        } else {
            // Exibe a resposta do backend Java
            $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
            if ($httpCode >= 200 && $httpCode < 300) {
                echo 'Produto atualizado com sucesso: ' . $response;
            } else {
                echo 'Erro ao atualizar o produto. Código HTTP: ' . $httpCode . '. Resposta: ' . $response;
            }
        }

        // Fecha a sessão cURL
        curl_close($ch);
    }
    ?>
</body>
</html>
