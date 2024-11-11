<?php
$id = isset($_GET['id']) ? $_GET['id'] : null;

if ($id) {
    // URL do backend Java com o ID específico
    $link = 'http://localhost:8080/forma-pagamento/' . $id;

    // Inicia a sessão cURL
    $ch = curl_init();

    // Configurações da requisição GET
    curl_setopt($ch, CURLOPT_URL, $link); 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);// Retorna a resposta como string

    // Executa a requisição e pega a resposta
    $response = curl_exec($ch);

    // Verifica se ocorreu algum erro
    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
    } else {
        // Exibe a resposta do backend Java
        echo 'Resposta do backend Java: ' . $response;
    }

    // Fecha a sessão cURL
    curl_close($ch);
} else {
    echo 'ID não especificado.';
}
?>
