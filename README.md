# Lógica da Classe App

Este documento descreve exclusivamente a lógica utilizada no método
`main` e nos métodos auxiliares da classe `App`.

------------------------------------------------------------------------

## Lógica do método main

O método `main` executa a aplicação seguindo uma sequência organizada de
etapas:

### 1. Preparação do ambiente

Primeiro, são criados os objetos necessários para que o sistema
funcione:

-   Um `Scanner` para ler entradas do usuário.
-   Um objeto `Transacao` para registrar as movimentações financeiras.
-   Um objeto `Validacao` para controlar autenticação.
-   Um `Cliente` e uma `Conta` com saldo inicial.
-   Caso o tipo da conta seja especial, é criado também um objeto
    `Especial`.

Essa etapa garante que todos os elementos do sistema estejam prontos
antes de iniciar qualquer operação.

------------------------------------------------------------------------

### 2. Definição do tipo de conta

A variável `tipo` determina o comportamento da conta:

-   Se for 1 → conta comum.
-   Se for 2 → conta especial.

Dependendo dessa escolha, o sistema executa transações chamando métodos
diferentes (com ou sem o objeto `Especial`).

------------------------------------------------------------------------

### 3. Execução das transações

Após definir o tipo da conta, são realizadas várias transações
financeiras.

Cada transação: - Recebe data, conta, histórico, valor e tipo de
operação. - Atualiza o saldo da conta conforme as regras de negócio. - É
armazenada na lista de movimentos.

------------------------------------------------------------------------

### 4. Processo de autenticação

Antes de permitir o acesso ao extrato, o sistema exige autenticação.

A lógica funciona assim:

-   O usuário pode tentar autenticar até 3 vezes.
-   Se conseguir, o fluxo continua normalmente.
-   Se falhar 3 vezes, o sistema pergunta se deseja redefinir usuário e
    senha.

------------------------------------------------------------------------

### 5. Redefinição de credenciais

Se o usuário optar por redefinir:

-   É solicitado um novo usuário.
-   É solicitada uma nova senha.
-   A senha precisa obedecer às regras de validação.

Se a redefinição for bem-sucedida, o usuário pode tentar autenticar
novamente.

Caso falhe novamente, a aplicação é encerrada.

------------------------------------------------------------------------

### 6. Emissão do extrato

Se a autenticação for bem-sucedida:

-   O sistema chama o método `imprimirExtrato`.
-   O extrato mostra todas as movimentações realizadas.
-   Mostra saldo anterior e saldo atual.
-   Mede o tempo de execução da impressão.

------------------------------------------------------------------------

## Lógica dos Métodos Auxiliares

### autenticarUsuario

-   Executa até 3 tentativas de login.
-   Solicita usuário e senha.
-   Retorna `true` se autenticado.
-   Retorna `false` após 3 falhas.

------------------------------------------------------------------------

### perguntarSimNao

-   Exibe uma mensagem ao usuário.
-   Só aceita respostas "s" ou "n".
-   Continua perguntando até receber uma resposta válida.
-   Retorna `true` para "s" e `false` para "n".

------------------------------------------------------------------------

### redefinirCredenciais

-   Solicita usuário.
-   Solicita nova senha.
-   Valida a senha usando a classe `Validacao`.
-   Continua solicitando até que a senha seja válida.

------------------------------------------------------------------------

### imprimirExtrato

-   Exibe os dados principais da conta.
-   Percorre a lista de movimentos.
-   Mostra data, histórico, valor e tipo da operação.
-   Exibe saldo final.
-   Calcula e mostra o tempo de execução.

------------------------------------------------------------------------

## Resumo da Lógica Geral

A classe `App` executa a aplicação seguindo esta ordem:

1.  Cria os objetos.
2.  Define o tipo da conta.
3.  Executa transações.
4.  Controla autenticação.
5.  Permite redefinição de senha se necessário.
6.  Exibe o extrato.

Toda a lógica está organizada de forma sequencial e controlada por
condições e laços, garantindo que o usuário só visualize o extrato após
autenticação válida.
