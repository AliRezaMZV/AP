import java.util.Scanner;
import java.lang.Math;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FootballPlayerRobots{

    public static int findNextValidData(String str, int from){
        Pattern pattern = Pattern.compile("@#(forward|keeper),x=[0-9]+,y=[0-9]+,distance=[0-9]+#@");
        
        Matcher strMatcher = pattern.matcher(str);
        
        if(strMatcher.find(from))
            return strMatcher.start();
        else
            return str.length();
    }

    public static int findDataEnd(String str, int dataBeginPos){
        Pattern pattern = Pattern.compile("@#(forward|keeper),x=[0-9]+,y=[0-9]+,distance=[0-9]+#@");
        
        Matcher strMatcher = pattern.matcher(str);
        
        strMatcher.find(dataBeginPos);

        return strMatcher.end()-1;
    }

    public static int getNum(String str, int[] refPos){
        int num = 0;
        
        while(refPos[0]<str.length() && Character.isDigit(str.charAt(refPos[0])))
            num = num*10+str.charAt(refPos[0]++)-'0';

        return num;
    }

    public static int getDist(String str, int pos){
        pos += 12;
        int[] callByRefPos = {pos};
        getNum(str, callByRefPos);
        pos = callByRefPos[0];

        pos+=3;
        callByRefPos[0] = pos;
        getNum(str, callByRefPos);
        pos = callByRefPos[0];

        pos += 10;
        int dist = getNum(str, new int[]{pos});
        
        return dist;
    }

    public static boolean checkGoal(String str, int pos){
        pos += 12;
        int[] callByRefPos = {pos};
        int x = getNum(str, callByRefPos);
        pos = callByRefPos[0];
     
        pos+=3;
        callByRefPos[0] = pos;
        int y = getNum(str, callByRefPos);
        pos = callByRefPos[0];
        
        pos += 10;
        int dist = getNum(str, new int[]{pos});
     
        if(Math.sqrt(x*x+y*y)-(double)dist <= 10)
            return true;
        
        return false;
    }

    public static void main(String args[]){
        String input = "";
        Scanner scan = new Scanner(System.in);
        input = scan.nextLine();
        scan.close();

        int goal = 0, lastPos = -1,  invalid = 0,  lastDist = -1;
        boolean isForwardShoot = false;
        while(lastPos < input.length()){
            int pos = findNextValidData(input, lastPos+1);
     
            invalid += (pos-1)-lastPos;
            if(pos>=input.length() || invalid>=200)
                break;
            invalid = 0;
           
            if(input.charAt(pos+2) == 'f'){
                int dist = getDist(input, pos);

                if(dist>lastDist && checkGoal(input, pos) && isForwardShoot){
                    goal++;
                    isForwardShoot = false;
                }
                else if(dist <= lastDist)
                   isForwardShoot = false;
                
                if(dist <= 10)   
                    isForwardShoot = true;
               
                lastDist = dist;
                
                lastPos = findDataEnd(input, pos);
                    
                continue;
            }
            else
                lastPos = findDataEnd(input, pos);
        }
        
        System.out.println(goal);
    }
}