package gritto.teste.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String disponibilidade;

    @ManyToOne
    @JoinColumn(name = "catServico_id")
    private CategoriaServico cat_servico;

    @OneToOne
    @JoinColumn(name = "tomador_id")
    private Usuario tomador;

    private Status status;

}
