package designPatterns;

import Graphics.AddCompetition;
import Graphics.CompetitionFrame;

public class CompetitioinSingelton {

        private static AddCompetition addCompetition = null;

        private CompetitioinSingelton(){
            System.out.println("Creating The Main Frame");
        }

        public static synchronized AddCompetition getInstance(){
            if(addCompetition==null){
                addCompetition = new AddCompetition();
            }
            return addCompetition;
        }

}
