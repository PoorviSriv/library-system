# Library Management System

A REST API for managing a library's books, authors, members, and loans.

## Entities

- **Book** — title, ISBN, total copies, available copies
- **Author** — name
- **Member** — name, email, role (librarian or member)
- **Loan** — links one member to one book, with a checkout date, due date, and return date

## Relationships

- A **Book** can have multiple **Authors**, and an **Author** can write multiple **Books** — many-to-many
- A **Member** can have multiple **Loans**, but each **Loan** belongs to one **Member** — one-to-many
- A **Book** can be borrowed many times over its life (multiple **Loans**), but each **Loan** is for exactly one **Book** — one-to-many

## Tech stack

Spring Boot, Spring Data JPA, Spring Security, Kafka, Docker, Kubernetes