# Pechincha 🛒

O **Pechincha** é um aplicativo Android que permite aos usuários cadastrar, buscar e filtrar ofertas de produtos. Com ele, você pode gerenciar ofertas, aplicar cupons e encontrar os melhores preços para seus produtos favoritos.

---

## 🚀 Funcionalidades

- **Cadastro de Ofertas**: Cadastre ofertas com título, preço e cupom.
- **Busca de Ofertas**: Busque ofertas por título ou faixa de preço.
- **Filtragem de Ofertas**: Filtre ofertas por preço mínimo e máximo.
- **Listagem de Ofertas**: Visualize todas as ofertas cadastradas em uma lista.
- **Edição e Exclusão de Ofertas**: Edite ou exclua ofertas existentes.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Banco de Dados**: SQLite
- **Bibliotecas**:
  - `RecyclerView`: Para exibir a lista de ofertas.
  - `SQLiteOpenHelper`: Para gerenciar o banco de dados local.
  - `Toast`: Para exibir mensagens de feedback ao usuário.
  - `AlertDialog`: Para confirmação de exclusão de ofertas.

---

## 📁 Estrutura do Projeto

- **`/src/main/java/com/example/pechincha`**:
  - **`model/`**: Contém as classes de modelo, como `Oferta` e `Usuario`.
  - **`repository/`**: Contém as classes de acesso ao banco de dados, como `OfertaDAO` e `BDpechincha`.
  - **`recycler/`**: Contém o adaptador para a lista de ofertas (`OfertaAdapter`).
  - **`activity/`**: Contém as atividades principais, como `BuscarOferta` e `CadastrarOferta`.

- **`/src/main/res`**:
  - **`layout/`**: Contém os arquivos XML de layout das atividades.
  - **`drawable/`**: Contém recursos visuais, como ícones e imagens.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Android Studio (última versão estável).
- Dispositivo Android ou emulador configurado.
