package edu.ifma.locacaodeimoveis.model;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoImovel {
      APARTAMENTO(0),
      CASA(1),
      KITNET(2);


      private int identificador;

      TipoImovel(int identificador) {
            this.identificador = identificador;
      }

      public static TipoImovel toEnum(int identificador) {
            return Stream.of(TipoImovel.values())
                    .filter(t -> t.getIdentificador() == identificador)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Identificador: " + identificador + " inválido."));
      }


}
