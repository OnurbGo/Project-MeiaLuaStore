<?php
// URL do backend Java
$link = 'http://localhost:8080/api/usuarios';

// Inicia a sessão cURL
$ch = curl_init();

// Configurações da requisição GET
curl_setopt($ch, CURLOPT_URL, $link); // URL da requisição
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // Retorna a resposta como string
curl_setopt($ch, CURLOPT_TIMEOUT, 30); // Timeout de 30 segundos (opcional)

// Executa a requisição e pega a resposta
$response = curl_exec($ch);

// Verifica se ocorreu algum erro
if(curl_errno($ch)) {
    echo 'Erro cURL: ' . curl_error($ch);
} else {
    // Exibe a resposta do backend Java
    echo 'Resposta do backend Java: ' . $response;
}

// Fecha a sessão cURL
curl_close($ch);
?>
