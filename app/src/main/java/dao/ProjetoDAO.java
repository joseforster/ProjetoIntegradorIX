package dao;

import java.util.ArrayList;

import model.ProjetoViewModel;
import util.IDAO;

public class ProjetoDAO implements IDAO<ProjetoViewModel> {
    @Override
    public boolean Create(ProjetoViewModel viewModel) {
        return true;
    }

    @Override
    public boolean Update(ProjetoViewModel viewModel) {
        return false;
    }

    @Override
    public boolean Delete(ProjetoViewModel viewModel) {
        return false;
    }

    @Override
    public ArrayList<String> ReadAll() {
        //Apenas para apresentação e teste
        ArrayList<String> lista = new ArrayList<String>();

        lista.add("1 - Projeto Um");
        lista.add("2 - Projeto Dois");

        return lista;
    }
}
