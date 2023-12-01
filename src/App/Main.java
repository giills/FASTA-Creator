package App;

import java.awt.CardLayout;

import javax.swing.*;

import Adapter.View.ViewModel;
import Data_Access.FinderDataAccessObject;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame application = new JFrame("Find and Write");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // view objects
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // what view is showing
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        ViewModel viewModel = new ViewModel();
        FinderDataAccessObject finderDataAccess = new FinderDataAccessObject();

        View view = FindWriteUseCaseFactory.create(viewManagerModel, viewModel, finderDataAccess);
        views.add(view, view.viewName);

        /* to be added as UI for each use case is done
        CompareView compareView = CompareUseCaseFactory.create(viewManagerModel, navigationViewModel, compareViewModel);
        views.add(compareView, compareView.viewName);


        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessOject);
        views.add(playerSearchView, playerSearchView.viewName);
        */

        viewManagerModel.setActiveView(view.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
