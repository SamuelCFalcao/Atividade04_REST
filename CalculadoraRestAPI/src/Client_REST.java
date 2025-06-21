import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client_REST 
{

    private static final String BASE_URL = "https://calculadora-fxpc.onrender.com/operations";

    public static void main(String[] args) 
    {
        try 
        {
            // 1. Listar operações disponíveis
            System.out.println("Operações disponíveis:");
            listOperations();

            // 2. Escolher operação
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDigite a operação (ex: sum, sub, mul, div): ");
            String operacao = scanner.nextLine();

            System.out.print("Digite o valor de A: ");
            int a = scanner.nextInt();

            System.out.print("Digite o valor de B: ");
            int b = scanner.nextInt();

            // 3. Realizar operação
            executeOperation(operacao, a, b);

        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private static void listOperations() throws Exception 
    {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );

        String line;
        while ((line = br.readLine()) != null) 
        {
            System.out.println(line);
        }

        br.close();
        conn.disconnect();
    }

    private static void executeOperation(String operation, int a, int b) throws Exception 
    {
        URL url = new URL(BASE_URL + "/" + operation);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String jsonInput = String.format("{\"a\": %d, \"b\": %d}", a, b);

        OutputStream os = conn.getOutputStream();
        os.write(jsonInput.getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );

        String line;
        System.out.println("\nResultado:");
        while ((line = br.readLine()) != null) 
        {
            System.out.println(line);
        }

        br.close();
        conn.disconnect();
    }
}