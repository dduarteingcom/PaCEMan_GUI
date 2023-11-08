package GUI;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

class Arduino {
    SerialPort port = new SerialPort("COM4");
    SerialPortEvent event;

    String msg = null;
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
    private static class Singleton{
        private static final Arduino INSTANCE = new Arduino();
    }

    public static Arduino getInstance(){
        return Singleton.INSTANCE;
    }

    public SerialPortEvent getlistener(){
        return event;
    }


}
