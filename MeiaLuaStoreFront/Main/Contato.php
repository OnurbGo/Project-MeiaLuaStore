<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Meia-Lua Store</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200..700&display=swap" rel="stylesheet" />

  <link rel="stylesheet" href="css/style.css" />
  <link rel="shortcut icon" href="Imagens/Icone.png" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
  <div class="centro">
    <h1>Nos Contate Aqui:</h1>
    <br />

    <form name="formContato" id="form"  method="post" action="index.php?pg=agradecimento">
      <label for="nome">Digite seu nome completo:</label>
      <input type="text" name="nome" id="nome" />
      <br/>
      <label for="email">Digite seu e-mail:</label>
      <input type="email" name="email" id="email" />
      <br/>
      <label for="mensagem">Digite sua mensagem:</label>
      <textarea name="mensagem" id="mensagem" cols="30" rows="10"></textarea>
      <br/>
      
      <button class="botao" type="button" id="botao" onclick="LevaLink()">Enviar Mensagem</button>

      <script>
      function LevaLink() {
    

        //Validar dados
        var nome = document.getElementById("nome").value.trim();
        var email = document.getElementById("email").value.trim();
        var mensagem = document.getElementById("mensagem").value.trim();

        if (nome === ""){
          alert ("Por Favor Digite Seu Nome.");
          return False;
        }
        if (email === ""){
          alert ("Por Favor Digite Seu Email.");
          return False;
        }
        if (mensagem === ""){
          alert ("Por Favor Digite Sua Mensagem.");
          return False;
        }
      
        document.getElementById("form").submit();
      }
      </script>

    </form>
  </div>

  <br><br><br>
</body>

</html>