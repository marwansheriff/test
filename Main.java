import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RSAmethods enc = new RSAmethods();

        System.out.println("enter p ");
        int p = enc.findprime(sc.nextInt());
        System.out.println("enter q ");
        int q = enc.findprime(sc.nextInt());
        System.out.println(p);
        System.out.println(q);
        int n = p * q;
        System.out.println("n = " + n);

        int m = (p - 1) * (q - 1);
        System.out.println("m = " + m);
        int e = enc.getE(m);
        System.out.println("e= " + e);
        int d= enc.getD(m,e);
        System.out.println("d= " + d);
        System.out.println("enter the message ");
        String origmassege=sc.nextLine();
        origmassege=sc.nextLine();
        long encmassege[]=new long[origmassege.length()];
        System.out.println("encryption");
        for(int i=0;i<origmassege.length();i++){
            int charcter=(int)origmassege.charAt(i);
            encmassege[i]=(long) (Math.pow(charcter, e)%n);

            System.out.print((char)(encmassege[i]%257));//l

        }
        System.out.println(" ");
        System.out.println("decryption ");
        char decmassege[]=new char[origmassege.length()];
        for(int i=0;i<origmassege.length();i++){//
            decmassege[i]=(char)(enc.decryption((encmassege[i]), d, n)%257);

            System.out.print(decmassege[i]);
        }
    }
}

class RSAmethods {
    public int findprime(int x) {
        if(x==2)
            return x;
        else if (x % 2 == 0) {
            x--;
        }
        int a = (int) Math.sqrt(x);
        while (a > 1) {

            if (x % a == 0) {
                x -= 2;
                a = (int) Math.sqrt(x);
                continue;
            }
            a--;
        }
        return x;
    }

    public int getE (int m) {//input m=x y=b
        int x = m;
        int y = 1;
        int r;
        do {
            y++;
            int b = y;
            x=m;
            while (b != 0) {
                r = x % b;
                x = b;
                b = r;
            }
        } while (x != 1);
        return y;
    }
    public int getD(int m,int e){
        int k = 1, d;
        while (true) {
            double D = (double)(m * k + 1) / (double)e;
            if (Math.floor(D) == Math.ceil(D)) {
                d = (int) D;
                break;
            } else {
                k++;
            }
        }
        return d;
    }
    public long decryption(long c,int d,int n){
        long x=1;//x store c*x when d is odd
        c%=n;
        while(d!=1){
            if(d%2==1){
                d--;
                x=(c*x)%n;
            }
            c=(c*c)%n;
            d/=2;
        }
        c=(c*x)%n;
        return c;
    }

}