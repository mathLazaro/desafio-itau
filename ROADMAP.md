# Roadmap de Complexidade Arquitetural – Projeto Transactions

Este documento propõe **requisitos incrementais** para aumentar gradualmente a complexidade do projeto e **treinar
arquitetura de software (Clean Architecture / DDD light)**.

Cada nível adiciona desafios reais e indica **o que estudar em paralelo** para aplicar corretamente.

---

## 🧱 NÍVEL 1 — Complexidade de Domínio (Fundação)

### 1️⃣ Janela de tempo válida

**Regra**

* Só permitir transações criadas nos últimos **60 segundos**

**Treina**

* Regras temporais no domínio
* Testabilidade do tempo

**Estudar em paralelo**

* `java.time.Clock`
* Invariantes de domínio

---

### 2️⃣ Valor zero inválido

**Regra**

* `value == 0` não é permitido

**Treina**

* Clareza de regras simples
* Testes de domínio

**Estudar em paralelo**

* Value Objects
* Design by Contract

---

### 3️⃣ Tipos de transação

**Regra**

* `DEBIT` e `CREDIT`
* `DEBIT` possui restrições extras

**Treina**

* Enum rico
* Polimorfismo

**Estudar em paralelo**

* Object Calisthenics
* Evitar `if/else` por tipo

---

## 🧱 NÍVEL 2 — Casos de Uso (Application Layer)

### 4️⃣ Limite diário por usuário

**Regra**

* Usuário não pode debitar mais que X por dia

**Treina**

* Use Case orquestrador
* Consulta a repositório

**Estudar em paralelo**

* Application vs Domain rules
* Dependency Inversion

---

### 5️⃣ Transação duplicada

**Regra**

* Não permitir transações duplicadas (idempotência lógica)

**Treina**

* Consistência
* Políticas de unicidade

**Estudar em paralelo**

* Idempotência
* Testes com mocks

---

### 6️⃣ Estados da transação

**Regra**

* `CREATED → APPROVED → REJECTED`
* Transições inválidas são proibidas

**Treina**

* Máquina de estados
* Comportamento no domínio

**Estudar em paralelo**

* State Pattern
* Aggregate Roots

---

## 🧱 NÍVEL 3 — Interação entre Domínios

### 7️⃣ Notificações

**Regra**

* Aprovação dispara notificação

**Treina**

* Gateway
* Domínio transversal

**Estudar em paralelo**

* Ports & Adapters
* Side Effects

---

### 8️⃣ Auditoria

**Regra**

* Toda mudança relevante gera evento de auditoria

**Treina**

* Eventos de domínio
* Baixo acoplamento

**Estudar em paralelo**

* Domain Events
* Event-driven design (básico)

---

### 9️⃣ Múltiplas moedas

**Regra**

* Transações podem ter moedas diferentes
* Estatísticas devem normalizar

**Treina**

* Value Object complexo
* Integração externa

**Estudar em paralelo**

* Money Pattern
* Anti-Corruption Layer

---

## 🧱 NÍVEL 4 — Leitura Avançada (CQRS light)

### 🔟 Estatísticas em tempo real vs histórico

**Regra**

* Últimos 60s → memória
* Histórico → banco

**Treina**

* Separação de modelos
* Consistência eventual

**Estudar em paralelo**

* CQRS
* Read Models

---

### 1️⃣1️⃣ Estatísticas por dimensão

**Regra**

* Estatísticas por usuário, tipo e período

**Treina**

* DTOs especializados
* Queries otimizadas

**Estudar em paralelo**

* Projections
* Query Optimization

---

## 🧱 NÍVEL 5 — Sistema Real / Produção

### 1️⃣2️⃣ Idempotência via Header

**Regra**

* Requisições com mesmo `Idempotency-Key` não duplicam efeitos

**Treina**

* Robustez
* Integração HTTP → domínio

**Estudar em paralelo**

* APIs resilientes
* REST avançado

---

### 1️⃣3️⃣ Processamento assíncrono

**Regra**

* Transações grandes vão para aprovação assíncrona

**Treina**

* Separação comando/efeito
* Assíncrono sem caos

**Estudar em paralelo**

* Messaging
* Eventual consistency

---

### 1️⃣4️⃣ Feature Toggle

**Regra**

* Regras podem ser ligadas/desligadas

**Treina**

* Configuração sem acoplamento

**Estudar em paralelo**

* Strategy Pattern
* Feature Flags

---

## 🧪 NÍVEL 6 — Testes como Arquitetura

### 1️⃣5️⃣ Pirâmide de testes

**Requisitos**

* Testes de domínio
* Testes de use case
* Testes de infra
* Testes de controller

**Treina**

* Limites arquiteturais
* Qualidade real

**Estudar em paralelo**

* Test Pyramid
* Contract Tests

---

## 🎯 Roteiro sugerido de evolução

1. Imutabilidade + Clock
2. Tipos de transação
3. Limite diário
4. Estados
5. Notificação via Gateway
6. Estatísticas avançadas
7. Eventos de domínio

---

## 📚 Leituras recomendadas

* Clean Architecture — Robert C. Martin
* Domain-Driven Design (capítulos iniciais) — Eric Evans
* Implementing DDD — Vaughn Vernon
* Refactoring — Martin Fowler

---

> **Objetivo final**: pensar arquitetura como ferramenta de evolução, não como dogma.
