package Movement;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

public class Arduino {
    SerialPort port = new SerialPort("COM4");
    SerialPortEvent event;

    public String msg = null;

    /**
     * Creates the instance to access connection to the arduino.
     */
    private Arduino(){
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            port.addEventListener((event) ->{
                if (event.isRXCHAR()){
                    try {
                        msg = port.readString();
                    } catch (SerialPortException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the singleton class
     */
    private static class Singleton{
        private static final Arduino INSTANCE = new Arduino();
    }

    /**
     * Gets the Arduino Singleton Instance
     * @return the arduino singleton class
     */
    public static Arduino getInstance(){
        return Singleton.INSTANCE;
    }

    /**
     * The event listener
     * @return event reported by the serial port
     */
    public SerialPortEvent getlistener(){
        return event;
    }


}
