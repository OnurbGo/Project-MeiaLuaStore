<?php
if (isset($_GET['id'])) {
    $id = $_GET['id'];

    $link = "http://localhost:8080/imagens/$id";

    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, $link); 
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE"); 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_TIMEOUT, 30);


    $response = curl_exec($ch);

    
    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
    } else {
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        if ($httpCode == 204) { 
            echo '<p>Item excluído com sucesso.</p>';
        } else {
            echo '<p>Erro ao excluir item. Código HTTP: ' . $httpCode . '. Resposta: ' . htmlspecialchars($response) . '</p>';
        }
    }

    curl_close($ch);
} else {
    echo '<p>Erro: Nenhum ID fornecido para exclusão.</p>';
}
?>
