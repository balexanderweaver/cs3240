import java.io.*;
import java.util.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

public class Controller {
    Communicator comm;
    Claw claw;
    Movement movement;
    Telemetry telemetry;
    
    public Controller(MotorPort clawPort,
    				  MotorPort leftPort,
    				  MotorPort rightPort,
    				  SensorPort light,
    				  SensorPort touch,
    				  SensorPort ultra,
    				  SensorPort sound) {
    	claw = new Claw(clawPort);
    	movement = new Movement(leftPort, rightPort);
    	telemetry = new Telemetry(light, touch, ultra, sound);
    }
   
    public void mainLoop() {
        comm = new Communicator();
       
        comm.start();

        while (true) {
            Message message;
            
            if (!comm.isConnected()) {
            	claw.rotate(100);
            } else {     	
            	// not connected, check for messages
            }
            
            if (comm.hasMessage()) {
            	
            	message = comm.getMessage();
            	
            	//parse the message
            	
               	String command = message.pairs.get(0)[0];
               	String param = message.pairs.get(0)[1];
               	
                System.out.println(command);
               	
                Message ack = new Message(message.getSeqNum());
               	
            	if (command.equals("init")){
            		
            	}
            	else if (command.equals("move")){
            		int move = Integer.parseInt(param);
            		movement.setSpeed(move, move);
            	}
            	else if (command.equals("turn")){
            		int rate = Integer.parseInt(param);
                    movement.setSpeed(rate, -rate);
            	}
            	else if (command.equals("claw")){
                    int heading = Integer.parseInt(param);
            		claw.rotate(heading);
            	}
            	else if (command.equals("stop")){
            		movement.setSpeed(0,0);
            	}
            	else if (command.equals("query")) {
            		// get telemetry data, construct message, comm.send() it
            	    List<String> data =	telemetry.getTelemetryData();
                    Message m = new Message(message.getSeqNum());
                    m.pairs.add(new String[]{"data",null});
                    m.pairs.add(new String[]{"distance","0"});
                    m.pairs.add(new String[]{"light",data.get(0)});
                    m.pairs.add(new String[]{"sound",data.get(1)});
                    m.pairs.add(new String[]{"touch",data.get(2)});
                    m.pairs.add(new String[]{"claw",claw.getAngle()+""});
                    m.pairs.add(new String[]{"heading","0"});
                    m.pairs.add(new String[]{"speed",(movement.getLeftSpeed() + movement.getRightSpeed())/2 + ""});
                    m.pairs.add(new String[]{"ultrasonic",data.get(3)});
                    comm.sendMessage(m);
            	}
            	else if (command.equals("quit")){
            		System.exit(-1);
            	}
            	else if (command.equals("ack")){
            		System.out.println("Waiting for additional commands...");
            	}
            	else{
            		//not a valid command... 
            	}
            	
            	ack.pairs.add(new String[]{"done", command});
            	comm.sendMessage(ack);
                 
            } else {    
            	continue; // queue is empty, terminate loop iteration
            }
        }
    }
}
