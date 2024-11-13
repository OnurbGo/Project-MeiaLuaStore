<?php
$host = "localhost";
$dbname = "meialuastore";
$user = "root";
$password = "";

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $user, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Erro na conexão: " . $e->getMessage();
    exit;
}

$categoria = isset($_GET['categoria']) ? $_GET['categoria'] : 'Todos';

$produtos = [];

if ($categoria != 'Todos') {
    $query = $pdo->prepare("CALL buscar_por_categoria(:categoria)");
    $query->execute(['categoria' => $categoria]);
    $produtos = $query->fetchAll(PDO::FETCH_ASSOC);
} else {
    $query = $pdo->query("SELECT 
        p.id_produto, p.nome_produto, i.url AS url_imagem
        FROM produto p
        LEFT JOIN imagens i ON p.id_produto = i.id_produto");
    $produtos = $query->fetchAll(PDO::FETCH_ASSOC);
}

if (empty($produtos)) {
    $produtos = null;
}

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Meia-Lua Store</title>
  <link rel="stylesheet" href="css/style.css" />
  <link rel="shortcut icon" href="Imagens/Icone.png" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</head>
<body>

  <!-- Carrossel Dinâmico -->
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <?php 
        if (!empty($produtos)) {
            $first = true;
            foreach ($produtos as $produto) { 
                $active = $first ? 'active' : '';
                $first = false;

                $url_imagem = !empty($produto['url_imagem']) ? $produto['url_imagem'] : 'Imagens/placeholder.jpg';                ?>
                <div class="carousel-item <?= $active ?>">
                    <a href="index.php?pg=Pg_Jogo.php&id=<?= $produto['id_produto'] ?>">
                        <img src="<?= $url_imagem ?>" class="d-block w-100" alt="<?= $produto['nome_produto'] ?>">
                        <div class="carousel-caption d-none d-md-block">
                            <h5><?= htmlspecialchars($produto['nome_produto']) ?></h5>
                        </div>
                    </a>
                </div>
            <?php }
        } else {
            echo "<p></p>";
        }
        ?>
    </div>

    <!-- Controles do Carrossel -->
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Anterior</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Próximo</span>
    </button>
  </div>

  <!-- Filtro de Categoria -->
<div class="container mt-4">
    <div class="row">
      <div class="col-12">
        <h1>Jogos: <?= htmlspecialchars($categoria) ?></h1>
        <!-- Formulário de Filtro de Categoria -->
        <form action="index.php" method="get">
          <input type="hidden" name="pg" value="loja"> <!-- Adiciona o parâmetro 'pg=loja' -->
          <div class="form-group">
            <label for="categoria">Filtrar por Categoria:</label>
            <select class="form-select" id="categoria" name="categoria" onchange="this.form.submit()">
              <option value="Todos" <?= $categoria == 'Todos' ? 'selected' : '' ?>>Todos</option>
              <option value="Luta" <?= $categoria == 'Luta' ? 'selected' : '' ?>>Luta</option>
              <option value="Ação" <?= $categoria == 'Ação' ? 'selected' : '' ?>>Ação</option>
              <option value="Terror" <?= $categoria == 'Terror' ? 'selected' : '' ?>>Terror</option>
              <option value="Aventura" <?= $categoria == 'Aventura' ? 'selected' : '' ?>>Aventura</option>
              <option value="RPG" <?= $categoria == 'RPG' ? 'selected' : '' ?>>RPG</option>
              <option value="Estratégia" <?= $categoria == 'Estratégia' ? 'selected' : '' ?>>Estratégia</option>
            </select>
          </div>
        </form>
      </div>
    </div>


    <!-- Exibição dos Produtos nas "Caixinhas" com as suas classes -->
    <div class="flex mt-4">
        <?php 
        if ($produtos !== null) {
            foreach ($produtos as $produto) { 
                $url_imagem = !empty($produto['url_imagem']) ? $produto['url_imagem'] : 'Imagens/placeholder.jpg';
                ?>
                <div class="flex-coluna">
                    <a class="textoflex" href="index.php?pg=Pg_Jogo.php&id=<?= $produto['id_produto'] ?>">
                        <img src="<?= $url_imagem ?>" alt="<?= htmlspecialchars($produto['nome_produto']) ?>" title="<?= htmlspecialchars($produto['nome_produto']) ?>">
                        <p class="flexp"><?= htmlspecialchars($produto['nome_produto']) ?></p>
                    </a>
                </div>
            <?php }
        } else {
            echo "<p>Nenhum produto encontrado para essa categoria.</p>";
        }
        ?>
    </div>
  </div>

  <br>
  <br>
  <br>
  <br>

</body>
</html>
