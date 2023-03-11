package util;

import java.util.ArrayList;

public interface IDAO<T> {
    boolean Create(T viewModel);

    boolean Update(T viewModel);

    boolean Delete(T viewModel);

    ArrayList<String> ReadAll();
}
