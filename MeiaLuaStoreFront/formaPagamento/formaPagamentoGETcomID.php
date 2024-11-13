<?php
// realiza uma conexão Http usando o id
$id = isset($_GET['id']) ? $_GET['id'] : null;// o isset verifica se uma variável foi definida e não é nula

if ($id) {// verifica se o id existe
    
    $link = 'http://localhost:8080/forma-pagamento/' . $id;// esse é o url que ele vai se conectar

    $ch = curl_init();//aqui ele inicia o cRUL, o cRUL é uma ferramenta para fazer requisições de rede (HTTP), permitindo interagir com servidores e APIs.

    // Configurações da requisição GET
    curl_setopt($ch, CURLOPT_URL, $link); //Define a URL para a qual a requisição será enviada.
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);// Faz com que a resposta da requisição seja retornada como string, e não exibida diretamente.

    $response = curl_exec($ch);//A requisição é executada e o conteúdo da URL (Resposta) é armazenada na variável $response.

    if (curl_errno($ch)) {//se um erro ocorrer ele retorna o erro
        echo 'Erro cURL: ' . curl_error($ch);
    } else {
        // Exibe a resposta do backend Java
        echo 'Resposta do backend Java: ' . $response;// senão ele retorna a $response (Resposta)
    }

    
    curl_close($ch);//Fecha cURL após a execução. (mesmo que o PHP feche automaticamente o cURL ao final do script 
    //é uma boa prática fechar para liberar recursos imediatamente e evitar problemas).
} else {
    echo 'ID não especificado.'; // se o id não existir ele retorna a mensagem de erro
}
?>