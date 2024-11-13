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
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $link = 'http://localhost:8080/api/pedidos';

        $usuarioId = $_POST['usuarioId'];
        $dataPedido = $_POST['dataPedido'];
        $status = $_POST['status'];
        $quantidade = $_POST['quantidade'];

        $dadosProduto = [
            'usuarioId' => $usuarioId,
            'dataPedido' => $dataPedido,
            'status' => $status,
            'quantidade' => $quantidade
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
