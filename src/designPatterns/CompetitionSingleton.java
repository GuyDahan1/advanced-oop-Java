package designPatterns;

import Graphics.AddCompetition;
import Graphics.CompetitionFrame;

public class CompetitionSingleton {

        private static AddCompetition addCompetition = null;

        private CompetitionSingleton(){
            System.out.println("Creating The Main Frame");
        }

        public static synchronized AddCompetition getInstance(){
            if(addCompetition==null){
                addCompetition = new AddCompetition();
            }
            return addCompetition;
        }

}
