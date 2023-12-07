package Adapter.Finder;

import Adapter.View.ViewModel;
import Adapter.View.ViewState;
import Use_Case.FindSequence.FinderOutputBoundary;
import Use_Case.FindSequence.FinderOutputData;

public class FinderPresenter implements FinderOutputBoundary{
    private ViewModel viewModel;
    
    public FinderPresenter(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void prepareView(FinderOutputData outputData){
        ViewState viewState = viewModel.getState();
        viewState.setGenes(outputData.getGenes());
        this.viewModel.setState(viewState);
        viewModel.firePropertyChanged();
    }  
}