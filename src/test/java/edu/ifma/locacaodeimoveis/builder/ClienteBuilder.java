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
        builder.cliente.setNome("Akila");
        builder.cliente.setDataNascimento(LocalDate.of(2000, 12, 1));
        builder.cliente.setCpf("123.123.123-12");
        builder.cliente.setEmail("akila@email.com");
        builder.cliente.setTelefone1("986554653");

        return builder;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comId(Integer id) {
        cliente.setId(id);
        return this;
    }

    public Cliente constroi() {
        return cliente;
    }
}
