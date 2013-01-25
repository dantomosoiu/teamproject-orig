/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import com.jme3.math.Vector3f;
import goal.ExitGoal;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author 1003819k
 */
public class GoalParser {
    public static void parseGoals(String filename){
        Scanner linescan = null;
        try{
            FileInputStream file = new FileInputStream(filename);
            linescan = new Scanner(file);
        }catch(Exception e){
            System.err.println("Goal file could not be opened!");
            System.err.print(e);
            return;
        }
        
        Scanner tokenReader;
        String type;
        Vector3f position;
        while(linescan.hasNextLine()){
           tokenReader = new Scanner(linescan.nextLine());
           tokenReader.useDelimiter(",");
           type = tokenReader.next();
           if(type.equals("DOOR")){
               float positionx = tokenReader.nextFloat();
               float positiony = tokenReader.nextFloat();
               float positionz = tokenReader.nextFloat();
               float normalX = tokenReader.nextFloat();
               float normalY = tokenReader.nextFloat();
               float normalZ = tokenReader.nextFloat();
               float clearance = tokenReader.nextFloat();
               //insert code for initilising door goal
           }else if(type.equals("EXIT")){
               float positionx = tokenReader.nextFloat();
               float positiony = tokenReader.nextFloat();
               float positionz = tokenReader.nextFloat();
               float clearance = tokenReader.nextFloat();
               ExitGoal exit = new ExitGoal(new Vector3f(positionx,positiony,positionz), clearance);
               BehaviourModel.getExits().add(exit);
           }
        }
    }
}