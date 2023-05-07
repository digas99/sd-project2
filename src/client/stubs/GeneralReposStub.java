package client.stubs;

import genclass.GenericIO;
import utils.*;
import client.entities.*;
import client.main.*;

public class GeneralReposStub {

    /**
     * Name of the platform where is located the general repository server.
     */

    private String serverHostName;

    /**
     * Port number for listening to service requests.
     */

    private int serverPortNumb;

    /**
     * Instantiation of a stub to the general repository.
     *
     * @param serverHostName name of the platform where is located the barber shop server
     * @param serverPortNumb port number for listening to service requests
     */

    public GeneralReposStub(String serverHostName, int serverPortNumb) {
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Operation initialization of the simulation.
     *
     * @param fileName logging file name
     * @param nIter    number of iterations of the customer life cycle
     */

    public void initSimul(String fileName) {

        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message
        while (!con.open()) {
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(MessageType.SETNFIC, fileName);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != MessageType.NFICDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Operation to update the state of the master thief (service request).
     *
     * @param state state of the master thief
     */
    public void updateMasterThiefState(int state) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.UPMTSTATE);
        outMessage.getMasterThiefState();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.UPMTSTATEDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Operation to set the ordinary thief displacement (service request).
     * @param id
     * @param displacement
     */

    public void setOrdinaryThiefDisplacement(int id, int displacement){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTDISP);
        outMessage.getMasterThiefState();
        outMessage.getDisplacement();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTDISPDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    public void setDistanceToRoom(int roomID, int distance){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETDISTTOROOM);
        outMessage.getRoomId();
        outMessage.getRoomDistance();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETDISTTOROOMDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    public void setOrdinaryThiefState(int id, int state){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTSTATE);
        outMessage.getOrdinaryThiefId();
        outMessage.getOrdinaryThiefState();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTSTATEDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }


    /**
     * Set ordinary thief situation
     * if thief is waiting to join a party put 'W'
     * if thief is in a party put 'P'
     * @param id the thief id
     * @param situation boolean true if thief is in a party
     */
    public void setOrdinaryThiefSituation(int id, boolean situation){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTSIT);
        outMessage.getOrdinaryThiefId();
        outMessage.getOrdinaryThiefSituation();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTSITDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Set ordinary thief party id
     * @param id the thief id
     * @param assaultPartyID the party id
     */
    public void setOrdinaryThiefAssaultPartyId(int id, int assaultPartyID){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTAPID);
        outMessage.getOrdinaryThiefId();
        outMessage.getAssaultPartyId();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTAPIDDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Set the ordinary thief position
     * @param id the thief id
     * @param position the position of the thief
     */
    public void setOrdinaryThiefPosition(int id, int position){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTPOS);
        outMessage.getOrdinaryThiefId();
        outMessage.getOrdinaryThiefPosition();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTPOSDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Set the ordinary thief canvas
     * if thief has canvas set 1
     * if thief doesn't have canvas set 0
     * @param id the thief id
     * @param hasCanvas boolean true if thief has canvas
     */
    public void setOrdinaryThiefCanvas(int id, boolean hasCanvas){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SETOTCANVAS);
        outMessage.getOrdinaryThiefId();
        outMessage.hasCanvas();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETOTCANVASDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Set the number of paintings in a room
     * @param roomID the room id
     * @param nPaintings the number of paintings
     */
    public void setnPaintings(int roomID, int nPaintings){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()){
            try{
                Thread.currentThread().sleep((long) (10));
            }catch (InterruptedException e){}
        }

        outMessage = new Message(MessageType.SETNPAINTINGS);
        outMessage.getRoomId();
        outMessage.getRoomPaintings();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETNPAINTINGSDONE){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Set assault party id
     * @param id room id
     */
    public void setAssaultPartyID(int id){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()){
            try{
                Thread.currentThread().sleep((long) (10));
            }catch (InterruptedException e){}
        }

        outMessage = new Message(MessageType.SETAPID);
        outMessage.getAssaultPartyId();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETAPIDDONE){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }
    /**
     * Set the total number of paintings collected
     * @param n_canvas
     */
    public void setnCanvas(int n_canvas){
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()){
            try{
                Thread.currentThread().sleep((long) (10));
            }catch (InterruptedException e){}
        }

        outMessage = new Message(MessageType.SETNCANVAS);
        outMessage.getnCanvas();
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SETNCANVASDONE){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }

    /**
     * Print the sum up
     */
    public void printSumUp() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.PRINTSUMUP);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.PRINTSUMUPDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    /**
     * Operation to shut down the server (service request).
     */

    public void shutDown () {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(MessageType.SHUT);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != MessageType.SHUTDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        con.close();
    }
}
