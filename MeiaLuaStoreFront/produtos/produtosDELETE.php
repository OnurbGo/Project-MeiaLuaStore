<?php
// Verifica se o ID foi fornecido
if (isset($_GET['id'])) {
    $id = $_GET['id'];  // Pega o ID a partir do parâmetro GET na URL

    // URL do backend Java, incluindo o ID para exclusão
    $link = "http://localhost:8080/api/produtos/$id";

    // Inicia a sessão cURL
    $ch = curl_init();

    // Configurações da requisição DELETE
    curl_setopt($ch, CURLOPT_URL, $link); // URL da requisição com o ID do item a ser excluído
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE"); // Define o método como DELETE
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // Retorna a resposta como string
    curl_setopt($ch, CURLOPT_TIMEOUT, 30); // Timeout de 30 segundos (opcional)

    // Executa a requisição e pega a resposta
    $response = curl_exec($ch);

    // Verifica se ocorreu algum erro
    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
    } else {
        // Exibe a resposta do backend Java
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        if ($httpCode == 204) { // Código 204 indica sucesso na exclusão
            echo '<p>Item excluído com sucesso.</p>';
        } else {
            echo '<p>Erro ao excluir item. Código HTTP: ' . $httpCode . '. Resposta: ' . htmlspecialchars($response) . '</p>';
        }
    }

    // Fecha a sessão cURL
    curl_close($ch);
} else {
    echo '<p>Erro: Nenhum ID fornecido para exclusão.</p>';
}
?>
