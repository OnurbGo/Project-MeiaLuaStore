<?php
$id = isset($_GET['id']) ? $_GET['id'] : null;

if ($id) {
    $link = 'http://localhost:8080/api/pedidos/' . $id;

    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, $link); 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); 
    curl_setopt($ch, CURLOPT_TIMEOUT, 30);

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro cURL: ' . curl_error($ch);
    } else {
        echo 'Resposta do backend Java: ' . $response;
    }

    curl_close($ch);
} else {
    echo 'ID não especificado.';
}
?>
