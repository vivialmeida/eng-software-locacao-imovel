package edu.ifma.locacaodeimoveis.builder;

import edu.ifma.locacaodeimoveis.model.Cliente;

import java.time.LocalDate;

public class ClienteBuilder {
    private Cliente cliente;

    private ClienteBuilder() {
    }

    public static ClienteBuilder umCliente() {
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Cliente();
        builder.cliente.setNomeCliente("Akila");
        builder.cliente.setDtNascimento(LocalDate.of(2000, 12, 1));
        builder.cliente.setCpf("123.123.123-12");
        builder.cliente.setEmail("akila@email.com");
        builder.cliente.setCelular("986554653");

        return builder;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNomeCliente(nome);
        return this;
    }

    public ClienteBuilder comId(Long id) {
        cliente.setIdCliente(id);
        return this;
    }

    public Cliente constroi() {
        return cliente;
    }
}
