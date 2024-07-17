package zad2;

import javax.swing.*;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    private final JList<TaskHandler> taskList;
    String[] list = {
            "Pizza, mozzarella~ Pizza, mozzarella~ Rella rella rella rella Rella rella rella rella Rella rella rella rella Pizza, mozzarella~",
            "Gorgonzola~ Zola zola zola zola zola zola~",
            "I like large fries, I like large fries, I like large fries, but not fried chicken. Not fried chicken, not fried chicken, I just want those crunchies. I like large fries."
    };

    public Task(JList<TaskHandler> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(6000);
        taskList.repaint();
        int n = (int)(Math.random() * 3);
        System.out.println(n);
        return list[n];
    }
}
