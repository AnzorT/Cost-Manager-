package il.ac.hit.costmanager;

import il.ac.hit.costmanager.models.CostManagerModel;
import il.ac.hit.costmanager.models.CostManagerException;
import il.ac.hit.costmanager.viewmodels.CostManagerViewModel;
import il.ac.hit.costmanager.views.IView;
import il.ac.hit.costmanager.models.IModel;
import il.ac.hit.costmanager.viewmodels.IViewModel;
import il.ac.hit.costmanager.views.CostManagerView;

import javax.swing.*;

/**
 * This class describes Program.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Program
{
    /**
     * Main function that runs the entire program.
     * @param args Stores Java command line arguments and is an array of type java.lang.String class.
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    public static void main(String[] args) throws CostManagerException {
        // Creating new IModel.
        IModel model = new CostManagerModel();
        // Creating new IViewModel.
        IViewModel vm = new CostManagerViewModel();
        // Creating new IView.
        IView view = new CostManagerView();
        // Begin the program.
        SwingUtilities.invokeLater(() -> {
            view.init();
            view.start();
        });
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);
    }
}

