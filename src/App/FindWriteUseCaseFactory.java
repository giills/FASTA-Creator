package App;

import Adapter.Finder.FinderController;
import Adapter.Finder.FinderPresenter;
import Adapter.View.ViewModel;
import Adapter.Writer.WriterController;
import Adapter.Writer.WriterPresenter;
import Data_Access.FinderDataAccessObject;
import Use_Case.FindSequence.FindSequence;
import Use_Case.FindSequence.FindSequenceDataAccessInterface;
import Use_Case.FindSequence.FinderInputBoundary;
import Use_Case.FindSequence.FinderOutputBoundary;
import Use_Case.WriteEntry.WriteEntry;
import Use_Case.WriteEntry.WriteEntryDataAccessInterface;
import Use_Case.WriteEntry.WriterInputBoundary;
import Use_Case.WriteEntry.WriterOutputBoundary;

public class FindWriteUseCaseFactory {
    private FindWriteUseCaseFactory(){};
    public static View create(ViewManagerModel viewManagerModel, ViewModel viewModel, FinderDataAccessObject findDataAccessObject){
            WriterController writerController = createWriteCase(viewManagerModel, viewModel, findDataAccessObject);
            FinderController finderController = createFindCase(viewManagerModel, viewModel, findDataAccessObject, writerController);
            return new View(finderController, viewModel, writerController);
    }
    private static WriterController createWriteCase(ViewManagerModel viewManagerModel, ViewModel viewModel, WriteEntryDataAccessInterface findDataAccessObject){
        WriterOutputBoundary writerOutputBoundary = new WriterPresenter(viewManagerModel, viewModel);

        WriterInputBoundary writerInteractor = new WriteEntry(writerOutputBoundary, findDataAccessObject);

        return new WriterController(writerInteractor);
    }

    private static FinderController createFindCase(ViewManagerModel viewManagerModel, ViewModel viewModel, FindSequenceDataAccessInterface findDataAccessObject, WriterController writerController){
        FinderOutputBoundary finderOutputBoundary = new FinderPresenter(viewModel);

        FinderInputBoundary finderInteractor = new FindSequence(finderOutputBoundary, findDataAccessObject);

        return new FinderController(finderInteractor);
    }
}
