package Adapter.Finder;
import java.util.*;

import Adapter.View.ViewModel;
import Adapter.View.ViewState;
import Adapter.Writer.WriterController;
import App.ViewManagerModel;
import Use_Case.FindSequence.FinderOutputBoundary;
import Use_Case.FindSequence.FinderOutputData;

public class FinderPresenter implements FinderOutputBoundary{
    private final WriterController writerController;
    private ViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    
    public FinderPresenter(WriterController writerController, ViewManagerModel viewManagerModel, ViewModel viewModel){
        this.writerController = writerController;
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(FinderOutputData outputData){
        ViewState viewState = viewModel.getState();
        viewState.setGenes(outputData.getGenes());
        this.viewModel.setState(viewState);
        viewModel.firePropertyChanged();
    }  
}