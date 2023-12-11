package concurrent;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.ConstantFunction;
public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        ConstantFunction sourse = new ConstantFunction(-1);
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(sourse, 1, 1000, 1000);
        ReadTask readTask = new ReadTask(tabulatedFunction);
        Thread threadRead = new Thread(readTask);
        WriteTask writeTask = new WriteTask(tabulatedFunction, 0.5);
        Thread threadWrite = new Thread(writeTask);
        threadRead.start();
        threadWrite.start();
    }
}
