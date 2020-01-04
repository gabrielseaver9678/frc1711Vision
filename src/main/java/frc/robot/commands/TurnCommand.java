/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.PixyCam;

public class TurnCommand extends CommandBase {
  
  public PixyCam pixy;

  public TurnCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pixy = new PixyCam();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int move = Robot.lastMoveDir;
    Boolean shouldUpdate = false;
    if (Robot.millisTimer > 100) {
      System.out.println("UPDATING MOVE");
      move = pixy.getPoint();
      Robot.lastMoveDir = move;
      Robot.millisTimer = 0;
      shouldUpdate = true;
    }
    double turnSpeed = 0.2; 
    double moveSpeed = 0.5;


    if (shouldUpdate) {
      if (move == 2) {
        System.out.println("STRAIGHT."); 
        pixy.frontLeft.set(moveSpeed);
        pixy.frontRight.set(moveSpeed);
        pixy.rearLeft.set(moveSpeed);
        pixy.rearRight.set(moveSpeed);
      } else if (move == -1) {
        System.out.println("DIR 1"); 
        pixy.frontLeft.set(0);
        pixy.rearLeft.set(0);
        pixy.frontRight.set(-turnSpeed);
        pixy.rearRight.set(-turnSpeed);
      } else if (move == 1) {
        System.out.println("DIR 2"); 
        pixy.frontLeft.set(turnSpeed);
        pixy.rearLeft.set(turnSpeed);
        pixy.frontRight.set(0);
        pixy.rearRight.set(0);
      } else {
        pixy.frontLeft.set(0); 
        pixy.frontRight.set(0); 
        pixy.rearRight.set(0); 
        pixy.rearLeft.set(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
