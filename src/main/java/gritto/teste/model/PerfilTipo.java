package gritto.teste.model;

public enum PerfilTipo {
    PRESTADOR(1, "PRESTADOR"), TOMADOR(2, "TOMADOR");

    private long cod;
    private String desc;

    private PerfilTipo(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}
