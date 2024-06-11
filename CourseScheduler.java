import java.util.*;

public class CourseScheduler {
    private Map<String, List<String>> graph;
    private Set<String> visited;
    private Set<String> recStack;
    private List<String> result;

    public CourseScheduler() {
        this.graph = new HashMap<>();
        this.visited = new HashSet<>();
        this.recStack = new HashSet<>();
        this.result = new ArrayList<>();
    }

    public void addCourse(String course, String prereq) {
        graph.computeIfAbsent(course, k -> new ArrayList<>()).add(prereq);
        if (!graph.containsKey(prereq)) {
            graph.put(prereq, new ArrayList<>());
        }
    }

    private boolean isCyclicUtil(String course) {
        if (recStack.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }
        
        visited.add(course);
        recStack.add(course);
        
        for (String neighbor : graph.get(course)) {
            if (isCyclicUtil(neighbor)) {
                return true;
            }
        }
        
        recStack.remove(course);
        return false;
    }

    public boolean isCyclic() {
        for (String course : graph.keySet()) {
            if (isCyclicUtil(course)) {
                return true;
            }
        }
        return false;
    }

    private void topologicalSortUtil(String course, Set<String> visited, Deque<String> stack) {
        visited.add(course);
        for (String neighbor : graph.get(course)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(course);
    }

    public List<String> topologicalSort() {
        if (isCyclic()) {
            throw new IllegalStateException("Cycle detected. No valid course schedule.");
        }

        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String course : graph.keySet()) {
            if (!visited.contains(course)) {
                topologicalSortUtil(course, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        CourseScheduler scheduler = new CourseScheduler();
        scheduler.addCourse("CourseA", "CourseB");  // CourseB is a prerequisite of CourseA
        scheduler.addCourse("CourseB", "CourseC");
        scheduler.addCourse("CourseC", "CourseD");

        try {
            List<String> schedule = scheduler.topologicalSort();
            System.out.println("Course Schedule: " + schedule);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
