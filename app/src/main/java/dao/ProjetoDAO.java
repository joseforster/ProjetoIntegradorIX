package dao;

import java.util.ArrayList;

import model.ProjetoViewModel;
import util.IDAO;

public class ProjetoDAO implements IDAO<ProjetoViewModel> {
    @Override
    public boolean Create(ProjetoViewModel viewModel) {
        return false;
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
    public ArrayList<ProjetoViewModel> ReadAll() {
        return null;
    }
}
