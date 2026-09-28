public class Ex01 {
    public static void main(String[] args) {
        
        int a=10;
        int b=20;

        try{
            int resultado=a/b;
            System.out.println("Resultado "+resultado);
        }catch(ArithmeticException e){
            System.out.println("Erro: Não é possível dividir por zero!");

        }
        finally{
            System.out.println("Tchau");
        }
    }
}
