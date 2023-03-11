package dao;

import java.util.ArrayList;

import model.RequisitoViewModel;
import util.IDAO;

public class RequisitoDAO implements IDAO<RequisitoViewModel> {
    @Override
    public boolean Create(RequisitoViewModel viewModel) {
        return true;
    }

    @Override
    public boolean Update(RequisitoViewModel viewModel) {
        return false;
    }

    @Override
    public boolean Delete(RequisitoViewModel viewModel) {
        return false;
    }

    @Override
    public ArrayList<String> ReadAll() {
        return null;
    }
}
