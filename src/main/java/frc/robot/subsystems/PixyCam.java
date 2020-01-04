package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.pixy2api.*;
import frc.pixy2api.Pixy2CCC.*;
import frc.pixy2api.links.*;

public class PixyCam extends SubsystemBase {
    private Pixy2 pixy;
    
    public WPI_TalonSRX frontLeft;
    public WPI_TalonSRX rearLeft;
    public WPI_TalonSRX frontRight;
    public WPI_TalonSRX rearRight;
    public SpeedControllerGroup leftDrive; 
    public SpeedControllerGroup rightDrive; 
    public DifferentialDrive rDrive; 

    public PixyCam () {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init();
    }

    private Block getBlock() {
        ArrayList<Block> blocks = pixy.getCCC().getBlocks();
        Block largestBlock = null;
        for (Block block : blocks) {
            if (largestBlock == null) {
                largestBlock = block;
            } else if (largestBlock.getWidth() < block.getWidth()) {
                largestBlock = block;
            }
        }
        return largestBlock;
    }

    public int getPoint() {
        Block block = getBlock();
        //The bottom left point is 315, 207
        //The center point is 158, 103
        int x = block.getX() + block.getWidth()/2;
        if (Math.abs(x - 158) < 45) {
            System.out.println("Near center of screen");
            return 2;
        } else {
            System.out.println("Not in center");
            return 0;
        }
    }
}