package lesson2;

public class LambdaMain {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        MyFunction f = new MyFunctionImpl();
        MyFunction f2 = new MyFunction() {
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        };
        MyFunction f3 = (a1, b1) -> {
            return a1 + b1;
        };

        MyFunction f4 = Integer::sum;

        System.out.println(f.apply(a, b));
    }
}

class MyFunctionImpl implements MyFunction {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}