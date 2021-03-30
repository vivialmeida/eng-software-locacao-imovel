package edu.ifma.locacaodeimoveis.service.cadastro;

import edu.ifma.locacaodeimoveis.model.Cliente;
import edu.ifma.locacaodeimoveis.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente inserirOuAlterar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPor(Long id) {
        return clienteRepository.getOne(id);
    }
}
