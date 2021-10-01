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
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "prestador_id")
    private Usuario prestador;

    private Status status;

    @ManyToOne
    @JoinColumn(name= "piblicacao_id")
    private Publicacao publicacao;
}