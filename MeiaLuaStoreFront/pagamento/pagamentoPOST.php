<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Pagamento</title>
</head>
<body>

    <h1>Cadastro de Pagamento</h1>
    <form action="" method="POST">
        <label for="metodoPagamento">Metodo Pagamento:</label>
        <input type="text" id="metodoPagamento" name="metodoPagamento" required>
        
        <label for="pedidoId">ID do pedido:</label>
        <input type="number" id="pedidoId" name="pedidoId" required>

        <label for="formaPagamentoId">ID da Forma de Pagamento:</label>
        <input type="number" id="formaPagamentoId" name="formaPagamentoId" required>

        <label for="dataPagamento">Data Pagamento:</label>
        <input type="date" id="dataPagamento" name="dataPagamento" required>

        <label for="valorPago">Valor pago:</label>
        <input type="number" id="valorPago" name="valorPago" required>


        <button type="submit">Enviar Pagamento</button>
    </form>

    <?php
// Verifica se o formulário foi enviado
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // URL do backend Java para criação de pagamento
    $link = 'http://localhost:8080/pagamento';

    // Obtém os dados do formulário
    $metodoPagamento = $_POST['metodoPagamento'];
    $pedidoId = intval($_POST['pedidoId']); // Garante que é um inteiro
    $formaPagamentoId = intval($_POST['formaPagamentoId']); // Garante que é um inteiro
    $dataPagamento = $_POST['dataPagamento'] . 'T00:00:00'; // Adiciona hora, se necessário
    $valorPago = floatval($_POST['valorPago']); // Garante que é float

    // Cria o array com os dados
    $dadosProduto = [
        'metodoPagamento' => $metodoPagamento,
        'pedidoId' => $pedidoId,
        'formaPagamentoId' => $formaPagamentoId,
        'dataPagamento' => $dataPagamento,
        'valorPago' => $valorPago
    ];

    // Converte os dados para JSON
    $dadosJson = json_encode($dadosProduto);

    // Inicia a sessão cURL
    $ch = curl_init();

    // Configurações da requisição POST
    curl_setopt($ch, CURLOPT_URL, $link);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_TIMEOUT, 30);

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
            echo '<p>Pagamento criado com sucesso: ' . $response . '</p>';
        } else {
            echo '<p>Erro ao criar pagamento. Código HTTP: ' . $httpCode . '. Resposta: ' . $response . '</p>';
        }
    }

    // Fecha a sessão cURL
    curl_close($ch);
}
?>


</body>
</html>
