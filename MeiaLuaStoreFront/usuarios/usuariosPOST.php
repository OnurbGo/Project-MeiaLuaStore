<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuario</title>
</head>
<body>

    <h1>Cadastro de Usuario</h1>
    <form action="" method="POST">
        <label for="login">Nome de login:</label>
        <input type="text" id="login" name="login" required>
        
        <label for="senha">Senha:</label>
        <input type="text" id="senha" name="senha" required>

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" required>

        <button type="submit">Enviar Usuario</button>
    </form>

    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $link = 'http://localhost:8080/api/usuarios';

        $login = $_POST['login'];
        $senha = $_POST['senha'];
        $nome = $_POST['nome'];
        $cpf = $_POST['cpf'];

        $dadosProduto = [
            'login' => $login,
            'senha' => $senha,
            'nome' => $nome,
            'cpf' => $cpf
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
