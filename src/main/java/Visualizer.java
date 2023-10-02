import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizer implements ActionListener {
    private JFrame frame = new JFrame();
    private DrawPanel drawPanel;
    private JButton runButton;
    private JButton shuffleButton;

    private int current = 0;
    private boolean sorted;
    private boolean reset;

    public Visualizer() {
        drawPanel = createDrawPanel();
        drawPanel.setHeightOfArray();

        JPanel buttonPanel = createButtons();

        frame.setSize(1280, 720);
        frame.setTitle("Algorithmus Visualisierer");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        frame.add(drawPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private DrawPanel createDrawPanel(){
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(1280,576));
        drawPanel.setBackground(new Color(211,211,211));
        return drawPanel;
    }


    private JPanel createButtons(){

        JPanel buttonPanel = new JPanel();
        runButton = new JButton("run");
        shuffleButton = new JButton("shuffle");
        runButton.setPreferredSize(new Dimension(142,70));
        shuffleButton.setPreferredSize(new Dimension(142,70));

        runButton.addActionListener(this);
        shuffleButton.addActionListener(this);

        buttonPanel.add(runButton);
        buttonPanel.add(shuffleButton);

        return buttonPanel;
    }

    private void oneThingSelectionSort(int[] arr){
        int min_pos = current;
        for(int j = current+1; j < arr.length;j++ ){
            if(arr[j] < arr[min_pos]){
                min_pos = j;
            }
        }
        if(current != arr.length -1 ){
            int temp = arr[min_pos];
            arr[min_pos] = arr[current];
            arr[current] = temp;
        }
        current++;
    }

    private boolean isSorted(){
        sorted = true;
        int[] array = drawPanel.getArray();

        for(int i = 0; i < array.length-1; i++){
            if(array[i] > array[i+1]){
                sorted = false;
            }
        }

        if(sorted|| reset) {
            current = 0;
        }

        return sorted;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == runButton){
            reset = false;
            Timer timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent ae){

                    if (isSorted() || reset) {
                        ((Timer) ae.getSource()).stop();
                        runButton.setEnabled(true);
                    }
                    else {
                        runButton.setEnabled(false);
                        oneThingSelectionSort(drawPanel.getArray());

                    }
                    drawPanel.repaint();
                }
            });
            timer.start();
        }
        else if(e.getSource() == shuffleButton){
            reset = true;
            drawPanel.setHeightOfArray();

        }
    }

}
