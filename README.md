# Trabalho 2 - Sistema de Organização de Biblioteca 📚

**Trabalho Prático de Programação Orientada a Objetos (POO)**  
Sistema com Interface Gráfica, Acesso Restrito e Gerenciamento de Categorias.

---

## 🎯 Objetivo Geral

Desenvolver um sistema com **interface gráfica em Java (Swing)** com acesso a banco de dados (PostgreSQL), controle de login e permissões, além do **gerenciamento de categorias de livros**.

O sistema possui dois tipos de usuários:
- **Administrador**: acesso total ao sistema (CRUD de categorias e usuários)
- **Comum**: acesso limitado à visualização das categorias

---

## 📚 Tema Escolhido: Sistema de Organização de Biblioteca

Sistema que gerencia **categorias de livros**, simulando uma estrutura de biblioteca digital.

- **Entidade principal**: Categoria de Livro
- **Campo**: nome da categoria (ex: Ficção, Biografia, Ciência)

---

## 🔐 Controle de Acesso

O sistema possui autenticação por login e senha, com dois níveis de acesso:

- **Usuário Comum**
  - Apenas visualiza a lista de categorias
- **Administrador**
  - Pode adicionar, editar e excluir categorias
  - Pode gerenciar usuários do sistema

O botão **“Criar novo usuário”** permite o cadastro de novos usuários (comuns ou admins).

---

## 🧩 Funcionalidades Implementadas

### 🔑 Tela de Login
- Entrada de login e senha
- Validação de credenciais
- Redirecionamento conforme tipo de usuário
- Cadastro de novo usuário

### 🏠 Tela Inicial
- Admin: acesso a gerenciamento de categorias e usuários
- Comum: acesso apenas à visualização das categorias

### 🗂️ Tela de Categorias
- **Administrador**
  - Cadastrar nova categoria
  - Listar categorias em uma tabela
  - Editar e excluir registros
- **Comum**
  - Apenas visualizar a tabela de categorias

### 👤 Tela de Gerenciamento de Usuários (Admin)
- Listar todos os usuários
- Adicionar novo usuário
- Editar dados (login, senha, tipo)
- Excluir usuários

---

## 🧪 Tecnologias Utilizadas

- **Java (Swing)** para interface gráfica
- **JDBC** para integração com banco de dados
- **PostgreSQL** como sistema gerenciador de banco de dados

---

## 🗓️ Organização das Aulas

- **1ª Aula**: Início do desenvolvimento
- **2ª Aula**: Continuação e ajustes
- **3ª Aula**: Apresentação final ao professor

---

## 📣 Apresentação

Durante a apresentação será demonstrado:
- Funcionamento do sistema (login, permissões, CRUD)
- Explicação clara do código
- Justificativa do tema escolhido e sua adaptação

---

> Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos** com foco em práticas de interface gráfica, banco de dados e controle de permissões.

