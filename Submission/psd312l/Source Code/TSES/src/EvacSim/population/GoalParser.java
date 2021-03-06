/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EvacSim.population;

import EvacSim.goal.DoorGoal;
import EvacSim.goal.ExitGoal;
import EvacSim.goal.ExitSignGoal;
import com.jme3.math.Vector3f;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author 1003819k
 */
public class GoalParser {
	/**
     *
     * @param filename
     */
    public static void parseGoals(String filename){
		Scanner linescan;
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
		while(linescan.hasNextLine()){

			tokenReader = new Scanner(linescan.nextLine());
			tokenReader.useDelimiter(",");
			type = tokenReader.next();

			if (type.equals("DOOR")){
				float positionx = tokenReader.nextFloat();
				float positiony = tokenReader.nextFloat();
				float positionz = tokenReader.nextFloat();
				float normalX = tokenReader.nextFloat();
				float normalY = tokenReader.nextFloat();
				float normalZ = tokenReader.nextFloat();
				float clearance = tokenReader.nextFloat();

				//initialise door goal
				DoorGoal doorGoal = new DoorGoal(new Vector3f(positionx, positiony, positionz), new Vector3f(normalX, normalY, normalZ), clearance);
				BehaviourModel.getGoals().add(doorGoal);

			} else if(type.equals("EXIT")) {
				float positionx = tokenReader.nextFloat();
				float positiony = tokenReader.nextFloat();
				float positionz = tokenReader.nextFloat();
				float clearance = tokenReader.nextFloat();
				ExitGoal exit = new ExitGoal(new Vector3f(positionx,positiony,positionz), clearance);
				BehaviourModel.getExits().add(exit);

			} else if(type.equals("EXITSIGN")) {
				float positionx = tokenReader.nextFloat();
				float positiony = tokenReader.nextFloat();
				float positionz = tokenReader.nextFloat();
				float normalX = tokenReader.nextFloat();
				float normalY = tokenReader.nextFloat();
				float normalZ = tokenReader.nextFloat();
				float clearance = tokenReader.nextFloat();
				//initialise exit sign goal
				ExitSignGoal exitSignGoal = new ExitSignGoal(new Vector3f(positionx, positiony, positionz), new Vector3f(normalX, normalY, normalZ), clearance);
				BehaviourModel.getGoals().add(exitSignGoal);
			}
		}
	}
}
