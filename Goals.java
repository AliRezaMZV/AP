import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Vector;

public class Goals{   

    public static int BFS(int root, boolean[][] adjacent){
        Queue <Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[12];
        int[] h = new int[12];

        q.add(root);
        seen[root] = true;
        h[root] = 0;

        while(q.size() > 0){
            int head = q.peek();
            q.remove();
            
            if(head == 11)
                return h[head];

            for(int i = 1; i < 12; i++)
                if(adjacent[head][i])
                    if(!seen[i]){
                        q.add(i);
                        seen[i] = true;
                        h[i] = h[head]+1;
                    }
        }

        return 100;
    }

    public static void main(String args[]){
        int t, n;

        Scanner scan = new Scanner(System.in);
        t = scan.nextInt();  
        n = scan.nextInt();
        
        boolean[][] adjacent = new boolean[12][12];

        while(n-- > 0){
            int v, u;
            v = scan.nextInt();
            u = scan.nextInt();

            adjacent[v][u] = adjacent[u][v] = true;
        }
        scan.close();

        int goalTime = BFS(1, adjacent) * t; 
        
        System.out.println(90/goalTime);
    }
}