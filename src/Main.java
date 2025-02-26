import model.entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Sale> list=new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path=sc.nextLine();

        try(BufferedReader br=new BufferedReader(new FileReader(path))){

            String line= br.readLine();
            while(line!=null){
                String[] vec=line.split(",");
                list.add(new Sale(Integer.parseInt(vec[0]),Integer.parseInt(vec[1]),vec[2],Integer.parseInt(vec[3]),Double.parseDouble(vec[4])));
                line= br.readLine();
            }
            List<Sale> list2016=list.stream().sorted((p1,p2)->-p1.averagePrice().compareTo(p2.averagePrice())).filter(p->p.getYear()==2016).limit(5).toList();
            double sum=list.stream().filter(p->(p.getSeller().equals("Logan"))&&((p.getMonth()==1)||(p.getMonth()==7))).map(p->p.getTotal()).reduce(0.0,(x,y)->x+y);

            System.out.println("Cinco primeiras vendas de 2016 de maior preco medio");
            list2016.forEach(System.out::println);
            System.out.println();
            System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = "+String.format("%.2f",sum));

        }
        catch(IOException e){
            System.out.println("Erro: "+e.getMessage());
        }
        sc.close();
    }
}