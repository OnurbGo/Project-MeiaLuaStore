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
        <label for="login">Nome de login:</label>
        <input type="text" id="login" name="login" required>
        
        <label for="senha">Senha:</label>
        <input type="text" id="senha" name="senha" required>

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" required>

        <button type="submit">Enviar Produto</button>
    </form>

    <?php
    // Verifica se o formulário foi enviado
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // URL do backend Java para criação de produto
        $url = 'http://localhost:8080/api/usuarios';

        // Obtém os dados do produto do formulário
        $login = $_POST['login'];
        $senha = $_POST['senha'];
        $nome = $_POST['nome'];
        $cpf = $_POST['cpf'];

        // Cria o array com os dados do produto
        $dadosProduto = [
            'login' => $login,
            'senha' => $senha,
            'nome' => $nome,
            'cpf' => $cpf
        ];

        // Converte os dados para JSON
        $dadosJson = json_encode($dadosProduto);

        // Inicia a sessão cURL
        $ch = curl_init();

        // Configurações da requisição POST
        curl_setopt($ch, CURLOPT_URL, $url); // URL da requisição
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
