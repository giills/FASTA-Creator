package App;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Adapter.Finder.FinderController;
import Adapter.View.ViewModel;
import Adapter.View.ViewState;
import Adapter.Writer.WriterController;

public class View extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "view";
    private final JTextArea locationInputField = new JTextArea(10, 10);
    final JMultilineLabel result;
    //private final JLabel result = new JLabel("");
    private final JLabel speciesLabel = new JLabel("Enter Species Name Abbreviation");
    private final JTextField speciesInputField = new JTextField(30);
    private final JLabel label = new JLabel("Enter Locations");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final JButton search;
    private final JButton write;

    public View(FinderController finderController, ViewModel viewModel, WriterController writerController) {
        result = new JMultilineLabel();
        result.setText("");
        result.setMaxWidth(400);
        result.setBorder(BorderFactory.createRaisedBevelBorder());

        viewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Find and Write");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        search = new JButton("Find genes");
        write = new JButton("Write to FASTA file");

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            ViewState currentState = viewModel.getState();
                            finderController.execute(currentState.getSpecies(), currentState.getArrayLocation());
                        }
                    }
                }
        );

        write.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(write)) {
                            ViewState currentState = viewModel.getState();
                            // Can only be done after fine
                            if ((currentState.getSpecies() == "") | (currentState.getGenes().keySet().size() == 0) | currentState.getResult() == ""){
                                JOptionPane.showMessageDialog(null, "Nothing to write!");
                            }
                            else{
                                writerController.execute(currentState.getSpecies(), currentState.getGenes());
                            }
                        }
                    }
                }
        );

        speciesInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ViewState currentState = viewModel.getState();
                        String text = speciesInputField.getText() + e.getKeyChar();
                        currentState.setSpecies(text);
                        viewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        locationInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ViewState currentState = viewModel.getState();
                        String[] genes = locationInputField.getText().split("\\n");
                        genes[genes.length - 1] += e.getKeyChar();
                        // build the text to set into textarea2
                        currentState.setLocation(genes);
                        viewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(speciesLabel)
                                .addComponent(speciesInputField)
                                .addComponent(label)
                                .addComponent(locationInputField)
                                .addComponent(result))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(search)
                                .addComponent(write)
                                .addComponent(buffer))
                                
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(speciesLabel)
                                                .addComponent(search))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(speciesInputField)
                                                .addComponent(write))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                                .addComponent(label)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                                .addComponent(locationInputField)
                                                .addComponent(buffer)
                                                )
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(result)
                        )

        );
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewState state = (ViewState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
        result.setText(state.getResult());
    }
}
