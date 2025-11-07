import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("===== Task Manager =====");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. List All Tasks");
            System.out.println("5. List Completed Tasks");
            System.out.println("6. List Pending Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = sc.nextLine();
                    manager.addTask(title);
                }
                case 2 -> {
                    manager.listTasks("all");
                    System.out.print("Enter task number to delete: ");
                    int delIndex = sc.nextInt() - 1;
                    manager.deleteTask(delIndex);
                }
                case 3 -> {
                    manager.listTasks("pending");
                    System.out.print("Enter task number to mark complete: ");
                    int compIndex = sc.nextInt() - 1;
                    manager.markTaskComplete(compIndex);
                }
                case 4 -> manager.listTasks("all");
                case 5 -> manager.listTasks("completed");
                case 6 -> manager.listTasks("pending");
                case 7 -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("⚠️ Invalid choice!");
            }
        }
    }
}