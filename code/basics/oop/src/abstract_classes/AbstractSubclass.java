package abstract_classes;

abstract class AbstractSubclass extends AbstractSuperClass {
    @Override
    void test1() {
        System.out.println("[AbstractSubclass] test1");
    }

    abstract void test3();
}
