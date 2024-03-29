package br.com.viasoft.projetoapi.model;

public enum Status {

    OFFLINE,
    ONLINE,
    INSTAVEL,
    FALTANTE;

    public static Status fromIcone(String nomeIcone) {
        switch (nomeIcone) {
            case "imagens/bola_vermelho_P.png": return Status.OFFLINE;
            case "imagens/bola_verde_P.png": return Status.ONLINE;
            case "imagens/bola_amarela_P.png": return Status.INSTAVEL;
            default: return Status.FALTANTE;
        }
    }
}
