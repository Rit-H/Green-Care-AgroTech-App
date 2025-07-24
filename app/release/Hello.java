import java.util.*;

public class Hello {

    public static void main(String argss[]){

        Scanner sc = new Scanner(System.in);
        int  a= sc.nextInt();


        int i=0;
        while (i<=10){


            int table=a*i;

            System.out.println(table);
            i++;
        }


    }
}