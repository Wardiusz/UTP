package zad2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main extends JFrame {
  private final DefaultListModel<TaskHandler> listModel;
  private final JList<TaskHandler> taskList;
  private final ExecutorService executorService;
  private final List<Future<String>> futures;

  public Main() {
    setTitle("10_points_program.exe");
    setSize(500, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    listModel = new DefaultListModel<>();
    taskList = new JList<>(listModel);
    taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    executorService = Executors.newFixedThreadPool(10);
    futures = new ArrayList<>();

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Add Task");
    JButton cancelButton = new JButton("Cancel Task");
    JButton resultButton = new JButton("Show Result");

    buttonPanel.add(addButton);
    buttonPanel.add(cancelButton);
    buttonPanel.add(resultButton);

    panel.add(buttonPanel, BorderLayout.SOUTH);

    add(panel);

    addButton.addActionListener(al -> {
      Task task = new Task(taskList);
      Future<String> future = executorService.submit(task);
      TaskHandler taskWrapper = new TaskHandler(future);

      listModel.addElement(taskWrapper);
      futures.add(future);

      refreshList();
    });

    cancelButton.addActionListener(al -> {
      int selectedIndex = taskList.getSelectedIndex();

      if (selectedIndex != -1) {
        Future<String> future = listModel.get(selectedIndex).getFuture();
        future.cancel(true);
        refreshList();
      }
    });

    resultButton.addActionListener(al -> {
      int selectedIndex = taskList.getSelectedIndex();

      if (selectedIndex != -1) {
        Future<String> future = listModel.get(selectedIndex).getFuture();
        try {
          if (future.isDone()) {
            if (future.isCancelled()) {
              JOptionPane.showMessageDialog(this, "Task was cancelled.");
            } else {
              String result = future.get();

              JOptionPane.showMessageDialog(this, "Result: " + result);
            }
          } else {
            JOptionPane.showMessageDialog(this, "Task is not yet complete.");
          }
        } catch (InterruptedException | ExecutionException | CancellationException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
        }
        refreshList();
      }
    });
  }

  private void refreshList() {
    taskList.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Main().setVisible(true));
  }
}

