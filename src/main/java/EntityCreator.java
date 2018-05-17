public class EntityCreator {
    private EntityCreator() {}

    private static long currId = Long.MIN_VALUE;

    private static Unit createUnit() {

        return new Unit(currId++);
    }
}
