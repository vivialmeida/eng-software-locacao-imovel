package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Cliente;

import java.util.logging.Logger;

public class EmailService {

  public void enviarEmailaluguel(Cliente cliente) {

    try {

      this.enviar();


    } catch (EmailException e) {
      Logger.getLogger("Erro ao enviar email para : " + cliente.getNome());
    }
  }

  public void enviar(){

  }// enviar

}
