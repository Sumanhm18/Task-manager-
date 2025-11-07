import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final String FILE_NAME = "tasks.dat";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String title) {
        tasks.add(new Task(title));
        saveTasks();
        System.out.println("✅ Task added successfully!");
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks();
            System.out.println("🗑️ Task deleted successfully!");
        } else {
            System.out.println("⚠️ Invalid task number.");
        }
    }

    public void markTaskComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markComplete();
            saveTasks();
            System.out.println("🎯 Task marked as complete!");
        } else {
            System.out.println("⚠️ Invalid task number.");
        }
    }

    public void listTasks(String filter) {
        System.out.println("\n--- Task List ---");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            boolean show = switch (filter) {
                case "completed" -> t.isCompleted();
                case "pending" -> !t.isCompleted();
                default -> true;
            };
            if (show) {
                System.out.println((i + 1) + ". " + t);
                count++;
            }
        }
        if (count == 0) System.out.println("No tasks found!");
        System.out.println("-----------------\n");
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}