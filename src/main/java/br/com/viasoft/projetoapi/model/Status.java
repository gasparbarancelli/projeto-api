package br.com.viasoft.projetoapi.model;

public enum Status {
    OFFLINE(0),
    ONLINE(1),
    INSTAVEL(2),
    FALTANTE(3);

    private int numero;

    Status(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public static Status fromIcone(String nomeIcone) {
        switch (nomeIcone) {
            case "imagens/bola_vermelho_P.png": return Status.OFFLINE;
            case "imagens/bola_verde_P.png": return Status.ONLINE;
            case "imagens/bola_amarela_P.png": return Status.INSTAVEL;
            default: return Status.FALTANTE;
        }
    }
}
