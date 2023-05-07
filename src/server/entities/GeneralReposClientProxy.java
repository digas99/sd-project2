package server.entities;

import genclass.GenericIO;
import server.sharedRegions.ConcentrationSiteInterface;
import server.sharedRegions.GeneralReposInterface;
import utils.Message;
import utils.MessageException;
import utils.ServerCom;

public class GeneralReposClientProxy extends ClientProxy {
    private GeneralReposInterface GeneralReposInter;

    public GeneralReposClientProxy(ServerCom sconi, GeneralReposInterface inter) {
        super(sconi, "GeneralReposClientProxy");
        this.GeneralReposInter = inter;
    }

    @Override
    public void run() {
        Message inMessage = null, outMessage = null;

        /* service providing */

        inMessage = (Message) sconi.readObject();
        try {
            outMessage = GeneralReposInter.processAndReply(inMessage);
        } catch (MessageException e) {
            GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage());
            GenericIO.writelnString(e.getMessageVal().toString());
            System.exit(1);
        }
        sconi.writeObject(outMessage);

    }

}