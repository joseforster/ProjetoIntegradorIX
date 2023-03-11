package model;

import java.util.Date;

public class RequisitoViewModel {
    private int Id;
    private String Descricao;
    private Date DataCriacao;
    private int NivelImportancia;
    private int NivelDificuldade;
    private double TempoEstimado;
    private ProjetoViewModel ProjetoViewModel;

    public RequisitoViewModel(int nivelImportancia, int nivelDificuldade, double tempoEstimado, ProjetoViewModel projetoViwModel, String descricao) {
        NivelImportancia = nivelImportancia;
        NivelDificuldade = nivelDificuldade;
        TempoEstimado = tempoEstimado;
        ProjetoViewModel = projetoViwModel;
        Descricao = descricao;
    }

    public RequisitoViewModel() {
    }

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

    public double getTempoEstimado() {
        return TempoEstimado;
    }

    public void setTempoEstimado(double tempoEstimado) {
        TempoEstimado = tempoEstimado;
    }

    public ProjetoViewModel getProjetoViewModel() {
        return ProjetoViewModel;
    }

    public void setProjetoViewModel(ProjetoViewModel projetoViewModel) {
        this.ProjetoViewModel = projetoViewModel;
    }
}
