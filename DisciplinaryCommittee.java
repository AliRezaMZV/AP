import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class DisciplinaryCommittee{

    public static void checkIsAllowedPlayer(String playerName, String playerTeamName, String[] allowedPlayers, SortedSet<String>delinquentedTeams){
        for(int i = 0; i < allowedPlayers.length; i++)
            if(playerName.equals(allowedPlayers[i]))
                return;

        delinquentedTeams.add(playerTeamName);

        return;
    }

    public static void checkHavingTeam(String playerName, int playerId, String playerTeamName, String[][] teamsPlayers, int[] teamsPlayersCount, 
                                       String[] teamsNames, SortedSet<String>delinquentedTeams){
        for(int i = 0; i < teamsPlayers.length; i++)
            for(int j = 0; j < teamsPlayersCount[i]; j++)
                if(playerName.equals(teamsPlayers[i][j]))
                    if(!playerTeamName.equals(teamsNames[i]) || j!=playerId){ 
                        delinquentedTeams.add(playerTeamName);
                        delinquentedTeams.add(teamsNames[i]);
                    }
                
        return;
    }

    public static void main(String args[]){
        int n, m;
        
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();

        String[] allowedPlayers = new String[n];
        String[] teamsNames = new String[m];
        int[] teamsPlayersCount = new int[m];
        String[][] teamsPlayers = new String[m][100];
        SortedSet <String> delinquentTeams = new TreeSet<>();

        for(int i = 0; i < n; i++)
            allowedPlayers[i] = scan.nextLine();
        
      
        for(int i = 0; i < m; i++){
            teamsNames[i] = scan.nextLine();
            teamsPlayersCount[i] = scan.nextInt();
            scan.nextLine();
            
            for(int j = 0; j < teamsPlayersCount[i]; j++){
                teamsPlayers[i][j] = scan.nextLine();

                checkIsAllowedPlayer(teamsPlayers[i][j], teamsNames[i], allowedPlayers, delinquentTeams); 
                checkHavingTeam(teamsPlayers[i][j], j, teamsNames[i], teamsPlayers, teamsPlayersCount, teamsNames, delinquentTeams);
            }
        }
        scan.close();

        while(delinquentTeams.size() > 0){
            System.out.println(delinquentTeams.first());
            delinquentTeams.remove(delinquentTeams.first());   
        }
    }

}