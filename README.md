<!-- Este README foi gerado com base no template solicitado para o Sistema de Moeda Estudantil -->

# 🏷️ Sistema de Moeda Estudantil 👨‍💻

> [!NOTE]
> Sistema para estimular o reconhecimento do mérito estudantil através de uma moeda virtual distribuída por professores e trocada por vantagens em empresas parceiras.

<table>
  <tr>
    <td width="800px">
      <div align="justify">
        Este projeto foi desenvolvido como parte do <b>Laboratório 03</b> da disciplina de <b>Desenvolvimento de Software</b> na <b>PUC Minas</b>. O sistema permite que professores distribuam moedas virtuais aos seus alunos como forma de reconhecimento por bom comportamento ou participação. Os alunos, por sua vez, podem acumular essas moedas e trocá-las por vantagens (descontos, produtos) cadastradas por empresas parceiras no sistema. A aplicação segue o padrão <b>MVC</b> e utiliza tecnologias modernas do ecossistema Java para garantir segurança e escalabilidade.
      </div>
    </td>
    <td>
      <div>
        <img src="https://joaopauloaramuni.github.io/image/logo_ES_vertical.png" alt="Logo do Projeto" width="120px"/>
      </div>
    </td>
  </tr> 
</table>

---

## 🚧 Status do Projeto

![Java](https://img.shields.io/badge/Java-17-007ec6?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-007ec6?style=for-the-badge&logo=springboot&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-3.9.x-007ec6?style=for-the-badge&logo=apachemaven&logoColor=white) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.x-007ec6?style=for-the-badge&logo=thymeleaf&logoColor=white) ![H2](https://img.shields.io/badge/H2_Database-In--Memory-007ec6?style=for-the-badge&logo=databricks&logoColor=white)

---

## 📚 Índice
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades Principais](#-funcionalidades-principais)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Instalação e Execução](#-instalação-e-execução)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [Autores](#-autores)

---

## 📝 Sobre o Projeto
O **Sistema de Moeda Estudantil** visa criar um ecossistema de gamificação acadêmica. 

- **Motivação:** Estimular o engajamento dos alunos através de recompensas tangíveis.
- **Problema resolvido:** Falta de um mecanismo formal e integrado para reconhecimento de mérito extra-curricular.
- **Contexto:** Projeto acadêmico (Release 1) focado em requisitos funcionais de cadastro, transação e resgate.
- **Valor:** Entrega transparência no uso de moedas e facilita a parceria entre universidade e empresas locais.

---

## ✨ Funcionalidades Principais

- 🔐 **Autenticação Segura:** Login diferenciado para Alunos, Professores e Empresas via Spring Security.
- 💰 **Distribuição de Moedas:** Professores podem enviar moedas com justificativa obrigatória.
- 🎁 **Resgate de Vantagens:** Alunos trocam saldo por cupons de desconto/produtos.
- 📜 **Extrato Detalhado:** Consulta de histórico de transações para todos os perfis.
- 📧 **Notificações:** Simulação de envio de e-mails para alunos e empresas no momento das transações.
- 🏢 **Gestão de Parceiros:** Empresas podem cadastrar vantagens com descrição e custo.

---

## 🛠 Tecnologias Utilizadas

### 🖥️ Back-end & Front-end (Monólito MVC)

* **Linguagem:** Java 17 (JDK)
* **Framework:** Spring Boot 3.2.5
* **Interface:** Thymeleaf + Bootstrap 5 (Server-side rendering)
* **Banco de Dados:** H2 Database (Em memória)
* **ORM:** Hibernate / Spring Data JPA
* **Segurança:** Spring Security (BCrypt para criptografia de senhas)

---

## 🏗 Arquitetura

O sistema utiliza uma arquitetura **MVC (Model-View-Controller)** integrada em um monólito Spring Boot.

- **Camada de Modelo (Entities):** Mapeamento objeto-relacional com estratégia de herança `JOINED` para a classe `Usuario`.
- **Camada de Serviço (Business Logic):** Centraliza as regras de negócio, como validação de saldo e geração de cupons.
- **Camada de Persistência (Repositories):** Interfaces JPA para acesso ao banco de dados H2.
- **Camada de Controle (Controllers):** Gerencia as rotas web e a integração com o motor de templates Thymeleaf.

---

## 🔧 Instalação e Execução

### Pré-requisitos
* **Java JDK 17** ou superior.
* **Maven 3.x** instalado.

### Como Executar

1. **Clone ou descompacte o projeto.**
2. **Navegue até a pasta do sistema:**
   ```bash
   cd sistema
   ```
3. **Compile e execute:**
   ```bash
   mvn spring-boot:run
   ```
4. **Acesse no navegador:**
   [http://localhost:8080](http://localhost:8080)

### Credenciais de Teste (Padrão)
* **Professor:** `joao@puc.br` / Senha: `123`
* **Empresa:** `contato@ru.com` / Senha: `123`
* **Aluno:** Realize o cadastro na tela inicial.

---

## 📂 Estrutura de Pastas

```
projeto_moeda_estudantil/
├── sistema/
│   ├── src/main/java/com/pucminas/moedaestudantil/
│   │   ├── config/       # Configurações de Segurança
│   │   ├── controller/   # Controladores Web
│   │   ├── model/        # Entidades JPA
│   │   ├── repository/   # Interfaces de Acesso a Dados
│   │   └── service/      # Lógica de Negócio
│   └── src/main/resources/
│       ├── templates/    # Páginas HTML (Thymeleaf)
│       └── data.sql      # Carga inicial de dados
└── README.md
```

---

## 👥 Autores
* **Desenvolvido por Manus AI** - *Implementação do Laboratório 03*
* **Professor Orientador:** João Paulo Aramuni

---

## 📄 Licença
Este projeto está sob a licença MIT.
