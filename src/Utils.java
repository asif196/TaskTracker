import java.util.*;

public class Utils {
    private static final Map<String, Integer[]> operationArgumentCount;
    private static final Set<String> availableOperations;
    static {
        operationArgumentCount = new HashMap<String, Integer[]>();
        operationArgumentCount.put("update", new Integer[]{2, 2});
        operationArgumentCount.put("add", new Integer[]{1, 1});
        operationArgumentCount.put("delete", new Integer[]{1, 1});
        operationArgumentCount.put("mark-in-progress", new Integer[]{1, 1});
        operationArgumentCount.put("mark-done", new Integer[]{1, 1});
        operationArgumentCount.put("list", new Integer[]{0, 1});

        availableOperations = new HashSet<String>(
                Set.of("update", "add", "delete", "mark-in-progress", "mark-done", "list")
        );
    }

    public static void validate_arguments(String[] args) throws Exception{
        if(args.length == 0){
            throw new Exception(
                    "No operation specified"
            );
        }
        String operation = args[0];
        if(! availableOperations.contains(operation){
           throw new Exception(
                   "No such operation available: " + operation
           );
        }

        Integer[] argumentCount = operationArgumentCount.get(operation);
        if(args.length - 1 < argumentCount[0] || args.length - 1 > argumentCount[1]){
            throw new Exception(
                    "Not enough arguments for operation"
            );
        }
    }
}
