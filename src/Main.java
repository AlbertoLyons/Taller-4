import services.Sistema;
import services.SistemaImpl;

import java.io.IOException;
import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) throws IOException {

        SistemaImpl sistema = new SistemaImpl();
        sistema.inicio();
        sistema.menuPrincipal();

    }
}