package zad2;

import java.util.concurrent.Future;

public class TaskHandler {
    private final Future<String> future;

    public TaskHandler(Future<String> future) {
        this.future = future;
    }

    public Future<String> getFuture() {
        return future;
    }

    @Override
    public String toString() {
        return "Task: " + (future.isDone() ? "Completed" : "Running");
    }
}
