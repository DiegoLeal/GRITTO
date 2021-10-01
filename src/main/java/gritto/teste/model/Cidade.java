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
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String uf;

    @ManyToOne
    @JoinColumn(name = "uniao_federativa", referencedColumnName = "id")
    private UniaoFederativa uniaoFederativa;
}
