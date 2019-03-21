/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.grp2.gnode.hardware.GreenhouseAPI.GreenhouseAPI;

import org.grp2.gnode.hardware.GreenhouseAPI.PLCCommunication.PLCConnection;
import org.grp2.gnode.hardware.GreenhouseAPI.PLCCommunication.UDPConnection;

/**
 * API tester
 * @author sps
 */
public class TestGreenhouse 
{
    public static void main(String[] args) throws InterruptedException {
        //PLCConnection con = new UDPConnection(1025, "localhost");
        PLCConnection con = new UDPConnection(5000, "192.168.0.40");
        //PLCConnection con = new SerialConnection("COM4");
        //SerialConnection.getPortList("COM1");
        
        IGreenhouse api = new Greenhouse(con);
        api.SetRedLight(100);

        api.SetBlueLight(0);

        api.SetFanSpeed(1);

        api.SetTemperature(273 + 25);
        //api.SetFanSpeed(1);
        double outdoorTemperature;
        while (true) {
            Thread.sleep(3000);
            outdoorTemperature = api.ReadTemp2();
            System.out.println(outdoorTemperature);
            System.out.println(api.ReadWaterLevel());
            System.out.println(api.ReadTemp1());
            System.out.println(api.ReadMoist());
        }
        
       
        
                
        //System.exit(3);
    }
    
}
