<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Pedidos</title>
</head>
<body>

    <h1>Cadastro de Pedidos</h1>
    <form action="" method="POST">
        <label for="usuarioId">ID Usuario:</label>
        <input type="number" id="usuarioId" name="usuarioId" required>
        
        <label for="dataPedido">Data Pedido:</label>
        <input type="datetime-local" id="dataPedido" name="dataPedido" required>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" required>

        <label for="quantidade">Quantidade:</label>
        <input type="number" id="quantidade" name="quantidade" required>

        <button type="submit">Enviar Pagamento</button>
    </form>

    <?php
    // Verifica se o formulário foi enviado
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // URL do backend Java para criação de produto
        $link = 'http://localhost:8080/api/pedidos';

        // Obtém os dados do produto do formulário
        $usuarioId = $_POST['usuarioId'];
        $dataPedido = $_POST['dataPedido'];
        $status = $_POST['status'];
        $quantidade = $_POST['quantidade'];

        // Cria o array com os dados do produto
        $dadosProduto = [
            'usuarioId' => $usuarioId,
            'dataPedido' => $dataPedido,
            'status' => $status,
            'quantidade' => $quantidade
        ];

        // Converte os dados para JSON
        $dadosJson = json_encode($dadosProduto);

        // Inicia a sessão cURL
        $ch = curl_init();

        // Configurações da requisição POST
        curl_setopt($ch, CURLOPT_URL, $link); // URL da requisição
        curl_setopt($ch, CURLOPT_POST, true); // Define o método como POST
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // Retorna a resposta como string
        curl_setopt($ch, CURLOPT_TIMEOUT, 30); // Timeout de 30 segundos (opcional)

        // Define os cabeçalhos HTTP
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
            // Verifica o código de resposta HTTP
            $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
            
            if ($httpCode >= 200 && $httpCode < 300) {
                // Sucesso
                echo '<p>Produto criado com sucesso: ' . $response . '</p>';
            } else {
                // Erro no backend
                echo '<p>Erro ao criar produto. Código HTTP: ' . $httpCode . '. Resposta: ' . $response . '</p>';
            }
        }

        // Fecha a sessão cURL
        curl_close($ch);
    }
    ?>

</body>
</html>
