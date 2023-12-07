package Adapter.Writer;

import java.util.Map;

import Adapter.View.ViewModel;
import App.View;
import App.ViewManagerModel;
import Use_Case.WriteEntry.WriterOutputBoundary;

public class WriterPresenter implements WriterOutputBoundary{
    private ViewManagerModel viewManagerModel;
    private ViewModel viewmodel;
    public WriterPresenter(ViewManagerModel viewManagerModel, ViewModel viewModel){
        this.viewManagerModel = viewManagerModel;
        this.viewmodel = viewModel;
    }
}
