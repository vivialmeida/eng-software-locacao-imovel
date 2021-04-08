package edu.ifma.locacaodeimoveis.service;

import edu.ifma.locacaodeimoveis.model.Cliente;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

public class EmailService {

  public void enviarEmailaluguel(Cliente cliente){

    try {

      //enviarEmail

    }catch (EmailException e){
      Logger.getLogger("Erro ao enviar email para : " + cliente.getNome());
    }


  }
}
