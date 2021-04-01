package edu.ifma.locacaodeimoveis.model;

import lombok.Getter;

@Getter
public enum TipoImovel {

  APARTAMENTO(1),
  CASA(2),
  KITNET(3),
  SITIO(4);

  private int identificador;

  TipoImovel(int identificador) {
    this.identificador = identificador;
  }
}
