package tads.eaj.aula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Size(min = 5, max = 30, message = Error.ERRO_TAMANHO_PRODUTO)
    @NotBlank(message = Error.ERRO_CAMPO_BRANCO)
    @NotNull(message = Error.ERRO_NULL)
    String nomeProduto;

    @Size(min=5, max=20, message = Error.ERRO_TAMANHO_CATEGORIA)
    @NotBlank(message = Error.ERRO_CAMPO_BRANCO)
    @NotNull(message = Error.ERRO_NULL)
    String categoria;

    @NotNull(message = Error.ERRO_NULL)
    @DecimalMin(value = "1", message = Error.ERRO_PRECO_MINIMO)
    BigDecimal preco;

    @NotNull(message = Error.ERRO_NULL)
    @DecimalMin(value = "1", message = Error.ERRO_QUANTIDADE_MINIMA)
    Integer quantidade;
}
