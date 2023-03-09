package model;

import java.util.Date;

public class RequisitoViewModel {
    private int Id;
    private String Descricao;
    private Date DataCriacao;
    private int NivelImportancia;
    private int NivelDificuldade;
    private Number TempoEstimado;
    private ProjetoViewModel projeto;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public int getNivelImportancia() {
        return NivelImportancia;
    }

    public void setNivelImportancia(int nivelImportancia) {
        NivelImportancia = nivelImportancia;
    }

    public int getNivelDificuldade() {
        return NivelDificuldade;
    }

    public void setNivelDificuldade(int nivelDificuldade) {
        NivelDificuldade = nivelDificuldade;
    }

    public Number getTempoEstimado() {
        return TempoEstimado;
    }

    public void setTempoEstimado(Number tempoEstimado) {
        TempoEstimado = tempoEstimado;
    }

    public ProjetoViewModel getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoViewModel projeto) {
        this.projeto = projeto;
    }
}
