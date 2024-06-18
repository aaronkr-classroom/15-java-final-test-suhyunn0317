import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double result;
    private String operator;
    
    public calculator() {
        currentInput = new StringBuilder();
        result = 0;
        operator = "";

    
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

     
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

  
        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "AC", "=", "/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

  
        createMenuBar();

      
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setTitle("계산기");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

     
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);

    
        JMenu editMenu = new JMenu("Edit");

     
        JMenu helpMenu = new JMenu("Help");

     
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if (command.equals("AC")) {
            currentInput.setLength(0);
            result = 0;
            operator = "";
            display.setText("0");
        } else if (command.equals("=")) {
            calculate(Double.parseDouble(currentInput.toString()));
            display.setText(String.valueOf(result));
            currentInput.setLength(0);
        } else {
            if (currentInput.length() > 0) {
                calculate(Double.parseDouble(currentInput.toString()));
                currentInput.setLength(0);
            }
            operator = command;
        }
    }

    private void calculate(double number) {
        switch (operator) {
            case "+":
                result += number;
                break;
            case "-":
                result -= number;
                break;
            case "*":
                result *= number;
                break;
            case "/":
                if (number != 0) {
                    result /= number;
                } else {
                    JOptionPane.showMessageDialog(this, "0으로 나눌 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    result = 0;
                }
                break;
            default:
                result = number;
                break;
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}

