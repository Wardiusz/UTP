package zad1;

public class StringTask  implements Runnable {
    private final String sentence;
    private final int num;
    volatile TaskState state;
    private String result;
    private boolean done = false;
    Thread thread;

    public enum TaskState {
        CREATED, RUNNING, ABORTED, READY
    }

    public StringTask(String string, int i) {
        this.result = "";
        this.sentence = string;
        this.num = i;
        this.state = TaskState.CREATED;
    }

    public TaskState getState() {
        return this.state;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getResult() {
        return result;
    }

    public void run(){
        this.state = TaskState.RUNNING;

        synchronized (this) {
            try {
                for (int i = 0; i < num; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        state = TaskState.ABORTED;
                        return;
                    }
                    result += sentence;
                }

                this.state = TaskState.READY;
                this.done = true;
            }
            catch (Exception e) {
                this.state = TaskState.ABORTED;
                this.done = false;
            }
        }

    }

    public void start() {
        if (state == TaskState.CREATED) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void abort() {
        this.done = true;
        this.state = TaskState.ABORTED;
        thread.interrupt();
    }

}
