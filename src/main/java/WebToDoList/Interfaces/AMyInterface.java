package WebToDoList.Interfaces;

public abstract class AMyInterface extends Thread implements IMyInterface {
    @Override
    public abstract void run();
}
